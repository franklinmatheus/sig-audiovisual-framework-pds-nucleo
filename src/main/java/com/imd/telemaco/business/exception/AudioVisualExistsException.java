package com.imd.telemaco.business.exception;

/**
 * 
 * @author valmir
 *
 */
public class AudioVisualExistsException extends Exception {
	
	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor default
	 */
	public AudioVisualExistsException () { }
	
	/**
	 * Exists Exception
	 * @param msg
	 */
	public AudioVisualExistsException (String msg) {
		super(msg);
	}
}
