package com.pyjioh.step;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class WheelSizeList extends BaseSizeList {

	public WheelSizeList(DetailItem item) {
		super(item);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void next(DetailItem item) {
		getStepContext().setStep(new WheelCatalog(item));
	}

	@Override
	public int getCaptionId() {
		return R.string.wheel_size;
	}

	@Override
	public String getTypeSizeName() {
		return "wheels";
	}

}
