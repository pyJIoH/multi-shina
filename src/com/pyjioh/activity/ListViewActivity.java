package com.pyjioh.activity;

import com.pyjioh.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewActivity extends BaseLoaderActivity {

	private ListView listView;
	private OnItemClickListener viewOnClick = new OnItemClickListener() {
		
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			String itemCaption = "";
			TextView text = (TextView) view.findViewById(android.R.id.text1);
			if (text != null)
				itemCaption = text.getText().toString();
			
			Toast.makeText(getApplicationContext(), itemCaption,
					Toast.LENGTH_SHORT).show();

			stepContext.selectItem(ListViewActivity.this, itemCaption);
		}
		
	};

	protected void init() {
		super.init();
		listView = (ListView) findViewById(R.id.listView);
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(viewOnClick);
	};
	
	@Override
	public ListView getListView() {
		return listView;
	}
	
}