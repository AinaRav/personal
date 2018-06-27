package com.ainarav.translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestGoogleTranslator {

	@Test
	public void testGoogleTranslatorSuccessFulCall() {
		Translator translator = new GoogleTranslator();
		String text = "Bonjour";
		String sourceLang = "fr";
		String targetLang = "en";
		assertEquals("Hello", translator.translate(text, sourceLang, targetLang));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGoogleTranslatorNoText() {
		Translator translator = new GoogleTranslator();
		String text = null;
		String sourceLang = "fr";
		String targetLang = "en";
		translator.translate(text, sourceLang, targetLang);
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void testGoogleTranslatorTextExceedsMaxLength() {
		Translator translator = new GoogleTranslator();
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < 10001; i++) {
			sb.append("a");
		}
		String text = sb.toString();
		String sourceLang = "fr";
		String targetLang = "en";
		translator.translate(text, sourceLang, targetLang);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGoogleTranslatorNoTargetLang() {
		Translator translator = new GoogleTranslator();
		String text = "Bonjour";
		String sourceLang = "fr";
		String targetLang = null;
		translator.translate(text, sourceLang, targetLang);
	}
	
	@Test
	public void testGoogleTranslatorNoSourceLang() {
		Translator translator = new GoogleTranslator();
		String text = "Bonjour";
		String sourceLang = null;
		String targetLang = "en";
		assertEquals("Hello", translator.translate(text, sourceLang, targetLang));
	}

	
}
