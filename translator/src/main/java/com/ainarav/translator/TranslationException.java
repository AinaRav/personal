package com.ainarav.translator;

import static java.lang.String.format;

import java.io.IOException;

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
	public TranslationException(String apiName, IOException e) {
		super(format("Error when translating text using %s API", apiName), e);
	}

}
