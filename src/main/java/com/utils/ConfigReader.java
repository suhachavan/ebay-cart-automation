package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	 private static Properties props;

	    public static Properties loadProperties() {
	        if (props == null) {
	            try {
	                props = new Properties();
	                FileInputStream fis = new FileInputStream("src/main/resources/Configurations/Desktop.properties");
	                props.load(fis);
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new RuntimeException("Failed to load config.properties");
	            }
	        }
	        return props;
	    }

}
