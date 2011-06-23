package com.pyjioh.core;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.pyjioh.R;
import com.pyjioh.activity.BaseLoaderActivity;
import com.pyjioh.step.StepContext;

public class AsyncContentLoader extends AsyncTask<ErrorLogger, Void, List<DetailItem>> {
	private ProgressDialog mProgressDlg;
	private StepContext stepContext = StepContext.getInstance();
	private BaseLoaderActivity currentActivity;
	private boolean hasError;

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
	
	public AsyncContentLoader(BaseLoaderActivity activity) {
		currentActivity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		hasError = false;
		showProgressDialog();
	}

	@Override
	protected List<DetailItem> doInBackground(ErrorLogger... params) {
		ErrorLogger errorLogger = params[0];
		try {
			stepContext.loadContent();
		} catch (Throwable t) {
			hasError = true;
			errorLogger.error(t);
		}
		return stepContext.getItemsList();
	}

	@Override
	protected void onPostExecute(List<DetailItem> items) {
		if (hasError) {
			dismissProgressDialog();
			currentActivity.showErrorMessage();
		} else {
			stepContext.afterLoadContent(currentActivity, items);
			dismissProgressDialog();
		}
	}

}
