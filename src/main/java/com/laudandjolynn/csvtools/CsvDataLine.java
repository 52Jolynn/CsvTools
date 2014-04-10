package com.laudandjolynn.csvtools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:20:52
 * @copyright: www.laudandjolynn.com
 */
public class CsvDataLine {
	private List<CsvValue> lines = new ArrayList<CsvValue>();

	public void add(CsvValue value) {
		this.lines.add(value);
	}

	public void addAll(List<CsvValue> values) {
		this.lines.addAll(values);
	}

	public List<CsvValue> getValues() {
		return this.lines;
	}

	@Override
	public String toString() {
		return "[lines=" + lines + "]";
	}
}
