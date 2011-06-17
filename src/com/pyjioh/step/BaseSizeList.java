package com.pyjioh.step;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.pyjioh.core.DetailItem;

public abstract class BaseSizeList extends Step {

	public BaseSizeList(DetailItem item) {
		super(item);
	}

	public abstract String getTypeSizeName();

	@Override
	public void loadContent() throws ClientProtocolException, IOException,
			ParserConfigurationException, SAXException {
		String xml = getXmlSource();
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));

		Document doc = docBuilder.parse(is);
		NodeList tyres = doc.getElementsByTagName(getTypeSizeName());

		Element element;
		if (tyres.getLength() > 0) {
			element = (Element) tyres.item(0);

			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				element = (Element) nodes.item(i);

				DetailItem item = new DetailItem();
				item.setCaption(element.getFirstChild().getNodeValue());
				item.setImageUrl(element.getAttribute("img"));
				item.setLink(element.getAttribute("link"));
				item.setPrice(element.getAttribute("price"));
				getItemsList().add(item);
			}
		}
	}
}
