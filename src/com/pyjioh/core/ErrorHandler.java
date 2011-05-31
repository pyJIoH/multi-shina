package com.pyjioh.core;

import android.util.Log;

public class ErrorHandler {
	private static final String LOG_TAG = "MULTISHINA";
	
	public void error(Throwable t) {
		Log.d(LOG_TAG, t.getMessage());
//		Toast.makeText(appContext, t.getMessage(),
//				Toast.LENGTH_SHORT).show();
	}
}
