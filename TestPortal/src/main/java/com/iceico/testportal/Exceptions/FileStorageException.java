/**
 * 
 */
package com.iceico.testportal.Exceptions;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 05/12/2019
 * 
 *          Updated Date :
 *
 */

public class FileStorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2894763977052905226L;

	/**
	 * 
	 */

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
