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
		protected static int mGroupIter;

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
			
			final CharSequence[] groups = {
	                "Clock Letters/Ê±ÖÓ×ÖÄ¸", 
	                "Hill Letters/É½Çð×ÖÄ¸", 
	                "Loop Letters/È¦È¦×ÖÄ¸",
	                "Line Letters/ÏßÌõ×ÖÄ¸"
	        };

	        AlertDialog.Builder builder = new AlertDialog.Builder(LessonPickerActivity.this);
	        builder.setTitle("Select a letter to learn:");
	        if (mLessonChoice == 3) //pick by group, lower case only
        	{
	        	builder.setItems(groups, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int group) {
	            		LessonPickerActivity.mSelection = group;
	            		
	            		mGroupIter = 0;
	            		
	            		switch (group)
	            		{
	            		case 0:
	            			mLoopNumber = 0;
	            			break;
	            		case 1:
	            			mLoopNumber = 13;
	            			break;
	            		case 2:
	            			mLoopNumber = 4;
	            			break;
	            		case 3:
	            			mLoopNumber = 8;
	            			break;
            			default:
            				break;
	            		}
		            	
		            	// Send intent resources gathered to lesson Activity class
		            	Intent intent;
		            	
	            		intent = new Intent(getBaseContext(), LowerLessonActivity.class);
		            	intent.putExtra("LESSON_ID", mLessonChoice);
	                	intent.putExtra("LOOP_NUMBER", mLoopNumber);
	                	intent.putExtra("LETTER_PICK", mSelection);
	                	//intent.putExtra("GROUP_PICK", mGroupPick);
	             	   	startActivity(intent);
		            }
		        });
        	}
	        else //pick by letter
        	{
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
        	}
	        AlertDialog alert = builder.create();
	        alert.show();
		}
	}
