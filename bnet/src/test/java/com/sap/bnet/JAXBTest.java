/**
 * 
 */
package com.sap.bnet;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.bnet.ws.model.Account;
import com.sap.bnet.ws.model.Package;
import com.sap.bnet.ws.model.Product;
import com.sap.bnet.ws.utils.JAXBUtils;

/**
 * @author Shawn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-ws.xml")
public class JAXBTest {
	
	@Test
	public void buildPackage(){
		Package basePackage = generateBasePackage();
		String xml = JAXBUtils.marshal(basePackage);
		System.out.println(xml);
	}
	
	@Test
	public void buildProductList(){
		Package basePackage = generateBasePackage();
		ArrayList<Product> productList = new ArrayList();
		Product product1 = new Product();
		product1.setParentType("usecount");
		product1.setProductInstId("200");
		product1.setProductValue("");
		productList.add(product1);
		
		Product product2 = new Product();
		product2.setParentType("USERMAX");
		product2.setProductInstId("1");
		product2.setProductValue("");
		productList.add(product2);
		basePackage.setProducts(productList);
		String xml = JAXBUtils.marshal(basePackage);
		System.out.println(xml);
	}
	
	@Test
	public void buildAccountList(){
		Package basePackage = generateBasePackage();
		ArrayList<Account> accountList = new ArrayList();
		Account account = new Account();
		account.setAccName("马汉");
		account.setImsi("");
		account.setUserID("ap");
		account.setAccType("1");
		accountList.add(account);
		
		basePackage.setAccounts(accountList);
		String xml = JAXBUtils.marshal(basePackage);
		System.out.println(xml);
	}
	
	private Package generateBasePackage(){
		Package pack = new Package();
		pack.setCustAccount("aptest");
		pack.setOpFlag("0104");
		pack.setSiId("");
		pack.setProductId("200");
		pack.setStreamingNo("1141");
		pack.setSummary("客户查询");
		pack.setTimeStamp("1244690606890");
		return pack;
	}
	

}
