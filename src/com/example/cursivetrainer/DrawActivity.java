/**
 * DrawActivity.java
 * 
 * Base Activity class that creates simple practice interface with user to learn how to write cursive characters
 * Only has a reset button to let user redraw if he/she made mistakes
 * 
 * @author Michael Hii
 */


package com.example.cursivetrainer;

import java.util.Random;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public abstract class DrawActivity extends Activity {
	
	private static final String TAG = "DrawView";
    
    DrawView mDrawView;
    RelativeLayout mLayout;
    NotificationManager mNotificationManager;
    
    public final static int DRAWVIEW_ID = 10;
    protected final static int NOTIFICATION_ID = 9999;
    protected final static int INVALID_RESOURCE = -1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getViewID());
		mLayout = (RelativeLayout) findViewById(getLayoutID());
		
		setLayout();
    }
	
	protected abstract int getLayoutID();
	protected abstract int getViewID();
	
	protected void setLayout()
	{
		mDrawView = new DrawView(this);
		mDrawView.setId(DRAWVIEW_ID);
		mLayout.addView(mDrawView);
        
        createResetButton();
        
        createSaveButton();
        
        setContentView(mLayout);
        mDrawView.requestFocus();
	}
	
	protected void createResetButton()
	{
		Button b = new Button(this);
        b.setText("Reset");
        LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(mLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(mLayout.ALIGN_PARENT_LEFT);
        mLayout.addView(b, lp);
        b.setOnClickListener(new OnClickListener()
        {      	
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	mDrawView.mBitmap.eraseColor(Color.TRANSPARENT);
            	mDrawView.mPath.reset();
            	mDrawView.invalidate();
            	
            	Log.d(TAG, "Reset button pressed!");
            }
        });
	}
	
	protected void createSaveButton()
	{
		Button b = new Button(this);
        b.setText("Save");
        LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(mLayout.ALIGN_PARENT_TOP);
        lp.addRule(mLayout.CENTER_HORIZONTAL);
        mLayout.addView(b, lp);
        b.setOnClickListener(new OnClickListener()
        {      	
            @Override
            public void onClick(View v) {
                String fileMsg = mDrawView.saveImage(mDrawView.mBitmap);
                
                Toast.makeText(DrawActivity.this,
                		"Saving image to: " + fileMsg, Toast.LENGTH_SHORT).show();
                
                if (isExternalStorageWritable() && isExternalStorageReadable())
                {
	                // Display saved image in gallery
                	// Refresh gallery to display image
                	MediaScannerConnection.scanFile(DrawActivity.this, new String[]{fileMsg}, null,
                            new MediaScannerConnection.OnScanCompletedListener() 
                	{
						@Override
						public void onScanCompleted(final String path, final Uri uri) 
						{
						    Log.i(TAG, String.format("Scanned path %s -> URI = %s", path, uri.toString()));
						}
					});
	                sendNotification("Cursive Trainer Image", "Image has been saved to: " + fileMsg, fileMsg);
                }
                else
                {
                	Toast.makeText(DrawActivity.this,
                    		"Image cannot be saved! Please check your external storage", Toast.LENGTH_SHORT).show();
                }
            	
            	Log.d(TAG, "Save button pressed!");
            }
        });
	}
	
	@SuppressWarnings("deprecation")
	protected void sendNotification(String title, String msg, String filePath) 
	{
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(
				R.drawable.ic_launcher, "Image saved!", System.currentTimeMillis());
		notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
		
		Intent notificationIntent = new Intent(Intent.ACTION_PICK);
		notificationIntent.setType("image/*");
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(DrawActivity.this, title, msg, pendingIntent);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}

	/**
	 * Creates instructions for the lesson activities
	 * @param input
	 * @return
	 */
	protected String buildString(String[] input)
	{
		String fin = "";
		int arrSize = input.length;
		
		for(int i = 0; i < arrSize; i++)
		{
			fin = fin + input[i] + "\n";
			
			// Insert line break where English and Chinese instructions split
			if(i == ((arrSize / 2) - 1))
			{
				fin = fin + "\n";
			}
		}
		
		return fin;
	}
	
	protected int getRand()
	{
		int min = 0;
		int max = 25;

		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
	
	/* Checks if external storage is available for read and write */
	protected boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	protected boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
