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

import com.sap.bnet.ws.constant.OPFlag;
import com.sap.bnet.ws.constant.RetunCode;
import com.sap.bnet.ws.utils.JAXBUtils;

/**
 * @author Shawn
 *
 */
@XmlRootElement(name="Package")
@XmlAccessorType(XmlAccessType.FIELD)
public class PackageElement implements Serializable{
	
	private static final long serialVersionUID = 1233567505377868105L;
	
	/**
	 * 流水号
	 */
	@XmlElement(name="StreamingNo")
	private String streamingNo;
	
	/**
	 * 业务标示
	 */
	@XmlElement(name="OPFlag")
	private OPFlag opFlag;
	
	/**
	 * SI编码
	 */
	@XmlElement(name="SIID")
	private String siId;
	
	/**
	 * 当前时间戳
	 */
	@XmlElement(name="TimeStamp")
	private String timeStamp;
	
	/**
	 * 产品标识
	 */
	@XmlElement(name="ProductID")
	private String productId;
	
	/**
	 * 客户帐号
	 */
	@XmlElement(name="CustAccount")
	private String custAccount;
	
	/**
	 * 备注
	 */
	@XmlElement(name="Summary")
	private String summary;
	
	/**
	 * 应答返回状态
	 */
	@XmlElement(name="ReturnStatus")
	private RetunCode returnStatus;
	
	/**
	 * 客户产品计费标识
	 */
	@XmlElement(name="BizID")
	private String bizID;
	
	/**
	 * 产品查询密码
	 */
	@XmlElement(name="Password")
	private String password;
	
	/**
	 * 客户地区
	 */
	@XmlElement(name="AreaCode")
	private String areaCode;
	
	/**
	 * 客户标识
	 */
	@XmlElement(name="CustID")
	private String custID;
	
	/**
	 * 客户名称
	 */
	@XmlElement(name="CustName")
	private String custName;
	
	/**
	 * 接入号
	 */
	@XmlElement(name="AccessNo")
	private String accessNo;
	
	/**
	 * 用户帐号
	 */
	@XmlElement(name="UserID")
	private String userId;
	
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

	public OPFlag getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(OPFlag opFlag) {
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

	public RetunCode getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(RetunCode returnStatus) {
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toXML(){
		return JAXBUtils.marshal(this);
	}
	
}
