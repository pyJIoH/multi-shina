package com.pyjioh.core;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.pyjioh.R;
import com.pyjioh.step.StepContext;

public class AsyncContentLoader extends AsyncTask<ErrorHandler, Void, List<DetailItem>> {
	private ProgressDialog mProgressDlg;
	private StepContext stepContext = StepContext.getInstance();
	private ListActivity currentActivity;

	public AsyncContentLoader(ListActivity activity) {
		currentActivity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		mProgressDlg = ProgressDialog.show(currentActivity, null,
				currentActivity.getText(R.string.msg_loading));
		mProgressDlg.setIndeterminate(true);
		mProgressDlg.setCancelable(true);
		mProgressDlg.show();
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
		currentActivity.setListAdapter(stepContext.makeAdapter(currentActivity,
				R.layout.single_item_caption_price, items));
		
		mProgressDlg.dismiss();
	};
}
