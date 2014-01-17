/**
 * LessonPickerActivity.java
 * 
 * Intermediate Activity to allow user to pick an individual letter to learn
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

	public class LessonPickerActivity extends Activity {
		protected static int mSelection;
		protected static int mLessonChoice;
		protected static int mLoopNumber;
		protected static boolean mIsCapital;

		@Override
	    public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			// Get resources from passed Intent
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
			    mLessonChoice = extras.getInt("LESSON_ID");
			    mLoopNumber = extras.getInt("LOOP_NUMBER");
			    mIsCapital = extras.getBoolean("IS_CAPITAL");
			}
			
			final CharSequence[] items = {
	                "A/a", 
	                "B/b", 
	                "C/c",
	                "D/d", 
	                "E/e", 
	                "F/f",
	                "G/g", 
	                "H/h", 
	                "I/i",
	                "J/j", 
	                "K/k", 
	                "L/l",
	                "M/m", 
	                "N/n", 
	                "O/o",
	                "P/p", 
	                "Q/q", 
	                "R/r",
	                "S/s", 
	                "T/t", 
	                "U/u",
	                "V/v", 
	                "W/w", 
	                "X/x",
	                "Y/y",
	                "Z/z"
	        };

	        AlertDialog.Builder builder = new AlertDialog.Builder(LessonPickerActivity.this);
	        builder.setTitle("Select a letter to learn:");
	        builder.setItems(items, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int item) {
	            	LessonPickerActivity.mSelection = item;
	            	
	            	// Send intent resources gathered to lesson Activity class
	            	Intent intent;
	            	
	            	if (mIsCapital)
	            	{
	            		intent = new Intent(getBaseContext(), UpperLessonActivity.class);
	            	}
	            	else
	            	{
	            		intent = new Intent(getBaseContext(), LowerLessonActivity.class);
	            	}
	            	intent.putExtra("LESSON_ID", mLessonChoice);
                	intent.putExtra("LOOP_NUMBER", mLoopNumber);
                	intent.putExtra("LETTER_PICK", mSelection);
             	   	startActivity(intent);
	            }
	        });
	        AlertDialog alert = builder.create();
	        alert.show();
		}
	}
