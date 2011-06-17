package com.pyjioh.activity;

import com.pyjioh.R;
import com.pyjioh.core.AsyncContentLoader;
import com.pyjioh.core.ErrorHandler;
import com.pyjioh.step.StepContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class DetailItemActivity extends Activity {

	private StepContext stepContext;
	private ErrorHandler errorHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_item);

		init();
		refreshCaption();
		refreshContent();
	}

	private void init() {
		stepContext = StepContext.getInstance();
		errorHandler = new ErrorHandler();
	}

	private void refreshContent() {
		new AsyncContentLoader(this).execute(errorHandler);
	}

	private void refreshCaption() {
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

}