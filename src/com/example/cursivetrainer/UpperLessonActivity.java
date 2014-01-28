/**
 * LessonActivity.java
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

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UpperLessonActivity extends LessonActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mLayout = (RelativeLayout) findViewById(getLayoutID());
		
		// Get parameter from main activity
		int lessonChoice = INVALID_SELECTION;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    lessonChoice = extras.getInt("LESSON_ID");
		    mLoopNumber = extras.getInt("LOOP_NUMBER");
		    mLetterPick = extras.getInt("LETTER_PICK");
		}
		
		// Determine which lesson mode we need to enter
		Resources res = getResources();
		
		mLetter = new LetterInfo();
		
		try
		{
			switch(lessonChoice)
			{
			case 0:
				mLetter.determineUpperLetter(mLoopNumber, res);
				break;
			case 1:
				mLetter.determineUpperLetter(mLetterPick, res);
				break;
			case 2:
				int rand = getRand();
				mLetter.determineUpperLetter(rand, res);
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
        textView.setText("Instructions for the letter '"+ mLetter.letter +"' \n\n" + stringBuilder);
        textView.setBackgroundColor(Color.WHITE);
        
        // Create instruction button
        createInstructionButton();
        
        // Create video tutorial button
        createVideoButton();
        
        if(mLoopNumber > INVALID_SELECTION)
    	{
    		createNextButton();
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
		return R.id.capitallesson_layout;
	}

	@Override
	protected int getViewID() {
		// TODO Auto-generated method stub
		return R.layout.capital_lesson_layout;
	}
}

