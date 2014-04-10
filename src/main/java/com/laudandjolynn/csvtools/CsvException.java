package com.laudandjolynn.csvtools;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:38:36
 * @copyright: www.laudandjolynn.com
 */
public class CsvException extends RuntimeException {
	private static final long serialVersionUID = -986768360613830726L;

	public CsvException() {
		super();
	}

	public CsvException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CsvException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvException(String message) {
		super(message);
	}

	public CsvException(Throwable cause) {
		super(cause);
	}

}
