package com.pyjioh.core;

import android.util.Log;

public class ErrorHandler {

	public void error(Throwable t) {
		Log.d("MULTISHINA", t.getMessage());
//		Toast.makeText(appContext, t.getMessage(),
//				Toast.LENGTH_SHORT).show();
	}
}
