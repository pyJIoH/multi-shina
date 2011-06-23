package com.pyjioh.core;

import android.util.Log;

public class ErrorLogger {
	public static final String LOG_TAG = "MULTISHINA";
	
	public void error(Throwable t) {
		Log.d(LOG_TAG, t.getMessage());
	}
}
