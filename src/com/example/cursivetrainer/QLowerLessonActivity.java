/**
 * LowerLessonActivity.java
 * 
 * Activity class that creates interface with user to learn how to write cursive characters
 * Creates button to display video alert, instruction sidebar, and picture answer
 * Subclassed from LessonActivity
 * 
 * TODO: Improve UI and polish it
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class QLowerLessonActivity extends LessonActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mLayout = (RelativeLayout) findViewById(getLayoutID());
		
		// Get parameter from main activity
		int lessonChoice = INVALID_SELECTION;
		Bundle extras = getIntent().getExtras();
		if (extras != null) 
		{
		    mLessonChoice = lessonChoice = extras.getInt("LESSON_ID");
		    mLoopNumber = extras.getInt("LOOP_NUMBER");
		    mLetterPick = extras.getInt("LETTER_PICK"); //letter or group choice from picker
		}
		
		// Determine which lesson mode we need to enter
		Resources res = getResources();
		
		mLetter = new LetterInfo();
		
		try
		{
			switch(lessonChoice)
			{
			case 0:
			case 3:
				mLetter.determineLowerLetter(mLoopNumber, res);
				break;
			case 1:
				mLetter.determineLowerLetter(mLetterPick, res);
				break;
			case 2:
				int rand = getRand();
				mLetter.determineLowerLetter(rand, res);
				break;
			default:
				break;
			}
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
        
        // Create instructions
        String stringBuilder = buildString(mLetter.info);
        TextView textView = (TextView) mLayout.findViewById(R.id.textView1);
    	textView.setText("Instructions for the letter 'qu' \n\n" + stringBuilder);
        textView.setBackgroundColor(Color.WHITE);
        
        // Create instruction button
        createInstructionButton();
        
        // Create video tutorial button
        createVideoButton();
        
        if(mLoopNumber > INVALID_SELECTION)
    	{
    		this.createNextButton();
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.id.drawlesson_qu_layout;
	}

	@Override
	protected int getViewID() {
		// TODO Auto-generated method stub
		return R.layout.draw_lesson_qu_layout;
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
	            	Intent intent = new Intent(getBaseContext(), LowerLessonActivity.class);
	            	intent.putExtra("LESSON_ID", 0);
	            	intent.putExtra("LOOP_NUMBER", nextIter);
	            	finish();
	            	startActivity(intent);
            	}
            }
            
            public void finishedAlert()
            {
            	AlertDialog.Builder builder = new AlertDialog.Builder(QLowerLessonActivity.this);
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
}