package com.shizhan.api.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelTools {

	
	/**得到第一列的url
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public Map<Integer, String> getUrl(HSSFSheet sheet) throws IOException{
		Map<Integer, String> map = new HashMap<Integer,String>();
        
        int rownum=sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rownum; i++) {
			sheet.getRow(i);
			String value = getCellFormatValue(sheet.getRow(i).getCell(0));
			
			map.put(i,value);
		}
        
        
		return map;  

	}
	
	
	
	
	/**
	 * @param sheet
	 * @return 得到第一列的验证信息
	 * @throws IOException
	 */
	public Map<Integer, String> getAssertdata(HSSFSheet sheet,int rownumber) throws IOException{
		Map<Integer, String> map = new HashMap<Integer,String>();
        
    
     
			sheet.getRow(rownumber);
			String value = getCellFormatValue(sheet.getRow(rownumber).getCell(0));
			
			map.put(rownumber,value);
		
        
        
		return map;  

	}
	
	
	/**得到excel的行数
	 * @return
	 * @throws IOException
	 */
	public int getrow() throws IOException{
        InputStream input = new FileInputStream(EnvSetting.testCasesPath);
        POIFSFileSystem fs = new POIFSFileSystem(input);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFSheet sheet = wb.getSheetAt(0);  
        int rownum=sheet.getPhysicalNumberOfRows();
        
        
		return rownum;  

	}
	
	/**得到后面的数据
	 * @return
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public Map<String,String> getdata()throws IOException,NullPointerException{
		Map<String, String> map = new HashMap<String,String>();
        InputStream input = new FileInputStream(EnvSetting.testCasesPath);
        POIFSFileSystem fs = new POIFSFileSystem(input);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFSheet sheet = wb.getSheet(EnvSetting.sheetname);  
        int rownum=sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rownum; i++) {
			int columnnum=sheet.getRow(i).getPhysicalNumberOfCells();
			for (int j = 1; j <=(columnnum-1)/2; j++) {
				if (j<2) {
					
				
				int keycolumn=j;
				
				
					
				
				int valuecomun=j+1;
				String key = getCellFormatValue(sheet.getRow(i).getCell(keycolumn));
				
				String value=getCellFormatValue(sheet.getRow(i).getCell(valuecomun));
				LoggerControler.getLogger(ExcelTools.class).info(key+":"+value);

				map.put(key,value);
				}
				else {
					int keycolumn=j*2-1;
					
					
					
					
					int valuecomun=j*2;
					String key = getCellFormatValue(sheet.getRow(i).getCell(keycolumn));
					
					String value=getCellFormatValue(sheet.getRow(i).getCell(valuecomun));
					LoggerControler.getLogger(ExcelTools.class).info(key+":"+value);

					map.put(key,value);
				}
			}
			
		}
        
		return map;
		
	}
	
	
	
	/**根据当前传入的行数 返回当前行的要传递的数据
	 * @param column
	 * @return
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public Map<String,String> getnowcolumndata(int column)throws IOException,NullPointerException{
		Map<String, String> map = new HashMap<String,String>();
        InputStream input = new FileInputStream(EnvSetting.testCasesPath);
        POIFSFileSystem fs = new POIFSFileSystem(input);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFSheet sheet = wb.getSheet(EnvSetting.sheetname);  
        int rownum=sheet.getPhysicalNumberOfRows();
			int columnnum=sheet.getRow(column).getLastCellNum();
			for (int j = 1; j <=(columnnum-1)/2; j++) {
				if (j<2) {
					
				
				int keycolumn=j;
				
				
					
				
				int valuecomun=j+1;
				String key = getCellFormatValue(sheet.getRow(column).getCell(keycolumn));
				
				String value=getCellFormatValue(sheet.getRow(column).getCell(valuecomun));

				map.put(key,value);
				}
				
				else {
					int keycolumn=j*2-1;
					
					
					
					
					int valuecomun=j*2;
					String key = getCellFormatValue(sheet.getRow(column).getCell(keycolumn));
					
					String value=getCellFormatValue(sheet.getRow(column).getCell(valuecomun));
					map.put(key,value);
				}
			}
			
		

		return map;
		
	}
	
	
	 /**格式化数据
	 * @param cell
	 * @return
	 */
	private String getStringCellValue(HSSFCell cell) {
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        if (cell == null) {
	            return "";
	        }
	        return strCell;
	    }

	    /**
	     * 获取单元格数据内容为日期类型的数据
	     * 
	     * @param cell
	     *            Excel单元格
	     * @return String 单元格数据内容
	     */
	    private String getDateCellValue(HSSFCell cell) {
	        String result = "";
	        try {
	            int cellType = cell.getCellType();
	            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
	                Date date = cell.getDateCellValue();
	                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
	                        + "-" + date.getDate();
	            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
	                String date = getStringCellValue(cell);
	                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
	            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
	                result = "";
	            }
	        } catch (Exception e) {
	            System.out.println("日期格式不正确!");
	            e.printStackTrace();
	        }
	        return result;
	    }

	    /**
	     * 根据HSSFCell类型设置数据
	     * @param cell
	     * @return
	     */
	    private String getCellFormatValue(HSSFCell cell) {
	        String cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            // 如果当前Cell的Type为NUMERIC
	            case HSSFCell.CELL_TYPE_NUMERIC:
	            case HSSFCell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    
	                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    
	                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    cellvalue = sdf.format(date);
	                    
	                }
	                // 如果是纯数字
	                else {
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // 如果当前Cell的Type为STRIN
	            case HSSFCell.CELL_TYPE_STRING:
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // 默认的Cell值
	            default:
	                cellvalue = " ";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;
	        
	        
	        
	        

	    }


	public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream("D://aa.xls");
        POIFSFileSystem fs = new POIFSFileSystem(input);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFSheet sheet = wb.getSheetAt(0);  
      // System.out.println(sheet.getPhysicalNumberOfRows());
       int rownum=sheet.getPhysicalNumberOfRows();
/*       for (int i = 1; i < rownum; i++) {
			sheet.getRow(i);
			sheet.getRow(i).getCell(1);
System.out.println(			sheet.getRow(i).getCell(1)
);*/
ExcelTools aa= new ExcelTools();
/*aa.getUrl();
Map<Integer, String> map = aa.getUrl();
*/
;
/*for (int j = 1; j < map.size()+1; j++) {
	System.out.println(map.get(j ));
	
}*/

/*Map<String, String> map1 = aa.getdata();
System.out.println(map1.size());
System.out.println(map1.get("b3"));
Map<String, String> map2 = aa.getnowcolumndata(2);
System.out.println(map2.size());
System.out.println(map2.get("CCC"));*/
for (int i = 1; i <aa.getrow(); i++) {
	Map<String, String> map3 = aa.getnowcolumndata(i);
	System.out.println("每行的size"+map3.size());
	System.out.println("b3 value"+map3.get("b3"));
}
}


		}
	

