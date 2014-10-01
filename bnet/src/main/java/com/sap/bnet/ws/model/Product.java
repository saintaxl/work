/**
 * 
 */
package com.sap.bnet.ws.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Shawn
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable{

	private static final long serialVersionUID = 6313437019264923985L;
	
	@XmlElement(name="ProductInstID")
	private String productInstId;
	
	@XmlElement(name="ProductType")
	private String productType;
	
	@XmlElement(name="ProductValue")
	private String productValue;
	
	@XmlElement(name="ParentType")
	private String parentType;
	
	@XmlElement(name="ProductParentInstID")
	private String productParentInstID;
	
	public String getProductInstId() {
		return productInstId;
	}

	public void setProductInstId(String productInstId) {
		this.productInstId = productInstId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getProductParentInstID() {
		return productParentInstID;
	}

	public void setProductParentInstID(String productParentInstID) {
		this.productParentInstID = productParentInstID;
	}
	

}
