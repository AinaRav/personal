package com.ainarav.translator;

import java.net.URLConnection;

import com.ainarav.translator.exception.TranslationException;

/**
 * The request method strategy
 * 
 * @author ainar
 *
 */
public interface RequestMethodStrategy {

	/**
	 * Connects to the API, builds the requests params (url, parameters, header) and
	 * sends the request
	 * 
	 * @param text
	 *            the text to translate. Required.
	 * @param sourceLang
	 *            the source language. Optional.
	 * @param targetLang
	 *            the target language. Required.
	 * @return the {@link URLConnection} to the API
	 * @throws TranslationException
	 */
	URLConnection sendRequest(String text, String sourceLang, String targetLang) throws TranslationException;

}
