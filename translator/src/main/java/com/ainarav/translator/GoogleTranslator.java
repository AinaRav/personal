package com.ainarav.translator;

import static java.net.URLEncoder.encode;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Google API-based translator
 */
public class GoogleTranslator extends AbstractTranslator {

	private static final String URL = "https://www.googleapis.com/language/translate/v2/";
	private static final String KEY = "AIzaSyDH-0lb3TIHBPss6zZwFLrw6vzdzN9MkXo";

	@Override
	protected String getUrl() {
		return URL;
	}

	@Override
	protected String buildQuery(String text, String sourceLang, String targetLang) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append("key=").append(encode(KEY, CHARSET));
		sb.append("&q=").append(encode(text, CHARSET));
		sb.append("&target=").append(encode(targetLang, CHARSET));
		if (sourceLang != null && !"".equals(sourceLang.trim())) {
			sb.append("&source=").append(encode(sourceLang, CHARSET));
		}
		return sb.toString();
	}

	@Override
	protected String readResponse(URLConnection connection) throws IOException {
		try (InputStream response = connection.getInputStream(); JsonReader rdr = Json.createReader(response)) {
			JsonObject obj = rdr.readObject();
			JsonObject data = obj.getJsonObject("data");
			JsonArray translationArray = data.getJsonArray("translations");
			Collection<JsonObject> translations = translationArray.getValuesAs(JsonObject.class);
			if (!translations.isEmpty()) {
				return translations.iterator().next().getString("translatedText");
			}
		}
		return null;
	}

}
