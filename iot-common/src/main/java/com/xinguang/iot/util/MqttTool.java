package com.xinguang.iot.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.xinguang.iot.common.config.Global;
import org.apache.log4j.Logger;

/**
 * Java Properties属性文件操作类
 * 
 * @author zer0
 * @version 1.0
 * @date 2015-2-19
 */
public class MqttTool {
	
	private final static Logger Log = Logger.getLogger(MqttTool.class);
	
	private static Properties props = new Properties();
	//配置文件路径
	private static final String CONFIG_FILE = "mqtt.properties";
	
	static{
		loadProperties(CONFIG_FILE);
	}
	
	/**
	 * 加载属性文件
	 * 
	 * @param propertyFilePath
	 * @author zer0
	 * @version 1.0
	 * @date 2015-2-19
	 */
	private static void loadProperties(String propertyFilePath){
		props = Global.getProperties();
	}

	/**
	 * 从指定的键取得对应的值
	 * 
	 * @param key
	 * @return String
	 * @author zer0
	 * @version 1.0
	 * @date 2015-2-19
	 */
	public static String getProperty(String key){
		return props.getProperty(key);
	}
	
	/**
	 *  从指定的键取得整数
	 * 
	 * @param key
	 * @return Integer
	 * @author zer0
	 * @version 1.0
	 * @date 2015-2-19
	 */
	public static Integer getPropertyToInt(String key){
		String str = props.getProperty(key);
		if(StringTool.isBlank(str.trim())){
			return null;
		}
		return Integer.valueOf(str.trim()); 
	}
}
