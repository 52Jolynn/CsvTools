package com.laudandjolynn.csvtools;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:38:15
 * @copyright: www.laudandjolynn.com
 */
public class Assert {
	/**
	 * assert object is not null
	 * 
	 * @param object
	 */
	public static void assertNotNull(Object object) {
		if (object == null) {
			throw new CsvException("object can't be null.");
		}
	}
}
