package com.ainarav.translator.impl;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;
import static com.ainarav.translator.util.TranslatorHelper.checkArgs;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import com.ainarav.translator.RequestMethod;
import com.ainarav.translator.RequestMethodStrategy;
import com.ainarav.translator.Translator;
import com.ainarav.translator.exception.TranslationException;

/**
 * {@link Translator} skeleton
 *
 * @author ainar
 */
public abstract class AbstractTranslator implements Translator {

	@Override
	public String translate(String text, String sourceLang, String targetLang) {
		checkArgs(text, targetLang);
		URLConnection connection = getRequestMethodStrategy().sendRequest(text, sourceLang, targetLang);
		try (InputStream response = connection.getInputStream()) {
			return readResponse(response);
		} catch (IOException e) {
			throw new TranslationException(getApiName(), e);
		}
	}

	protected void setRequestProperties(URLConnection connection) {
		connection.setRequestProperty("Accept-Charset", CHARSET);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
	}

	protected abstract String buildParamsString(String text, String sourceLang, String targetLang)
			throws UnsupportedEncodingException;

	protected abstract String readResponse(InputStream response) throws TranslationException;

	protected abstract String getApiName();

	protected abstract String getApiUrl();

	protected abstract RequestMethod getRequestMethod();

	private RequestMethodStrategy getRequestMethodStrategy() {
		switch (getRequestMethod()) {
		case GET:
			return new GetRequestMethodStrategy(this);
		case POST:
			return new PostRequestMethodStrategy(this);
		default:
			throw new TranslationException(
					String.format("Unknown request method %s for % API", getRequestMethod().name(), getApiName()));
		}
	}

}
