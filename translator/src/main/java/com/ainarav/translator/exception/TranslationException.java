package com.ainarav.translator.exception;

import static java.lang.String.format;

/**
 * Unrecoverable exception thrown when translating.
 *
 * @author ainar
 *
 */
public class TranslationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6655578545883749417L;

	/**
	 * Constructor that wraps the exception thrown when translating with the given API
	 *
	 * @param apiName
	 *            underlying API name
	 * @param e
	 *            underlying exception
	 */
	public TranslationException(String apiName, Exception e) {
		super(format("Error when translating text using %s API", apiName), e);
	}

	/**
	 * @param msg
	 */
	public TranslationException(String msg) {
		super(msg);
	}

}
