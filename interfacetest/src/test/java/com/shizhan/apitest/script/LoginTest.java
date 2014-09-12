package com.shizhan.apitest.script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.shizhan.api.imp.GetExcelData;
import com.shizhan.api.tools.EnvSetting;
import com.shizhan.api.tools.ExcelTools;

public class LoginTest {

	/**233环境登陆接口测试
	 * @throws NullPointerException
	 * @throws Exception
	 */
	@Test
	public void testlogin() throws NullPointerException, Exception {
		// TODO Auto-generated method stub
		InputStream input = new FileInputStream(EnvSetting.testCasesPath);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet testsheet = wb.getSheet(EnvSetting.sheetname);
		HSSFSheet assertsheet = wb.getSheet(EnvSetting.assertsheet);

		GetExcelData ged= new GetExcelData();
		int rownumber = testsheet.getPhysicalNumberOfRows();
		for (int i = 1; i <= rownumber - 1; i++) {
			String assertdata=ged .getAssertData(i, assertsheet);
			boolean result=ged.getData(i, testsheet).contains(assertdata);
			Assert.assertEquals(result, true);
			

		}
	}
}
