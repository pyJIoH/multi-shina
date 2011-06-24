package com.pyjioh.activity;

import com.pyjioh.R;

import android.os.Bundle;

public class DetailItemActivity extends BaseLoaderActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_item);
		initializeAndLoad();
	}
}