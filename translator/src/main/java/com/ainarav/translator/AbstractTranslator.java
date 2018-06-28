package com.ainarav.translator;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;
import static com.ainarav.translator.util.TranslatorHelper.checkArgs;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
		try {
			URLConnection connection = initConnection(text, sourceLang, targetLang);
			try (InputStream response = connection.getInputStream()) {
				return readResponse(response);
			} catch (ParserConfigurationException | SAXException e) {
				throw new TranslationException(getApiName(), e);
			}
		} catch (IOException e) {
			throw new TranslationException(getApiName(), e);
		}
	}
	
	protected void setRequestProperties(URLConnection connection) {
		connection.setRequestProperty("Accept-Charset", CHARSET);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
	}

	protected abstract URLConnection initConnection(String text, String sourceLang, String targetLang) throws IOException;

	protected abstract String buildParamsString(String text, String sourceLang, String targetLang) throws UnsupportedEncodingException;

	protected abstract String readResponse(InputStream response)
			throws IOException, ParserConfigurationException, SAXException;

	protected abstract String getApiName();

	protected abstract String getApiUrl();

}
