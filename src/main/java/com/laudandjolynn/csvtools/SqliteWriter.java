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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午5:15:56
 * @copyright: www.laudandjolynn.com
 */
public class SqliteWriter implements CsvDataWriter {
	private String databaseName = null;
	private String tableName = null;

	/**
	 * @param databaseName
	 * @param tableName
	 */
	public SqliteWriter(String databaseName, String tableName) {
		this.databaseName = databaseName;
		this.tableName = tableName;
	}

	@Override
	public void write(CsvFile csvFile) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new CsvException(e);
		}
		Connection connection = null;
		// create a database connection
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"
					+ databaseName);
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			List<CsvDataLine> csvDataList = CsvTools.parse(csvFile);
			int fieldLineIndex = csvFile.getFieldLineIndex();
			if (fieldLineIndex == -1) {
				throw new CsvException(
						"can't find field declaration in csv file.");
			}
			List<CsvValue> fields = csvDataList.get(fieldLineIndex).getValues();
			StringBuffer sbField = new StringBuffer();
			for (CsvValue field : fields) {
				sbField.append(field.getValue() + ",");
			}
			sbField.deleteCharAt(sbField.length() - 1);
			String strField = sbField.toString();
			for (int i = csvFile.getSkipLines(), size = csvDataList == null ? 0
					: csvDataList.size(); i < size; i++) {
				CsvDataLine csvDataLine = csvDataList.get(i);
				List<CsvValue> values = csvDataLine.getValues();
				StringBuffer sbValues = new StringBuffer();
				for (CsvValue value : values) {
					sbValues.append("'" + value.getValue() + "',");
				}
				sbValues.deleteCharAt(sbValues.length() - 1);
				String sql = "insert into " + tableName + "(" + strField
						+ ") values (" + sbValues.toString() + ")";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			throw new CsvException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new CsvException(e);
				}
			}
		}

	}
}
