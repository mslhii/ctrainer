/**
 * UpperDrawActivity.java
 * 
 * Subclassed from DrawActivity
 * 
 * TODO: Merge upper case activity class with this lower case one? Improve UI and polish it
 * 
 * @author Michael Hii
 */


package com.example.cursivetrainer;

import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;

public class UpperDrawActivity extends DrawActivity {
	
	private static final String TAG = "DrawView";
    
    DrawView m_drawView;
    RelativeLayout m_myLayout;
    
    public final static int DRAWVIEW_ID = 10;
	
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

	@Override
	protected int getLayoutID() {
		return R.id.capital_drawview_layout;
	}

	@Override
	protected int getViewID() {
		return R.layout.capital_draw_layout;
	}

}
