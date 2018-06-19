package com.imd.telemaco.business.exception;

public class AudiovisualInvalidException extends Exception {

	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Constructor Default
	 */
	public AudiovisualInvalidException() { }
	
	/**
	 * Invalid Exception
	 * @param msg
	 */
	public AudiovisualInvalidException(String msg) {
		super(msg);
	}
	
}
