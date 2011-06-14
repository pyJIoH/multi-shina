package com.pyjioh.step;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.pyjioh.R;
import com.pyjioh.adapter.CaptionPriceArrayAdapter;
import com.pyjioh.core.DetailItem;

public class WheelCatalog extends Step {

	public WheelCatalog(DetailItem item) {
		super(item);
	}

	@Override
	public void next(DetailItem item) {
		getStepContext().setStep(new ItemInfo(item));
	}

	@Override
	public int getCaptionId() {
		return R.string.wheel_catalog;
	}
	
	@Override
	protected ArrayAdapter<DetailItem> makeAdapter(Context context,
			int textViewResourceId, List<DetailItem> items) {

		return new CaptionPriceArrayAdapter(context, textViewResourceId, items);
	}
	
}
