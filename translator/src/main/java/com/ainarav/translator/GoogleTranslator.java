package com.ainarav.translator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Google API-based {@link Translator}
 *
 * @author ainar
 *
 */
public class GoogleTranslator extends AbstractTranslator {

	private static final String API_NAME = "Google Translate";
	private static final String URL = "https://www.googleapis.com/language/translate/v2/";
	private static final String KEY = "AIzaSyDH-0lb3TIHBPss6zZwFLrw6vzdzN9MkXo";

	@Override
	protected Map<String, String> buildParamsMap(String text, String sourceLang, String targetLang) {
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("key", KEY);
		paramsMap.put("q", text);
		paramsMap.put("target", targetLang);
		if (sourceLang != null && !"".equals(sourceLang.trim())) {
			paramsMap.put("source", sourceLang);
		}
		return paramsMap;
	}


	@Override
	protected String readResponse(InputStream response) throws IOException {
		try (JsonReader rdr = Json.createReader(response)) {
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

	@Override
	protected String getAPIName() {
		return API_NAME;
	}

	@Override
	protected String getUrl() {
		return URL;
	}

}
