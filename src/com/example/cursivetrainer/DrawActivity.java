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

public abstract class DrawActivity extends Activity {
	
	private static final String TAG = "DrawView";
    
    DrawView mDrawView;
    RelativeLayout mLayout;
    NotificationManager mNotificationManager;
    
    public final static int DRAWVIEW_ID = 10;
    protected final static int NOTIFICATION_ID = 9999;
	
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
                mDrawView.saveImage(mDrawView.mBitmap);
                
                String fileMsg = "file://" + Environment.getExternalStorageDirectory();
                
                // Display saved image in gallery
                sendBroadcast(new Intent(
                		Intent.ACTION_MEDIA_MOUNTED,
                		            Uri.parse(fileMsg)));
                
                sendNotification("Cursive Trainer Image", "Image has been saved to: " + fileMsg, fileMsg);
            	
            	Log.d(TAG, "Save button pressed!");
            }
        });
	}
	
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

	protected String buildString(String[] input)
	{
		String fin = "";
		int arrSize = input.length;
		
		for(int i = 0; i < (arrSize / 2); i++)
		{
			fin = fin + input[i] + "\n" + input[i + (arrSize / 2)] + "\n";
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
