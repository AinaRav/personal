package com.ainarav.translator;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Webservice-based translator skeleton
 * 
 * @author ainar
 *
 */
public abstract class AbstractTranslator implements Translator {

	protected static final String CHARSET = UTF_8.name();

	private static final int MAX_TEXT_LENGTH = 10000;

	@Override
	public String translate(String text, String sourceLang, String targetLang) {
		checkArgs(text, targetLang);
		try {
			URLConnection connection = initConnection();
			String query = buildQuery(text, sourceLang, targetLang);
			try (OutputStream output = connection.getOutputStream()) {
				output.write(query.getBytes(CHARSET));
			}
			return readResponse(connection);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract String getUrl();

	protected abstract String buildQuery(String text, String sourceLang, String targetLang)
			throws UnsupportedEncodingException;

	protected abstract String readResponse(URLConnection connection) throws IOException;

	protected URLConnection initConnection() throws MalformedURLException, IOException {
		URLConnection connection = new URL(getUrl()).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Charset", CHARSET);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
		return connection;
	}

	private static void checkArgs(String text, String targetLang) {
		if (text == null || "".equals(text.trim())) {
			throw new IllegalArgumentException("text argument is mandatory");
		}
		if (text.length() > MAX_TEXT_LENGTH) {
			throw new IllegalArgumentException(format("text length must not exceed %s characters", MAX_TEXT_LENGTH));
		}
		if (targetLang == null || "".equals(targetLang.trim())) {
			throw new IllegalArgumentException("targetLang argument is mandatory");
		}
	}

}
