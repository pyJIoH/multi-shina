package com.pyjioh.step;

import com.pyjioh.R;

import com.pyjioh.core.DetailItem;

public class ItemInfo extends Step {

	public ItemInfo(DetailItem item) {
		super(item);
	}

	@Override
	public void next(DetailItem item) {
		//to do nothing
	}

	@Override
	public int getCaptionId() {
		return R.string.detail_item_step;
	}
	
	@Override
	public void loadContent() {
		DetailItem item = new DetailItem();
		item.setCaption("Характеристики:\nБренд: Replica\nМодель: Peugeot (PG6)\nШирина: 7.00мм\nВылет: 39.00\nДиаметр: 16'\nТип:\nЦвет: silver");
		getItemsList().add(item);		
	}

}
