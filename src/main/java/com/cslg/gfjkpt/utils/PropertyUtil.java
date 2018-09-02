package com.cslg.gfjkpt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author aekc
 */
public class PropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private static Properties properties;

    private static Map<String, Object> propertyMap;

    static {
        loadProps();
    }

    private static synchronized void loadProps() {
        properties = new Properties();
        InputStream in = null;
        try {
            //通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("resource/resource.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.error("resource.properties文件没有找到");
        } catch (IOException e) {
            logger.error("加载文件出现错误");
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("resource.properties文件流关闭出现异常");
            }
        }
    }

    public static Map getPropertyMap() {
        if(properties == null) {
            loadProps();
        }
        Enumeration en = properties.keys();
        while(en.hasMoreElements()) {
            String key = (String) en.nextElement();
            propertyMap.put(key, properties.getProperty(key));
        }
        return propertyMap;
    }

    public static String getProperty(String key) {
        if(properties == null) {
            loadProps();
        }
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == properties) {
            loadProps();
        }
        return properties.getProperty(key, defaultValue);
    }
}
