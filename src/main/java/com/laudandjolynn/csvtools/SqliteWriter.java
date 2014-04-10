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

import java.util.List;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午5:15:56
 * @copyright: www.laudandjolynn.com
 */
public class SqliteWriter implements CsvDataWriter {
	/**
	 * @param databaseName
	 * @param tableName
	 */
	public SqliteWriter(String databaseName, String tableName) {

	}

	@Override
	public void write(CsvFile csvFile) {
		List<CsvDataLine> csvDataList = CsvTools.parse(csvFile);
		for (int i = 0, size = csvDataList == null ? 0 : csvDataList.size(); i < size; i++) {
			CsvDataLine csvDataLine = csvDataList.get(i);
		}
	}
}
