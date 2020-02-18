/**
 * 
 */
package com.iceico.testportal.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 05/12/2019
 * 
 *          Updated Date :
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8826936515809007996L;

	public MyFileNotFoundException(String message) {
		super(message);
	}

	public MyFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
