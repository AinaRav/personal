package com.ainarav.translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestGoogleTranslator {

	@Test
	public void testGoogleTranslatorSuccessFullCall() {
		Translator translator = new GoogleTranslator();
		String text = "Bonjour";
		String sourceLang = "fr";
		String targetLang = "en";
		assertEquals("Hello", translator.translate(text, sourceLang, targetLang));
	}

}
