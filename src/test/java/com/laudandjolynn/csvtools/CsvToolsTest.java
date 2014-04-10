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

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2014年4月10日 上午11:31:02
 * @copyright: www.laudandjolynn.com
 */
public class CsvToolsTest extends TestCase {
	public void testCsvTools() {
		String filePath = "C:\\Users\\tdhuang\\Desktop\\功夫baby数据表格1(未加数值)\\关卡对应图片\\high_level_picture.csv";
		CsvFile csvFile = new CsvFile(filePath, 1, 4, 2);
		List<CsvDataLine> data = CsvTools.parse(csvFile);
		System.out.println(data);
		assertNotNull(data);
	}

	public void testCsvTools2() {
		String filePath = "C:\\Users\\tdhuang\\Desktop\\功夫baby数据表格1(未加数值)\\战斗关卡地图\\rich_map.csv";
		CsvFile csvFile = new CsvFile(filePath, 1, 4, 2);
		List<CsvDataLine> data = CsvTools.parse(csvFile);
		System.out.println(data);
		assertNotNull(data);
	}
}
