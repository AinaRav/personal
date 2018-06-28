package com.ainarav.translator.util;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.ainarav.translator.Translator;

/**
 * Helper class for {@link Translator}
 *
 * @author ainar
 *
 */
public final class TranslatorHelper {

	public static final String CHARSET = UTF_8.name();

	public static final int MAX_TEXT_LENGTH = 10000;

	private TranslatorHelper() {

	}

	/**
	 * Performs verifications on the parameters passed to the
	 * {@link Translator#translate(String, String, String)} method
	 *
	 * @param text
	 *            the text to translate. Must not be empty or null. Length must not
	 *            exceed {@link TranslatorHelper#MAX_TEXT_LENGTH}
	 * @param targetLang
	 *            the target language. Must not be empty or null.
	 */
	public static void checkArgs(String text, String targetLang) {
		if (text == null || "".equals(text.trim())) {
			throw new IllegalArgumentException("text is mandatory");
		}
		if (text.length() > MAX_TEXT_LENGTH) {
			throw new IllegalArgumentException(format("text length must not exceed %s characters", MAX_TEXT_LENGTH));
		}
		if (targetLang == null || "".equals(targetLang.trim())) {
			throw new IllegalArgumentException("targetLang is mandatory");
		}
	}

}
