/**
 * 
 */
package com.sap.bnet.ws.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.bnet.ws.model.Package;

/**
 * @author Shawn
 *
 */
public class JAXBUtils {
	
	public final static Logger logger = LoggerFactory.getLogger(JAXBUtils.class);
	
	public static String marshal(Package packages){
		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		try {  
            JAXBContext context = JAXBContext.newInstance(Package.class);  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"utf-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            PrintWriter writer = new PrintWriter(buff);
            writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            marshaller.marshal(packages, writer);
        } catch (JAXBException e) {
        	logger.error("JAXB marshal package object exception",e);
            throw new RuntimeException(e);
        }
		return buff.toString();
	}
	
	public static Package unmarshal(String xml) {
		Package packages = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Package.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			packages = (Package) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			logger.error("JAXB unmarshal package object exception",e);
            throw new RuntimeException(e);
		}
		return packages;
	}
	
}
