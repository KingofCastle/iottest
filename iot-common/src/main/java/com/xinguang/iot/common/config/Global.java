package com.xinguang.iot.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chuantou on 2016/12/26.
 */
public class Global {

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("iot.properties");

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = propertiesLoader.getProperty(key);
            map.put(key, value);
        }
        return value;
    }

    public static Properties getProperties() {
        return propertiesLoader.getProperties();
    }
}
