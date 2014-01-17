/**
 * LowerDrawActivity.java
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

public class LowerDrawActivity extends DrawActivity {

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
		return R.id.drawview_layout;
	}

	@Override
	protected int getViewID() {
		return R.layout.draw_layout;
	}

}
