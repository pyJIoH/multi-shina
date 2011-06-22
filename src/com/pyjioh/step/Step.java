package com.pyjioh.step;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.pyjioh.R;
import com.pyjioh.activity.BaseLoaderActivity;
import com.pyjioh.activity.ListViewActivity;
import com.pyjioh.adapter.CaptionArrayAdapter;
import com.pyjioh.core.DetailItem;
import com.pyjioh.core.ErrorHandler;
import com.pyjioh.internet.WebManager;

/**
 * Use <b>State</b> pattern for application wizard style
 * */

public abstract class Step {
	private List<DetailItem> items = new LinkedList<DetailItem>();

	private DetailItem item;

	private static final Pattern itemPattern = Pattern
			.compile("<\\?xml version=\"1\\.0\" encoding=\"windows-1251\"\\?>(.*)</clientXML>");

	public Step(DetailItem item) {
		this.item = item;
	}

	protected String getXmlSource() throws ClientProtocolException, IOException {
		StringBuffer xml = new StringBuffer();
		Matcher matcher = getRegExpPattern().matcher(getStepHtmlSource());
		while (matcher.find())
			xml.append(matcher.group(0));
		return WebManager.replaceBrTag(xml.toString());
	}

	private String getStepHtmlSource() throws ClientProtocolException,
			IOException {
		return WebManager.getPageSource(item.getLink());
	}

	private Pattern getRegExpPattern() {
		return itemPattern;
	}

	public List<String> getCaptionList() {
		LinkedList<String> captions = new LinkedList<String>();
		for (DetailItem item : items) {
			captions.add(item.getCaption());
		}
		return captions;
	}

	public List<DetailItem> getItemsList() {
		return items;
	}

	public void loadContent() throws ClientProtocolException, IOException,
			ParserConfigurationException, SAXException {
		String xml = getXmlSource();
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource source = new InputSource();
		source.setCharacterStream(new StringReader(xml));

		Document doc = docBuilder.parse(source);
		NodeList nodes = doc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			DetailItem item = new DetailItem();
			item.setCaption(element.getFirstChild().getNodeValue());
			item.setImageUrl(element.getAttribute("img"));
			item.setLink(element.getAttribute("link"));
			item.setPrice(element.getAttribute("price"));

			if (isNeedDownloadImage() && item.getImageUrl() != null)
				item.setBitmap(downloadBitmap(item.getImageUrl()));
			items.add(item);
		}
	}

	protected boolean isNeedDownloadImage() {
		return false;
	}

	private Bitmap downloadBitmap(String url) {
		final DefaultHttpClient client = new DefaultHttpClient();
		final HttpGet getRequest = new HttpGet(url);

		try {
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(ErrorHandler.LOG_TAG, "Error " + statusCode
						+ " while retrieving bitmap from " + url);
				return null;
			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					inputStream = entity.getContent();
					final Bitmap bitmap = BitmapFactory
							.decodeStream(inputStream);
					return bitmap;
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
			getRequest.abort();
			Log.w(ErrorHandler.LOG_TAG, "Error while retrieving bitmap from "
					+ url, e);
		} finally {
			// if (client != null) {
			// client.close();
			// }
		}
		return null;
	}

	public DetailItem getItem(String itemName) {
		for (DetailItem item : items) {
			if (item.getCaption().equals(itemName))
				return item;
		}
		return null;
	}

	public StepContext getStepContext() {
		return StepContext.getInstance();
	}

	abstract public void next(DetailItem item);

	abstract public int getCaptionId();

	protected ArrayAdapter<DetailItem> makeAdapter(Context context,
			int textViewResourceId, List<DetailItem> items) {
		return new CaptionArrayAdapter(context, textViewResourceId, items);
	}

	public Class<?> getActivityClass() {
		return ListViewActivity.class;
	}

	public void afterLoadContent(Activity activity, List<DetailItem> items) {
		((BaseLoaderActivity) activity).getListView().setAdapter(makeAdapter(activity,
				R.layout.single_item_caption_price, items)); 
	}

}
