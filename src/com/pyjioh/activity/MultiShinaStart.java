package com.pyjioh.activity;

import com.pyjioh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
import android.view.View;

public class MultiShinaStart extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater menuInflater = getMenuInflater();
//		menuInflater.inflate(R.menu.menu, menu);
//		return super.onCreateOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.mycart:
//			return true;
//		}
//		return false;
//	}

	public void onAutoSelectionClick(View v) {
		startActivity(new Intent(this, MultiShinaItemList.class));
	}

	public void onAboutClick(View v) {
		startActivity(new Intent(this, About.class));
	}

}
