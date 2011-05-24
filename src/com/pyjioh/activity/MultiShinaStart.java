package com.pyjioh.activity;

import com.pyjioh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MultiShinaStart extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}
	
	public void onClick(View v) {
		startActivity(new Intent(this, MultiShinaItemList.class));
	}

}
