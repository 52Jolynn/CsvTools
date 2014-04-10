package com.laudandjolynn.csvtools;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:18:38
 * @copyright: www.laudandjolynn.com
 */
public class CsvValue {
	private int lineNumber;
	private String value;
	private String dataType;

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "[lineNumber=" + lineNumber + ", value=" + value + ", dataType="
				+ dataType + "]";
	}

}
