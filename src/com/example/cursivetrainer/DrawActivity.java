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
import android.graphics.Color;
import android.os.Bundle;
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
    RelativeLayout mMyLayout;
    
    public final static int DRAWVIEW_ID = 10;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getViewID());
		mMyLayout = (RelativeLayout) findViewById(getLayoutID());
		
		setLayout();
    }
	
	protected abstract int getLayoutID();
	protected abstract int getViewID();
	
	protected void setLayout()
	{
		mDrawView = new DrawView(this);
		mDrawView.setId(DRAWVIEW_ID);
		mMyLayout.addView(mDrawView);
        
        createResetButton();
        
        setContentView(mMyLayout);
        mDrawView.requestFocus();
	}
	
	protected void createResetButton()
	{
		Button b = new Button(this);
        b.setText("Reset");
        LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(mMyLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(mMyLayout.ALIGN_PARENT_LEFT);
        mMyLayout.addView(b, lp);
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
