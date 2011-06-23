package com.pyjioh.step;

import java.util.List;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyjioh.R;

import com.pyjioh.activity.DetailItemActivity;
import com.pyjioh.core.DetailItem;

public class ItemInfo extends Step {

	public ItemInfo(DetailItem item) {
		super(item);
	}

	@Override
	public void next(DetailItem item) {
		// to do nothing
	}

	@Override
	public int getCaptionId() {
		return R.string.detail_item_step;
	}

	@Override
	protected void downloadBitmapIfNeed(DetailItem item) {
		if (item.getImageUrl() != null)
			item.setBitmap(downloadBitmap(item.getImageUrl()));
	}
	
	@Override
	public Class<?> getActivityClass() {
		return DetailItemActivity.class;
	}

	@Override
	public void afterLoadContent(Activity activity, List<DetailItem> items) {
		DetailItem item = items.get(0);
		if (item != null) {
			ImageView imageItem = (ImageView) activity
					.findViewById(R.id.imageItem);
			
			if (item.getBitmap() == null)
				imageItem.setImageResource(R.drawable.noimage);
			else
				imageItem.setImageBitmap(item.getBitmap());

			TextView textInfo = (TextView) activity.findViewById(R.id.textInfo);
			if (textInfo != null)
				textInfo.setText(item.getCaption());

			TextView textPrice = (TextView) activity
					.findViewById(R.id.textPrice);
			if (textPrice != null)
				textPrice.setText(item.getPrice());
		}

	}
}
