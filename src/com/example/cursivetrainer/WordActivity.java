/**
 * WordActivity.java
 * 
 * Special Activity to display word layout, subclassed from DrawActivity
 * TODO: Add list of words to be generated?
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import android.os.Bundle;
import android.view.Menu;

public class WordActivity extends DrawActivity {
	
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
		return R.id.wordview_layout;
	}

	@Override
	protected int getViewID() {
		return R.layout.word_layout;
	}

}
