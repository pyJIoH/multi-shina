package com.pyjioh.step;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class Brand extends Step {
	
	private final static String START_URL = "http://multi-shina.ru/";
	private final static DetailItem startItem = new DetailItem();
	
	public Brand() {
		super(startItem);
		startItem.setLink(START_URL);
	}

	public int getCaptionId() {
		return R.string.brand_step;
	}

	public void next(DetailItem item) {
		getStepContext().setStep(new Model(item));
	}

}
