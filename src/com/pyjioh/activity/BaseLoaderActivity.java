package com.pyjioh.activity;

import com.pyjioh.R;
import com.pyjioh.core.AsyncContentLoader;
import com.pyjioh.core.ErrorHandler;
import com.pyjioh.step.StepContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;

public abstract class BaseLoaderActivity extends Activity {

	protected StepContext stepContext;
	private ErrorHandler errorHandler; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item_view);

		init();
		refreshCaption();
		refreshContent();
	}

	protected void init() {
		stepContext = StepContext.getInstance();
		errorHandler = new ErrorHandler();
	}

	protected void refreshContent() {
		new AsyncContentLoader(this).execute(errorHandler);
	}

	protected void refreshCaption() {
		TextView captionText = (TextView) findViewById(R.id.captionText);
		if (captionText != null)
			captionText.setText(stepContext.getCaptionId());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			stepContext.back();
		return super.onKeyDown(keyCode, event);
	}
	
	public ListView getListView() {
		return null;
	}

}
