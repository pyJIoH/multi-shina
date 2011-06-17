package com.pyjioh.step;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class TyreSizeList extends BaseSizeList {

	public TyreSizeList(DetailItem item) {
		super(item);
	}

	@Override
	public void next(DetailItem item) {
		getStepContext().setStep(new TyreCatalog(item));
	}

	@Override
	public int getCaptionId() {
		return R.string.tyre_size;
	}

	@Override
	public String getTypeSizeName() {
		return "tyres";
	}

}
