/**
 * 
 */
package com.sap.bnet.ws.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Shawn
 *
 */
@XmlRootElement(name="Package")
@XmlAccessorType(XmlAccessType.FIELD)
public class Package implements Serializable{
	
	private static final long serialVersionUID = 1233567505377868105L;
	
	@XmlElement(name="StreamingNo")
	private String streamingNo;
	
	@XmlElement(name="OPFlag")
	private String opFlag;
	
	@XmlElement(name="SIID")
	private String siId;
	
	@XmlElement(name="TimeStamp")
	private String timeStamp;
	
	@XmlElement(name="ProductID")
	private String productId;
	
	@XmlElement(name="CustAccount")
	private String custAccount;
	
	@XmlElement(name="Summary")
	private String summary;
	
	@XmlElement(name="ReturnStatus")
	private String returnStatus;
	
	@XmlElement(name="BizID")
	private String bizID;
	
	@XmlElement(name="Password")
	private String password;
	
	@XmlElement(name="AreaCode")
	private String areaCode;
	
	@XmlElement(name="CustID")
	private String custID;
	
	@XmlElement(name="CustName")
	private String custName;
	
	@XmlElement(name="AccessNo")
	private String accessNo;
	
	@XmlElementWrapper(name="ProductInfo")
    @XmlElement(name="ProductAttribute")
	private List<Product> products;
	
    @XmlElement(name="AccountInfo")
	private List<Account> accounts;
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getStreamingNo() {
		return streamingNo;
	}

	public void setStreamingNo(String streamingNo) {
		this.streamingNo = streamingNo;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public String getSiId() {
		return siId;
	}

	public void setSiId(String siId) {
		this.siId = siId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getBizID() {
		return bizID;
	}

	public void setBizID(String bizID) {
		this.bizID = bizID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccessNo() {
		return accessNo;
	}

	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
