package com.pyjioh.step;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class Model extends Step {

	public Model(DetailItem item) {
		super(item);
		// TODO Auto-generated constructor stub
	}

	public int getCaptionId() {
		return R.string.model_step;
	}

	@Override
	public void next(DetailItem item) {
		getStepContext().setStep(new Year(item));
	}


}
