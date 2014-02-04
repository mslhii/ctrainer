/**
 * DrawView.java
 * 
 * Paint example code borrowed from tutorial for purposes
 * 
 */

package com.example.cursivetrainer;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class DrawView extends View implements OnTouchListener {

	public Bitmap  mBitmap;
	private Canvas  mCanvas;
	public Path    mPath;
	private Paint   mBitmapPaint;
	Context context;
	private Paint circlePaint;
	private Path circlePath;
	private Paint mPaint;

	public DrawView(Context c) {
		super(c);
		context=c;
		mPath = new Path();
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);  
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
	    mPaint.setDither(true);
	    mPaint.setColor(Color.RED);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeJoin(Paint.Join.ROUND);
	    mPaint.setStrokeCap(Paint.Cap.ROUND);
	    mPaint.setStrokeWidth(12);
		circlePaint = new Paint();
		circlePath = new Path();
		circlePaint.setAntiAlias(true);
		circlePaint.setColor(Color.BLUE);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setStrokeJoin(Paint.Join.MITER);
		circlePaint.setStrokeWidth(4f); 

		setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

		canvas.drawPath(mPath, mPaint);

		canvas.drawPath(circlePath, circlePaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}
	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
			mX = x;
			mY = y;

			circlePath.reset();
			circlePath.addCircle(mX, mY, 30, Path.Direction.CW); 

		}
	}
	private void touch_up() {
		mPath.lineTo(mX, mY);
		circlePath.reset();
		mCanvas.drawPath(mPath, mPaint);
		// Reset path to every time user stops contact with screen
		mPath.reset();

	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}  
	
	public String saveImage(Bitmap bmp) 
	{
		File root = Environment.getExternalStorageDirectory();
	    File dir = new File(root + File.separator + "cursive_trainer_images");
	    if(dir.isDirectory())
	    {
	    	dir.mkdirs();
	    }
	    String fname = "CursiveLetter-"+ System.currentTimeMillis() +".jpg";
	    File file = new File (dir, fname);
	    if (file.exists()) 
    	{
	    	file.delete(); 
    	}
	    
	    try 
	    {
	           FileOutputStream out = new FileOutputStream(file);
	           bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
	           out.flush();
	           out.close();
	    } 
	    catch (Exception e) 
	    {
	    	Toast.makeText(context,
            		"Image cannot be saved to" + file.toString() + 
            		", error is: " + e.toString(), Toast.LENGTH_LONG).show();
	    	e.printStackTrace();
	    }
	    
	    // Return path image saved to
	    return dir.toString() + "/" + fname;
	}
}

class Dot {
	float x, y;

	@Override
	public String toString() {
		return x + ", " + y;
	}
}
