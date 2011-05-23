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
		item.setCaption("��������������:\n�����: Replica\n������: Peugeot (PG6)\n������: 7.00��\n�����: 39.00\n�������: 16'\n���:\n����: silver");
		getItemsList().add(item);		
	}

}
