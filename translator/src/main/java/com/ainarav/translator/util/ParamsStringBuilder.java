package com.ainarav.translator.util;

import static com.ainarav.translator.util.TranslatorHelper.CHARSET;

import java.io.UnsupportedEncodingException;

/**
 * Builds a form url encoded string: <code>key1=value1&key2=value2</code>
 * 
 * @author ainar
 *
 */
public class ParamsStringBuilder {

	private final StringBuilder sb;
	private boolean first;

	/**
	 * Initializes the builder
	 */
	public ParamsStringBuilder() {
		sb = new StringBuilder();
		first = true;
	}

	/**
	 * Adds a key and its value to the string
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the param string in form url encoded format
	 * @throws UnsupportedEncodingException
	 */
	public ParamsStringBuilder addParam(String key, String value) throws UnsupportedEncodingException {
		if (!first) {
			sb.append('&');
		}
		first = false;
		sb.append(key).append('=').append(java.net.URLEncoder.encode(value, CHARSET));
		return this;
	}

	/**
	 * Builds the final string
	 * 
	 * @return the final string
	 */
	public String build() {
		return sb.toString();
	}

}
