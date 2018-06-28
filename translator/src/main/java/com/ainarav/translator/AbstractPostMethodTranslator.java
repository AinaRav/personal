package com.ainarav.translator;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class AbstractPostMethodTranslator extends AbstractTranslator {

	@Override
	protected URLConnection initConnection(String text, String sourceLang, String targetLang) throws IOException {
		URLConnection connection = new URL(getApiUrl()).openConnection();
		connection.setDoOutput(true);
		setRequestProperties(connection);
		try (OutputStream output = connection.getOutputStream()) {
			output.write(buildParamsString(text, sourceLang, targetLang).getBytes(CHARSET));
		}
		return connection;
	}

}
