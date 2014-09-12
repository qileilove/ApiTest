package com.shizhan.api.tools;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;



 /**
 * @author qilei
 *
 */
public final class LoggerControler {
	  private static Logger logger = null;
	    private static LoggerControler logg = null;
	    
	    /**得到log4j的配置 并创建全局对象
	     * @param T
	     * @return
	     */
	    public static LoggerControler getLogger(Class<?> T) {
	        if (logger == null) {
	            Properties props = new Properties();
	            
	            try {
	                InputStream is = new FileInputStream(System.getProperty("user.dir")+"/log/log4j.properties");
	                System.out.println(is);
	                props.load(is);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            PropertyConfigurator.configure(props);
	            logger = Logger.getLogger(T);
	            logg = new LoggerControler();
	        }
	        return logg;
	    }
	    
	    /**
		 * 重写logger方法
		 */
	    public void log(String msg) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Calendar ca = Calendar.getInstance();
	        logger.info(msg);
	        Reporter.log("Reporter:" + sdf.format(ca.getTime()) + "===>" + msg);
	    }
	    
	    public void debug(String msg) {
	        logger.debug(msg);
	    }
	    
	    public void warn(String msg) {
	        logger.warn(msg);
	        Reporter.log("Reporter:" + msg);
	    }
	    
	    public void error(String msg) {
	        logger.error(msg);
	        Reporter.log("Reporter:" + msg);
	    }
	    public void info(String msg) {
	        logger.info(msg);
	        Reporter.log("Reporter:" + msg);
	    }
	
}
