/**
 * 
 */
package com.sap.bnet.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author Shawn
 *
 */
public class ResourcesUtils {
	
	public final static Logger logger = LoggerFactory.getLogger(ResourcesUtils.class);
	
	public static Resource getResource(String path){
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource(path);
		if(resource.exists()){
			return resource;
		}
		return null;
	}
	
	public static String getResourceContent(String path){
		Resource resource = getResource(path);
		try {
			File file = resource.getFile();
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	sb.append(tempString);
	        }
			if(sb.length()>0){
				return sb.toString();
			}
		} catch (IOException e) {
			logger.error("PathMatchingResource exception",e);
            throw new RuntimeException(e);
		}
		return null;
	}

}
