/**
 * 
 */
package com.sap.bnet.ws.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.bnet.ws.model.Account;
import com.sap.bnet.ws.model.Package;
import com.sap.bnet.ws.model.Product;

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
	
	
	
	public static void main(String[] args) {
		Package pack = new Package();
		pack.setCustAccount("accc");
		pack.setOpFlag("0104");
		pack.setSiId("");
		pack.setProductId("");
		pack.setStreamingNo("asddsds");
		pack.setSummary("客户查询");
		pack.setTimeStamp("12548947949");
		
		ArrayList<Product> list = new ArrayList();
		Product product = new Product();
		product.setParentType("parentT");
		product.setProductInstId("productInId");
		product.setProductValue("");
		list.add(product);
		
		Product product2 = new Product();
		product2.setParentType("parentT2");
		product2.setProductInstId("productInId2");
		product2.setProductValue("");
		list.add(product2);
		pack.setProducts(list);
		
		Account account = new Account();
		account.setAccName("accName1");
		account.setAccType("accType1");
		
		ArrayList<Account> list2 = new ArrayList();
		list2.add(account);
		pack.setAccounts(list2);
		
		String result = JAXBUtils.marshal(pack);
		System.out.println(result);
		
		
		String customerXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
	              +   "<Package>"
	              +        "<OPFlag>0104</OPFlag>"
	              +        "<SIID>3314</SIID>"
	              +        "<TimeStamp>558899</TimeStamp>"
	              +        "<ProductID>15549898</ProductID>"
	              +        "<CustAccount>1546466111</CustAccount>"
	              +        "<Summary>客户查询</Summary>" 
	              +   "</Package>";
		
		Package packages = JAXBUtils.unmarshal(customerXml);
		System.out.println(packages.getOpFlag());
		System.out.println(packages.getProductId());
	}

}
