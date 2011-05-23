package com.pyjioh.step;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.pyjioh.adapter.CaptionItemArrayAdapter;
import com.pyjioh.core.DetailItem;
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
		return xml.toString();
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
			
			items.add(item);
		}
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

	public ArrayAdapter<DetailItem> makeAdapter(Context context,
			int textViewResourceId, List<DetailItem> items) {
		return new CaptionItemArrayAdapter(context, textViewResourceId, items);
	}

}
