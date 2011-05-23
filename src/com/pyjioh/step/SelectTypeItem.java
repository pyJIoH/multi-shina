package com.pyjioh.step;

import java.util.Arrays;
import java.util.List;

import com.pyjioh.R;
import com.pyjioh.core.DetailItem;

public class SelectTypeItem extends Step {
	
	private static final String TYRE_STEP = "Tyres"; //TODO load from resources
	private static final String WHEEL_STEP = "Wheels";
	private String tyresWheelsStepUrl; 
	
	public SelectTypeItem(DetailItem item) {
		super(item);
		tyresWheelsStepUrl = item.getLink();
	}

	public List<String> getCaptionList() {
		return Arrays.asList(new String[] {TYRE_STEP, WHEEL_STEP});
	}

	@Override
	public void loadContent() {
		DetailItem item = new DetailItem();
		item.setCaption(TYRE_STEP);
		item.setLink(tyresWheelsStepUrl);
		getItemsList().add(item);
		
		item = new DetailItem();
		item.setCaption(WHEEL_STEP);
		item.setLink(tyresWheelsStepUrl);
		getItemsList().add(item);
	}

	@Override
	public int getCaptionId() {
		return R.string.select_type_step;
	}

	@Override
	public void next(DetailItem item) {
		if (item.getCaption().equals(TYRE_STEP))
			getStepContext().setStep(new TyreSizeList(item));
		else if (item.getCaption().equals(WHEEL_STEP))
			getStepContext().setStep(new WheelSizeList(item));
	}

}
