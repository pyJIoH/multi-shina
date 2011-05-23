package com.pyjioh.activity;

import com.pyjioh.R;
import com.pyjioh.core.AsyncContentLoader;
import com.pyjioh.core.ErrorHandler;
import com.pyjioh.step.StepContext;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MultiShinaItemList extends ListActivity {

	private StepContext stepContext;
	private ErrorHandler errorHandler; 

	private OnItemClickListener viewOnClick = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			String itemCaption = "";
			TextView text = (TextView) view.findViewById(android.R.id.text1);
			if (text != null)
				itemCaption = text.getText().toString();
			
			Toast.makeText(getApplicationContext(), itemCaption,
					Toast.LENGTH_SHORT).show();

			stepContext.selectItem(itemCaption);
			startActivity(new Intent(MultiShinaItemList.this,
					MultiShinaItemList.class));
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multi_shina_item_list);

		init();
		refreshCaption();
		refreshContent();
	}

	private void init() {
		stepContext = StepContext.getInstance();
		errorHandler = new ErrorHandler();
		
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(viewOnClick);
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