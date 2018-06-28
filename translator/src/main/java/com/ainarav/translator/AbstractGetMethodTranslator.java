package com.ainarav.translator;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public abstract class AbstractGetMethodTranslator extends AbstractTranslator {

	@Override
	protected URLConnection initConnection(String text, String sourceLang, String targetLang) throws IOException {
		URLConnection connection = new URL(
				getApiUrl() + "?" + buildParamsString(text, sourceLang, targetLang)).openConnection();
		setRequestProperties(connection);
		return connection;
	}

}
