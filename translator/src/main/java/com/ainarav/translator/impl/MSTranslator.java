package com.ainarav.translator.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ainarav.translator.AbstractGetMethodTranslator;
import com.ainarav.translator.Translator;
import com.ainarav.translator.util.ParamsStringBuilder;

/**
 * 
 * MS Translator API-based {@link Translator}
 * 
 * @author ainar
 *
 */
public class MSTranslator extends AbstractGetMethodTranslator {

	private static final String API_NAME = "MS Translator";
	private static final String API_URL = "https://api.microsofttranslator.com/V2/Http.svc/Translate";
	private static final String API_KEY = "c15b5288363a46f8a122694e0c4cd909";

	@Override
	protected void setRequestProperties(URLConnection connection) {
		super.setRequestProperties(connection);
		connection.setRequestProperty("Ocp-Apim-Subscription-Key", API_KEY);
	}

	@Override
	protected String buildParamsString(String text, String sourceLang, String targetLang)
			throws UnsupportedEncodingException {
		ParamsStringBuilder builder = new ParamsStringBuilder();
		builder.addParam("text", text);
		builder.addParam("to", targetLang);
		if (sourceLang != null && !"".equals(sourceLang.trim())) {
			builder.addParam("from", sourceLang);
		}
		return builder.build();
	}

	@Override
	protected String readResponse(InputStream response) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(response);
        Element element;
		if ((element = document.getDocumentElement()) != null) {
			return element.getTextContent();
		}
		return null;
	}

	@Override
	protected String getApiName() {
		return API_NAME;
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

}
