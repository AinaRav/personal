package com.ainarav.translator.impl;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.ainarav.translator.RequestMethodStrategy;
import com.ainarav.translator.exception.TranslationException;

/**
 * POST request method strategy
 * 
 * @author ainar
 *
 */
public class PostRequestMethodStrategy implements RequestMethodStrategy {

	private final AbstractTranslator translator;

	/**
	 * 
	 * Constructs the request method strategy for a given translator
	 *
	 * @param translator
	 *            the translator for which apply this strategy
	 */
	public PostRequestMethodStrategy(AbstractTranslator translator) {
		this.translator = translator;
	}

	@Override
	public URLConnection sendRequest(String text, String sourceLang, String targetLang) throws TranslationException {
		URLConnection connection;
		try {
			connection = new URL(translator.getApiUrl()).openConnection();
		} catch (IOException e) {
			throw new TranslationException(translator.getApiName(), e);
		}
		connection.setDoOutput(true);
		translator.setRequestProperties(connection);
		try (OutputStream output = connection.getOutputStream()) {
			output.write(translator.buildParamsString(text, sourceLang, targetLang).getBytes(CHARSET));
		} catch (IOException e) {
			throw new TranslationException(translator.getApiName(), e);
		}
		return connection;
	}

}
