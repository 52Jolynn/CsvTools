/*******************************************************************************
 * Copyright (c) 2014 htd0324@gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     htd0324@gmail.com - initial API and implementation
 ******************************************************************************/
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
