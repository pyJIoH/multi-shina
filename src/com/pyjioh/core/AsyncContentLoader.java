package com.pyjioh.core;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.pyjioh.R;
import com.pyjioh.step.StepContext;

public class AsyncContentLoader extends AsyncTask<ErrorHandler, Void, List<DetailItem>> {
	private ProgressDialog mProgressDlg;
	private StepContext stepContext = StepContext.getInstance();
	private Activity currentActivity;

	private void showProgressDialog() {
		mProgressDlg = ProgressDialog.show(currentActivity, null,
				currentActivity.getText(R.string.msg_loading));
		mProgressDlg.setIndeterminate(true);
		mProgressDlg.setCancelable(true);
		mProgressDlg.show();
	}
	
	private void dismissProgressDialog() {
		mProgressDlg.dismiss();
	}
	
	public AsyncContentLoader(Activity activity) {
		currentActivity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		showProgressDialog();
	}

	@Override
	protected List<DetailItem> doInBackground(ErrorHandler... params) {
		ErrorHandler errorHandler = params[0];
		try {
			stepContext.loadContent();
		} catch (Throwable t) {
			errorHandler.error(t);
		}
		return stepContext.getItemsList();
	}

	@Override
	protected void onPostExecute(List<DetailItem> items) {
		stepContext.afterLoadContent(currentActivity, items);
		dismissProgressDialog();
	}

}
