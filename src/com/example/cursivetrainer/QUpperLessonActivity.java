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

public class QUpperLessonActivity extends LessonActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mLayout = (RelativeLayout) findViewById(getLayoutID());
		
		// Get parameter from main activity
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    mLessonChoice = extras.getInt("LESSON_ID");
		    mLoopNumber = extras.getInt("LOOP_NUMBER");
		    mLetterPick = extras.getInt("LETTER_PICK");
		}
		
		// Determine which lesson mode we need to enter
		Resources res = getResources();
		
		mLetter = new LetterInfo();
		
		try
		{
			switch(mLessonChoice)
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
    	textView.setText("Instructions for the letter 'Qu' \n\n" + stringBuilder);
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
		return R.id.capitallesson_qu_layout;
	}

	@Override
	protected int getViewID() {
		// TODO Auto-generated method stub
		return R.layout.capital_lesson_qu_layout;
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
            	int nextIter = mLoopNumber + 1;
            	Intent intent = new Intent(getBaseContext(), UpperLessonActivity.class);
            	intent.putExtra("LESSON_ID", 0);
            	intent.putExtra("LOOP_NUMBER", nextIter);
            	finish();
            	startActivity(intent);
            }
        });
	}
}

