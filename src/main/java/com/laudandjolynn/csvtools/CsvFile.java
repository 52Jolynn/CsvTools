package com.laudandjolynn.csvtools;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:24:10
 * @copyright: www.laudandjolynn.com
 */
public class CsvFile {
	private String filePath;
	private int skipLines;
	private int dataTypeLine;

	public CsvFile(String filePath, int skipLines, int dataTypeLine) {
		this.filePath = filePath;
		this.skipLines = skipLines;
		this.dataTypeLine = dataTypeLine;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSkipLines() {
		return skipLines;
	}

	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

	public int getDataTypeLine() {
		return dataTypeLine;
	}

	public void setDataTypeLine(int dataTypeLine) {
		this.dataTypeLine = dataTypeLine;
	}

	@Override
	public String toString() {
		return "[filePath=" + filePath + ", skipLines=" + skipLines
				+ ", dataTypeLine=" + dataTypeLine + "]";
	}

}
