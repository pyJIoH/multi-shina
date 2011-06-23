package com.pyjioh.activity;

import com.pyjioh.R;
import com.pyjioh.core.AsyncContentLoader;
import com.pyjioh.core.ErrorLogger;
import com.pyjioh.step.StepContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;

public abstract class BaseLoaderActivity extends Activity {

	protected StepContext stepContext;
	private ErrorLogger errorLogger;

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
		errorLogger = new ErrorLogger();
	}

	protected void refreshContent() {
		new AsyncContentLoader(this).execute(errorLogger);
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

	public void showErrorMessage() {
		OnClickListener onOkClick = new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		};

		new AlertDialog.Builder(this).setMessage(R.string.msg_error_loading)
				.setNeutralButton("Ok", onOkClick).show();
	}

}
