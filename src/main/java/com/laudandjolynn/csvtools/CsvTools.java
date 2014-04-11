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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 下午1:33:26
 * @copyright: www.laudandjolynn.com
 */
public class CsvTools {
	private final static Logger logger = LoggerFactory
			.getLogger(CsvTools.class);
	public final static String COMMA = ",";
	private final static String DEFAULT_DATA_TYPE = "string";

	/**
	 * parse csv file
	 * 
	 * @param csvFile
	 * @return
	 */
	public static List<CsvDataLine> parse(CsvFile csvFile) {
		Assert.assertNotNull(csvFile);
		String filePath = csvFile.getFilePath();
		File file = new File(filePath);
		if (!file.exists()) {
			throw new CsvException(filePath + " can not be found.");
		}

		InputStream is = null;
		BufferedReader reader = null;
		try {
			String line = null;
			int lineNumber = 0;
			int skipLines = csvFile.getSkipLines();
			int dataTypeLineIndex = csvFile.getDataTypeLineIndex();
			int[] ignoreColumnIndex = csvFile.getIgnoreColumnIndex();
			// 排序，后面使用二分查找
			if (ignoreColumnIndex != null) {
				Arrays.sort(ignoreColumnIndex);
			}
			List<String> dataTypeList = new ArrayList<String>();
			List<CsvDataLine> csvDataList = new ArrayList<CsvDataLine>();
			is = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				int dataTypeListSize = 0;
				if (lineNumber > skipLines) {
					dataTypeListSize = dataTypeList == null ? 0 : dataTypeList
							.size();
				}
				CsvDataLine csvDataLine = new CsvDataLine();
				StringTokenizer tokenizer = new StringTokenizer(line, COMMA);
				int columnIndex = 0;
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					if (ignoreColumnIndex != null
							&& Arrays.binarySearch(ignoreColumnIndex,
									columnIndex) >= 0) {
						columnIndex++;
						continue;
					}
					CsvValue value = new CsvValue();
					value.setLineNumber(lineNumber);
					value.setValue(token);
					if (lineNumber == dataTypeLineIndex) {
						dataTypeList.add(token.toLowerCase());
					}
					value.setDataType(lineNumber > skipLines
							&& columnIndex < dataTypeListSize ? dataTypeList
							.get(columnIndex) : DEFAULT_DATA_TYPE);
					logger.debug("read data from csv file: " + value);
					csvDataLine.add(value);
					columnIndex++;
				}
				csvDataList.add(csvDataLine);
			}
			return csvDataList;
		} catch (FileNotFoundException e) {
			throw new CsvException(e);
		} catch (IOException e) {
			throw new CsvException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new CsvException(e);
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new CsvException(e);
				}
			}
		}
	}
}
