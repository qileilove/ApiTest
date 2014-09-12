package com.shizhan.api.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.shizhan.api.tools.EnvSetting;
import com.shizhan.api.tools.ExcelTools;
import com.shizhan.api.tools.LoggerControler;

/**
 * @author qilei 创建HttpClient 的实例
 */
public class GetExcelData {
	ExcelTools et = new ExcelTools();
	TestInterface tif = new TestInterface();

	HttpClient client = new DefaultHttpClient();

	/**
	 * @param rownumber
	 * @param sheet
	 * @return
	 * @throws NullPointerException
	 * @throws IOException
	 * @throws Exception
	 *             获取excel的数据并执行相应的post或者get请求
	 */
	public String getData(int rownumber, HSSFSheet sheet)
			throws NullPointerException, IOException, Exception {

		int cellnumber = sheet.getRow(rownumber).getPhysicalNumberOfCells();

		if (cellnumber < 2) {
			// 如果传入只有URL则进行get请求
			return tif.HttpGet(et.getUrl(sheet).get(rownumber), client,
					tif.GetSession(client));
		} else {
			et.getnowcolumndata(rownumber);
			LoggerControler.getLogger(ExcelTools.class).info(
					"map对象" + et.getnowcolumndata(rownumber).toString());

			return tif.HttpPost(et.getUrl(sheet).get(rownumber),
					et.getnowcolumndata(rownumber), client,
					tif.GetSession(client));

		}
	}

	public String getAssertData(int rownumber, HSSFSheet sheet)
			throws NullPointerException, IOException, Exception {

		return et.getAssertdata(sheet, rownumber).get(rownumber);

	}

	public static void main(String[] args) throws NullPointerException,
			Exception {

		InputStream input = new FileInputStream(EnvSetting.testCasesPath);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// HSSFSheet sheet = wb.getSheetAt(0);
		HSSFSheet sheet = wb.getSheet(EnvSetting.sheetname);

		GetExcelData gd = new GetExcelData();
		int rownumber = sheet.getPhysicalNumberOfRows();
		System.err.println(rownumber);
		for (int i = 1; i <= rownumber - 1; i++) {
			System.err.println(gd.getData(i, sheet));

		}

	}

}
