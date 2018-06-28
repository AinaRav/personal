package com.ainarav.translator.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.ainarav.translator.AbstractPostMethodTranslator;
import com.ainarav.translator.Translator;
import com.ainarav.translator.util.ParamsStringBuilder;

/**
 * Google Translate API-based {@link Translator}
 *
 * @author ainar
 *
 */
public class GoogleTranslator extends AbstractPostMethodTranslator {

	private static final String API_NAME = "Google Translate";
	private static final String API_URL = "https://www.googleapis.com/language/translate/v2/";
	private static final String API_KEY = "AIzaSyDH-0lb3TIHBPss6zZwFLrw6vzdzN9MkXo";

	@Override
	protected String buildParamsString(String text, String sourceLang, String targetLang)
			throws UnsupportedEncodingException {
		ParamsStringBuilder builder = new ParamsStringBuilder();
		builder.addParam("key", API_KEY);
		builder.addParam("q", text);
		builder.addParam("target", targetLang);
		if (sourceLang != null && !"".equals(sourceLang.trim())) {
			builder.addParam("source", sourceLang);
		}
		return builder.build();
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
	protected String getApiName() {
		return API_NAME;
	}

	@Override
	protected String getApiUrl() {
		return API_URL;
	}

}
