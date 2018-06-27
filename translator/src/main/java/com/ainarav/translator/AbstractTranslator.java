package com.ainarav.translator;

import static com.ainarav.translator.TranslatorHelper.CHARSET;
import static com.ainarav.translator.TranslatorHelper.buildParamsString;
import static com.ainarav.translator.TranslatorHelper.checkArgs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

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
			URLConnection connection = initConnection();
			Map<String, String> paramsMap = buildParamsMap(text, sourceLang, targetLang);
			String paramString = buildParamsString(paramsMap);
			try (OutputStream output = connection.getOutputStream()) {
				output.write(paramString.getBytes(CHARSET));
			}
			try (InputStream response = connection.getInputStream()) {
				return readResponse(response);
			}
		} catch (IOException e) {
			throw new TranslationException(getAPIName(), e);
		}
	}

	protected URLConnection initConnection() throws IOException {
		URLConnection connection = new URL(getUrl()).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Charset", CHARSET);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
		return connection;
	}

	protected abstract Map<String, String> buildParamsMap(String text, String sourceLang, String targetLang);

	protected abstract String readResponse(InputStream response) throws IOException;

	protected abstract String getAPIName();

	protected abstract String getUrl();

}
