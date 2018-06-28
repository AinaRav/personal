package com.ainarav.translator;

import com.ainarav.translator.util.TranslatorHelper;

/**
 * {@link Translator} test
 * 
 * @author ainar
 *
 */
public interface TranslatorTest {

	/**
	 * Defines the translator implementation to test
	 */
	void setUpTranslator();

	/**
	 * Translating with correct parameters should end OK
	 */
	void testTranslateWithCorrectParametersEndsOK();

	/**
	 * Translating without text should throw an {@link IllegalArgumentException}
	 */
	void testTranslateWithoutTextThrowsIllegalArgumentException();

	/**
	 * Translating with a text of which length exceeds
	 * {@link TranslatorHelper#MAX_TEXT_LENGTH} should throw an
	 * {@link IllegalArgumentException}
	 */
	void testTranslateWithTextExceedingMaxLengthThrowsIllegalArgumentException();

	/**
	 * Translating without a target language should throw an
	 * {@link IllegalArgumentException}
	 */
	void testTranslateWithoutTargetLangThrowsIllegalArgumentException();

	/**
	 * Translating without a source language should end OK
	 */
	void testTranslateWithoutSourceLangEndsOK();

}