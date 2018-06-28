package com.ainarav.translator;

import static com.ainarav.translator.util.TranslatorHelper.MAX_TEXT_LENGTH;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.ainarav.translator.exception.TranslationException;
import com.ainarav.translator.impl.GoogleTranslator;
import com.ainarav.translator.impl.MSTranslator;

/**
 * {@link Translator} test skeleton
 *
 * @author ainar
 *
 */
public class TranslatorTest {

	private static final String TEXT_TO_TRANSLATE = "Bonjour";
	private static final String TRANSLATED_TEXT = "Hello";
	private static final String SOURCE_LANG = "fr";
	private static final String TARGET_LANG = "en";

	private Collection<Translator> translators;

	@Before
	public void setUpTranslators() {
		translators = new ArrayList<>();
		translators.add(new GoogleTranslator());
		translators.add(new MSTranslator());
	}

	@Test
	public void testTranslateWithCorrectParametersEndsOK() {
		translators.forEach(translator -> assertEquals(TRANSLATED_TEXT,
				translator.translate(TEXT_TO_TRANSLATE, SOURCE_LANG, TARGET_LANG)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithoutTextThrowsIllegalArgumentException() {
		translators.forEach(translator -> translator.translate(null, SOURCE_LANG, TARGET_LANG));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithTextExceedingMaxLengthThrowsIllegalArgumentException() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_TEXT_LENGTH + 1; i++) {
			sb.append("a");
		}
		translators.forEach(translator -> translator.translate(sb.toString(), SOURCE_LANG, TARGET_LANG));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithoutTargetLangThrowsIllegalArgumentException() {
		translators.forEach(translator -> translator.translate(TEXT_TO_TRANSLATE, SOURCE_LANG, null));
	}

	@Test
	public void testTranslateWithoutSourceLangEndsOK() {
		translators.forEach(translator -> assertEquals(TRANSLATED_TEXT,
				translator.translate(TEXT_TO_TRANSLATE, null, TARGET_LANG)));
	}

	@Test(expected = TranslationException.class)
	public void testTranslateWithUnknownLangThrowsTranslationException() {
		translators.forEach(translator -> translator.translate(TEXT_TO_TRANSLATE, SOURCE_LANG, "toto"));
	}

}
