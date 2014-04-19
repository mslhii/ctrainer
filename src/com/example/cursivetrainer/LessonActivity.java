/**
 * LessonActivity.java
 * 
 * Abstract Activity class that creates interface with user to learn how to write cursive characters
 * Creates button to display video alert, instruction sidebar, and picture answer
 * Subclass of DrawActivity to inherit draw layout
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;

public abstract class LessonActivity extends DrawActivity {
	
	private static final String TAG = "DrawView";
	public final static int DRAWVIEW_ID = 10;
    public final static int INVALID_SELECTION = -1;
    public final static int MAX_ALPHABET = 26;
	
    protected static LetterInfo mLetter;
    
	protected DrawView mDrawView;
	protected RelativeLayout mLayout;
    protected static int mLoopNumber;
    protected static int mLetterChoice;
    protected static int mLetterPick;
    protected static int mLessonChoice;
    protected static int mGroupIter;
    private static ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void createVideoButton()
	{
		// Create instruction button
        Button video = new Button(this);
        video.setText("Tutorial");
        LayoutParams lpinfo = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpinfo.addRule(mLayout.ALIGN_PARENT_TOP);
        lpinfo.addRule(mLayout.ALIGN_PARENT_LEFT);
        mLayout.addView(video, lpinfo);
        video.setOnClickListener(new OnClickListener()
        {      	
            @Override
            public void onClick(View v) {
            	if(isNetworkAvailable())
            	{
            		final VideoView videoview;
	            	progressDialog = ProgressDialog.show(LessonActivity.this, "", "Loading...", true);
	                AlertDialog.Builder builder = new AlertDialog.Builder(LessonActivity.this);
	                LayoutInflater inflater = LessonActivity.this.getLayoutInflater();  
	                builder.setTitle("Video Tutorial");
	                builder.setView(inflater.inflate(R.layout.video_dialog, null))
	                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                           public void onClick(DialogInterface dialog, int id) {
	                           }
	                       })
	                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                           public void onClick(DialogInterface dialog, int id) {
	                               // User cancelled the dialog
	                        	   //videoview.start();
	                        	   //videoview.seekTo(0);
	                           }
	                       });
	                // Create the AlertDialog object and return it
	                AlertDialog alert = builder.create();
	                alert.show();
	                
	                // Fixes Nexus 7 window dimming bug
	                WindowManager.LayoutParams dimness = alert.getWindow().getAttributes();
	                dimness.dimAmount = 0;
	                alert.getWindow().setAttributes(dimness);
	                
	                // Play the video after the view has been set
	                videoview = (VideoView) alert.findViewById(R.id.videoview);
	                Uri uri;
	                //if (mLetter.videoString != INVALID_RESOURCE)
	                if (mLetter.videoURI != null)
	                {
	                	//uri = Uri.parse("android.resource://"+getPackageName()+"/"+mLetter.videoString);
	                	uri = Uri.parse(mLetter.videoURI);
	                	videoview.setVideoURI(uri);
	                    videoview.start();  
	                    progressDialog.dismiss();
	                    videoview.requestFocus();
	                }
	                else
	                {
	                	Toast.makeText(LessonActivity.this,
	                    		"Video cannot be played. Illegal letter selected.", Toast.LENGTH_SHORT).show();
	                }
	            	
	            	Log.d(TAG, "Video button pressed!");
	            }
	            else
	            {
	            	Toast.makeText(LessonActivity.this,
	                		"Video cannot be played. Please check your internet connection.", Toast.LENGTH_SHORT).show();
	            }
            }	
        });
	}
	
	protected void createInstructionButton()
	{		
		// Create instruction button
        Button info = new Button(this);
        info.setText("Answers");
        LayoutParams lpinfo = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpinfo.addRule(mLayout.ALIGN_PARENT_BOTTOM);
        lpinfo.addRule(mLayout.CENTER_HORIZONTAL);
        mLayout.addView(info, lpinfo);
        info.setOnClickListener(new OnClickListener()
        {      	
            @Override
            public void onClick(View v) {
            	// Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(LessonActivity.this);
                LayoutInflater inflater = LessonActivity.this.getLayoutInflater();
                builder.setTitle("Picture");
                
                builder.setView(inflater.inflate(R.layout.image_dialog, null))
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       })
                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               // User cancelled the dialog
                           }
                       });
                // Create the AlertDialog object and return it
                AlertDialog alert = builder.create();
                alert.show();
                
                final ImageView iv = (ImageView) alert.findViewById(R.id.imageview);
                //iv.setImageResource(mLetter.resourceString);
                
                new DownloadImageTask(iv).execute(mLetter.imageString);
            	
            	Log.d(TAG, "Instruction button pressed!");
            }
        });
	}
	
	protected void createNextButton()
	{
		Button b = new Button(this);
        b.setText("Next");
        LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(mLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(mLayout.ALIGN_PARENT_RIGHT);
        mLayout.addView(b, lp);
        b.setOnClickListener(new OnClickListener()
        {      	
            @Override
            public void onClick(View v) 
            {
            	if (mLessonChoice == 3) //group picking
            	{
            		int nextIter = -1;
            		int arrSize = 0;
            		
            		mGroupIter++;
            		
            		//TODO: code clean please
            		switch (mLetterPick)
            		{
            		case 0:
            			arrSize = mLetter.clockLetters.length;
            			if (mGroupIter < arrSize)
            			{
            				nextIter = mLetter.clockLetters[mGroupIter];   
            			}
            			break;
            		case 1:
            			arrSize = mLetter.hillLetters.length;
            			if (mGroupIter < arrSize)
            			{
            				nextIter = mLetter.hillLetters[mGroupIter]; 
            			}
            			break;
            		case 2:
            			arrSize = mLetter.loopLetters.length;
            			if (mGroupIter < arrSize)
            			{
            				nextIter = mLetter.loopLetters[mGroupIter];   
            			}
            			break;
            		case 3:
            			arrSize = mLetter.lineLetters.length;
            			if (mGroupIter < arrSize)
            			{
            				nextIter = mLetter.lineLetters[mGroupIter];
            			}
            			break;
        			default:
        				break;
            		}
            			
	            	if(mGroupIter < arrSize)
	            	{
		            	Intent intent = getIntent();
		            	intent.putExtra("LESSON_ID", 3);
		            	intent.putExtra("LOOP_NUMBER", nextIter);
		            	finish();
		            	startActivity(intent);
	            	}
	            	else
	            	{
	            		mGroupIter = 0; //reset iterator for next batch
	            		finishedAlert();
	            	}
            	}
            	else
            	{
	            	int nextIter = mLoopNumber + 1;
	            	if(nextIter < MAX_ALPHABET)
	            	{
		            	Intent intent = getIntent();
		            	intent.putExtra("LESSON_ID", 0);
		            	intent.putExtra("LOOP_NUMBER", nextIter);
		            	finish();
		            	startActivity(intent);
	            	}
	            	else
	            	{
	            		finishedAlert();
	            	}
            	}
            }
            
            public void finishedAlert()
            {
            	AlertDialog.Builder builder = new AlertDialog.Builder(LessonActivity.this);
                builder.setMessage("You have finished the lesson! Press OK to go back to the home screen.")
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        	   startActivity(intent);
                           }
                       });
                // Create the AlertDialog object and return it
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}
}

