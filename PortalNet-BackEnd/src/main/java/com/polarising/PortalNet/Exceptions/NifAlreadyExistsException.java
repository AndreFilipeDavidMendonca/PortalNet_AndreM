package com.polarising.PortalNet.Exceptions;



public class NifAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7536874238506435806L;

	public NifAlreadyExistsException(String message)
	{
		super(message);
	}
	
	public NifAlreadyExistsException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
