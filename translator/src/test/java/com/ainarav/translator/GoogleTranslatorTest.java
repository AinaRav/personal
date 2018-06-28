package com.ainarav.translator;

import static com.ainarav.translator.TranslatorTestCst.SOURCE_LANG;
import static com.ainarav.translator.TranslatorTestCst.TARGET_LANG;
import static com.ainarav.translator.TranslatorTestCst.TEXT_TO_TRANSLATE;
import static com.ainarav.translator.TranslatorTestCst.TRANSLATED_TEXT;
import static com.ainarav.translator.util.TranslatorHelper.MAX_TEXT_LENGTH;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ainarav.translator.impl.GoogleTranslator;

/**
 * {@link GoogleTranslator} test
 *
 * @author ainar
 *
 */
public class GoogleTranslatorTest implements TranslatorTest {

	private Translator translator;

	@Before
	@Override
	public void setUpTranslator() {
		translator = new GoogleTranslator();
	}

	@Override
	@Test
	public void testTranslateWithCorrectParametersEndsOK() {
		assertEquals(TRANSLATED_TEXT, translator.translate(TEXT_TO_TRANSLATE, SOURCE_LANG, TARGET_LANG));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithoutTextThrowsIllegalArgumentException() {
		translator.translate(null, SOURCE_LANG, TARGET_LANG);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithTextExceedingMaxLengthThrowsIllegalArgumentException() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_TEXT_LENGTH + 1; i++) {
			sb.append("a");
		}
		translator.translate(sb.toString(), SOURCE_LANG, TARGET_LANG);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testTranslateWithoutTargetLangThrowsIllegalArgumentException() {
		translator.translate(TEXT_TO_TRANSLATE, SOURCE_LANG, null);
	}

	@Override
	@Test
	public void testTranslateWithoutSourceLangEndsOK() {
		assertEquals(TRANSLATED_TEXT, translator.translate(TEXT_TO_TRANSLATE, null, TARGET_LANG));
	}

}
