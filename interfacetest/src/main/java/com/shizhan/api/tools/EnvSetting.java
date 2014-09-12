package com.shizhan.api.tools;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author qilei 
 * 环境设置类 加载properties，
 * 进行环境设置 在env.properties文件中，
 * 设置打开浏览器的类型，访问的地址
 * 
 *         规定：env.properties在./src下面
 * 
 */
public class EnvSetting {

	/**
	 * properties对象
	 * */
	private static Properties properties = getProperties();

	/**
	 * 测试总用例
	 * */
	public static String testCasesPath = properties.getProperty(
			"testCasesPath", System.getProperty("user.dir")
					+ "//aa.xls");
	/**
	 * loginUrl
	 */
	public static String loginurl = properties.getProperty(
			"loginurl");
	
	/**
	 * 用户名
	 */
	public static String username = properties.getProperty(
			"username");
	/**
	 * 密码
	 */
	public static String password = properties.getProperty(
			"password");
	
	/**
	 * 验证信息页
	 */
	public static String assertsheet = properties.getProperty(
			"assertsheet",System.getProperty("user.dir")
			+ "//aa.xls");
	
	
	
	/**
	 * excel的页的名称
	 */
	public static String sheetname = properties.getProperty(
			"sheetname", System.getProperty("user.dir")
					+ "//aa.xls");
	/**
	 * 内部方法 该方法指定了properties存放的位置和返回一个properties对象（读取env.properties的对象)
	 * */
	private static Properties getProperties() {
		Properties prop = new Properties();
		String envPaht = System.getProperty("user.dir")
				+ "\\env.properties";
		try {
			FileInputStream file = new FileInputStream(envPaht);
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
