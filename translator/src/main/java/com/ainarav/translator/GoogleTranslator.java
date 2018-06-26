package com.ainarav.translator;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Google API-based translator
 */
public class GoogleTranslator implements Translator {

	private static final String URL = "https://www.googleapis.com/language/translate/v2/";
	private static final String KEY = "AIzaSyDH-0lb3TIHBPss6zZwFLrw6vzdzN9MkXo";
	private static final String CHARSET = UTF_8.name();

	@Override
	public String translate(String text, String sourceLang, String targetLang) {
		URLConnection connection;
		try {
			// connect
			connection = new URL(URL).openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", CHARSET);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=" + CHARSET);

			// call translate api
			String query = String.format("key=%s&source=%s&target=%s&q=%s",
					encode(KEY, CHARSET),
					encode(sourceLang, CHARSET),
					encode(targetLang, CHARSET),
					encode(text, CHARSET));

			try (OutputStream output = connection.getOutputStream()) {
				output.write(query.getBytes(CHARSET));
			}

			// read response
			try (InputStream response = connection.getInputStream();
					JsonReader rdr = Json.createReader(response)) {
				JsonObject obj = rdr.readObject();
				JsonObject data = obj.getJsonObject("data");
				JsonArray translationArray = data.getJsonArray("translations");
				Collection<JsonObject> translations = translationArray.getValuesAs(JsonObject.class);
				if (!translations.isEmpty()) {
					return translations.iterator().next().getString("translatedText");
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}
