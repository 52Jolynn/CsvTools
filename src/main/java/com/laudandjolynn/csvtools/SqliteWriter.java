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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午5:15:56
 * @copyright: www.laudandjolynn.com
 */
public class SqliteWriter implements CsvDataWriter {
	private final static Logger logger = LoggerFactory
			.getLogger(SqliteWriter.class);
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
		// create a database connection
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"
					+ databaseName);
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			List<CsvDataLine> csvDataList = CsvTools.parse(csvFile);
			int fieldLineIndex = csvFile.getFieldLineIndex();
			if (fieldLineIndex == -1) {
				throw new CsvException(
						"can't find field declaration in csv file.");
			}
			int dataTypeLineIndex = csvFile.getDataTypeLineIndex();
			if (dataTypeLineIndex == -1) {
				throw new CsvException(
						"can't find data type declaration in csv file.");
			}
			List<CsvValue> fields = csvDataList.get(fieldLineIndex).getValues();
			List<CsvValue> dataTypes = csvDataList.get(dataTypeLineIndex)
					.getValues();
			int fsize = fields == null ? 0 : fields.size();
			int dtSize = dataTypes == null ? 0 : dataTypes.size();

			// 表不存在时，建表
			StringBuffer sbCreateTable = new StringBuffer();
			sbCreateTable.append("CREATE TABLE IF NOT EXISTS " + tableName
					+ " (");
			for (int i = 0; i < fsize; i++) {
				CsvValue value = fields.get(i);
				sbCreateTable.append(value.getValue());
				sbCreateTable.append(" ");
				if (i >= dtSize) {
					sbCreateTable.append("text");
				} else {
					String dataType = dataTypes.get(i).getValue();
					sbCreateTable
							.append(dataType.equalsIgnoreCase("int") ? dataType
									: "text");
				}
				if (i < fsize - 1) {
					sbCreateTable.append(",");
				}
			}
			sbCreateTable.append(")");
			stmt.execute(sbCreateTable.toString());

			StringBuffer sbField = new StringBuffer();
			for (int i = 0; i < fsize; i++) {
				CsvValue value = fields.get(i);
				sbField.append(value.getValue());
				if (i < fsize - 1) {
					sbField.append(",");
				}
			}
			String strField = sbField.toString();

			// 插入数据
			for (int i = csvFile.getSkipLines(), size = csvDataList == null ? 0
					: csvDataList.size(); i < size; i++) {
				CsvDataLine csvDataLine = csvDataList.get(i);
				List<CsvValue> values = csvDataLine.getValues();
				StringBuffer sbValues = new StringBuffer();
				for (CsvValue value : values) {
					sbValues.append("'" + value.getValue() + "',");
				}
				sbValues.deleteCharAt(sbValues.length() - 1);
				String sql = "INSERT into " + tableName + "(" + strField
						+ ") VALUES (" + sbValues.toString() + ")";
				logger.info("sql-" + i + ": " + sql);
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new CsvException(ex);
			}
			logger.error("import csv file failed.", e);
			throw new CsvException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new CsvException(e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new CsvException(e);
				}
			}
		}

	}
}
