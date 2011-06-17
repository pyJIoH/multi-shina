package com.pyjioh.step;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;

import com.pyjioh.core.DetailItem;

public class StepContext {
	private List<Step> steps = new LinkedList<Step>();

	private Step currentStep;

	private StepContext() {
		setStep(new Brand());
	}

	private static class LazyHolder {
		private static final StepContext INSTANCE = new StepContext();
	}

	public static final StepContext getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setStep(Step step) {
		steps.add(step);
		currentStep = step;
	}

	public List<DetailItem> getItemsList() {
		return currentStep.getItemsList();
	}

	public int getCaptionId() {
		return currentStep.getCaptionId();
	}

	public void loadContent() throws ClientProtocolException, IOException,
			ParserConfigurationException, SAXException {
		currentStep.loadContent();
	}

	public void selectItem(Activity activity, String itemCaption) {
		DetailItem item = currentStep.getItem(itemCaption);
		currentStep.next(item);
		activity.startActivity(new Intent(activity, currentStep.getActivityClass()));
	}

	public void back() {
		steps.remove(currentStep);
		if (steps.size() > 0)
			currentStep = steps.get(steps.size() - 1);
	}

	public void afterLoadContent(Activity activity, List<DetailItem> items) {
		currentStep.afterLoadContent(activity, items);
	}

}
