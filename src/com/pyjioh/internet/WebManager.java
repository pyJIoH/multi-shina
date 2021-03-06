package com.pyjioh.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebManager {
	private static final String TAG_BR = "<br>";
	
	private static String getValidUrl(String url) {
		return url.replaceAll(" ", "%20"); 
	} 
	
	public static String getPageSource(String url)
			throws ClientProtocolException, IOException {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(getValidUrl(url));
		HttpResponse response = client.execute(request);

		InputStream in = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder source = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null)
			source.append(line);

		in.close();

		return source.toString();
	}
	
	public static String replaceBrTag(String xml) {
		return xml.replaceAll(TAG_BR, "\n");
	}

}