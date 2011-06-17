package com.pyjioh.step;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class Year extends Step {

	public Year(DetailItem item) {
		super(item);
		// TODO Auto-generated constructor stub
	}

	public int getCaptionId() {
		return R.string.year_step;
	}

	@Override
	public void next(DetailItem item) {
		getStepContext().setStep(new SelectTypeItem(item));
	}

}
