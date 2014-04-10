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
 * @date: 2014年4月10日 下午1:24:10
 * @copyright: www.laudandjolynn.com
 */
public class CsvFile {
	private String filePath;
	private int fieldLineIndex;
	private int skipLines;
	private int dataTypeLineIndex;

	/**
	 * 
	 * @param filePath
	 *            csv file path
	 * @param fieldLineIndex
	 *            index of field line, -1 if not exists
	 * @param skipLines
	 *            how many line skip to data line, must equal or greater than 0
	 * @param dataTypeLineIndex
	 *            index of data type line, -1 if not exists
	 */
	public CsvFile(String filePath, int fieldLineIndex, int skipLines,
			int dataTypeLineIndex) {
		this.filePath = filePath;
		this.fieldLineIndex = fieldLineIndex;
		this.skipLines = skipLines < 0 ? 0 : skipLines;
		this.dataTypeLineIndex = dataTypeLineIndex;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFieldLineIndex() {
		return fieldLineIndex;
	}

	public void setFieldLineIndex(int fieldLineIndex) {
		this.fieldLineIndex = fieldLineIndex;
	}

	public int getSkipLines() {
		return skipLines;
	}

	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

	public int getDataTypeLineIndex() {
		return dataTypeLineIndex;
	}

	public void setDataTypeLineIndex(int dataTypeLineIndex) {
		this.dataTypeLineIndex = dataTypeLineIndex;
	}

	@Override
	public String toString() {
		return "[filePath=" + filePath + ", fieldLineIndex=" + fieldLineIndex
				+ ", skipLines=" + skipLines + ", dataTypeLineIndex="
				+ dataTypeLineIndex + "]";
	}

}
