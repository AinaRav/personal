package com.ainarav.translator.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.ainarav.translator.RequestMethodStrategy;
import com.ainarav.translator.exception.TranslationException;

/**
 * GET request method strategy
 * 
 * @author ainar
 *
 */
public class GetRequestMethodStrategy implements RequestMethodStrategy {

	private AbstractTranslator translator;

	/**
	 * 
	 * Constructs the request method strategy for a given translator
	 *
	 * @param translator
	 *            the translator for which apply this strategy
	 */
	public GetRequestMethodStrategy(AbstractTranslator translator) {
		this.translator = translator;
	}

	@Override
	public URLConnection sendRequest(String text, String sourceLang, String targetLang) throws TranslationException {
		try {
			URLConnection connection = new URL(
					translator.getApiUrl() + "?" + translator.buildParamsString(text, sourceLang, targetLang))
							.openConnection();
			translator.setRequestProperties(connection);
			return connection;
		} catch (IOException e) {
			throw new TranslationException(translator.getApiName(), e);
		}
	}

}
