/**
 * MainActivity.java
 * 
 * Main menu class that presents options for the user to pick
 * TODO: refine UI in future versions, add custom buttons, etc.
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	/*
	private Button mPracticeBtn;
	private Button mLessonBtn;
	private Button mWordBtn;
	private Button mFreeBtn;
	*/
	
	private ImageButton mPracticeBtn;
	private ImageButton mLessonBtn;
	private ImageButton mWordBtn;
	private ImageButton mFreeBtn;
	
	private final static int LESSON_ALL = 0;
	private final static int LESSON_ONE = 1;
	private final static int LESSON_RANDOM = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set lower case practice button
        SetLowerPracticeButton();
        
        // Set lower case lesson button
        SetLowerLessonButton();
           
        // Set word button
        SetWordButton();
        
        // Set freestyle button
        SetFreestyleButton();
        
        // Set lower case practice button
        SetUpperPracticeButton();
        
        // Set upper case lesson button
        SetUpperLessonButton();
    }
    
    private void SetLowerPracticeButton()
    {
        mPracticeBtn = (ImageButton) findViewById(R.id.lcp);
        mPracticeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), LowerDrawActivity.class);
                startActivity(intent);
            }
        });
    }
    
    private void SetUpperPracticeButton()
    {
        mPracticeBtn = (ImageButton) findViewById(R.id.ucp);
        mPracticeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), UpperDrawActivity.class);
                startActivity(intent);
            }
        });
    }
    
    private void SetWordButton()
    {
    	mWordBtn = (ImageButton) findViewById(R.id.lw);
        mWordBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), WordActivity.class);
                startActivity(intent);
            }
        });
    }
    
    private void SetFreestyleButton()
    {
    	mFreeBtn = (ImageButton) findViewById(R.id.fw);
        mFreeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), FreeActivity.class);
                startActivity(intent);
            }
        });
    }
    
    private void SetLowerLessonButton()
    {
    	mLessonBtn = (ImageButton) findViewById(R.id.lcl);
        mLessonBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	final CharSequence[] items = {
                        "All letters in ABC order", "One letter only", "Random letter"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a lesson choice:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int item) {
                    	int choice = -1;
                    	int loopNumber = -1;
                    	boolean needsPick = false;
                    	switch (item)
                    	{
                    	case LESSON_ALL:
                    		choice = LESSON_ALL;
                    		loopNumber = 0;
                    		break;
                    	case LESSON_ONE:
                    		choice = LESSON_ONE;
                    		needsPick = true;
                    		break;
                    	case LESSON_RANDOM:
                    		choice = LESSON_RANDOM;
                    		break;
                		default:
                			choice = -1;
                			break;
                    	}
                        
                    	if(needsPick)
                    	{
                    		Intent intent = new Intent(getBaseContext(), LessonPickerActivity.class);
                        	intent.putExtra("LESSON_ID", choice);
                        	intent.putExtra("LOOP_NUMBER", loopNumber);
                        	intent.putExtra("IS_CAPITAL", false);
                            startActivity(intent);
                    	}
                    	else
                    	{
                    		Intent intent = new Intent(getBaseContext(), LowerLessonActivity.class);
                        	intent.putExtra("LESSON_ID", choice);
                        	intent.putExtra("LOOP_NUMBER", loopNumber);
                        	intent.putExtra("LETTER_PICK", -1);
                            startActivity(intent);
                    	}
                    } 
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    
    private void SetUpperLessonButton()
    {
    	mLessonBtn = (ImageButton) findViewById(R.id.ucl);
        mLessonBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            	final CharSequence[] items = {
                        "All letters in ABC order", "One letter only", "Random letter"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a lesson choice:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int item) {
                    	int choice = -1;
                    	int loopNumber = -1;
                    	boolean needsPick = false;
                    	switch (item)
                    	{
                    	case LESSON_ALL:
                    		choice = LESSON_ALL;
                    		loopNumber = 0;
                    		break;
                    	case LESSON_ONE:
                    		choice = LESSON_ONE;
                    		needsPick = true;
                    		break;
                    	case LESSON_RANDOM:
                    		choice = LESSON_RANDOM;
                    		break;
                		default:
                			choice = -1;
                			break;
                    	}
                        
                    	if(needsPick)
                    	{
                    		Intent intent = new Intent(getBaseContext(), LessonPickerActivity.class);
                        	intent.putExtra("LESSON_ID", choice);
                        	intent.putExtra("LOOP_NUMBER", loopNumber);
                        	intent.putExtra("IS_CAPITAL", true);
                            startActivity(intent);
                    	}
                    	else
                    	{
                    		Intent intent = new Intent(getBaseContext(), UpperLessonActivity.class);
                        	intent.putExtra("LESSON_ID", choice);
                        	intent.putExtra("LOOP_NUMBER", loopNumber);
                        	intent.putExtra("LETTER_PICK", -1);
                            startActivity(intent);
                    	}
                    } 
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
