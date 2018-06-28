package com.ainarav.translator.util;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;

import java.io.UnsupportedEncodingException;

public class ParamsStringBuilder {

	private final StringBuilder sb;
	private boolean first;
	
	public ParamsStringBuilder() {
		sb = new StringBuilder();
		first = true;
	}
	
	public ParamsStringBuilder addParam(String key, String value) throws UnsupportedEncodingException {
		if (!first) {
			sb.append('&');
		}
		first = false;
		sb.append(key).append('=').append(java.net.URLEncoder.encode(value, CHARSET));
		return this;
	}
	
	public String build() {
		return sb.toString();
	}

}
