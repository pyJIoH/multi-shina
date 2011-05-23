package com.pyjioh.adapter;

import java.util.List;

import com.pyjioh.core.DetailItem;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CaptionItemArrayAdapter extends ArrayAdapter<DetailItem> {

	private final int resourceId;

	public CaptionItemArrayAdapter(Context context, int textViewResourceId,
			List<DetailItem> objects) {
		super(context, textViewResourceId, objects);

		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DetailItem item = getItem(position);
		if (item == null) 
			return null;

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view;
		if (convertView == null) {
			view = inflater.inflate(resourceId, null);
		} else {
			view = convertView;
		}
		
		TextView textView = (TextView) view.findViewById(R.id.text1);
		if (textView != null)
			textView.setText(item.getCaption());

		return view;
	}

}
