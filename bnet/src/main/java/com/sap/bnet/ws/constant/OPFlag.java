/**
 * 
 */
package com.sap.bnet.ws.constant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author Shawn
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public enum OPFlag {
	
	@XmlEnumValue("0101")
	CUST_OPEN_PRODUCT("0101","客户开通"),
	
	@XmlEnumValue("0102")
	CUST_CHANGE_PRODUCT("0102","客户变更"),
	
	@XmlEnumValue("0103")
	CUST_UNSUBSCRIBE_PRODUCT("0103","客户退订"),
	
	@XmlEnumValue("0104")
	CUST_QUERY("0104","客户查询"),
	
	@XmlEnumValue("0201")
	USER_BOUND_PRODUCT("0201","用户绑定"),
	
	@XmlEnumValue("0202")
	USER_CHANGE_PRODUCT("0202","用户变更"),
	
	@XmlEnumValue("0203")
	USER_UNBOUND_PRODUCT("0203","用户退订"),
	
	@XmlEnumValue("0204")
	USER_QUERY("0204","用户查询"),
	
	@XmlEnumValue("0205")
	AUTHENTICATION("0205","平台用户认证"),
	
	@XmlEnumValue("0206")
	LOGINON("0206","用户登录"),
	
	;
	
	private String code ;
	
	private String description;
	
	private OPFlag(String code,String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public static OPFlag getNameByCode(String code) {
		for (OPFlag codes : OPFlag.values()) {
			if (codes.code.equals(code)) {
				return codes;
			}
		}
		throw new IllegalArgumentException("Illegal OPFlag code: " + code);
	}
	
	public static OPFlag getCodeByName(int name) {
		for (OPFlag codes : OPFlag.values()) {
			if (codes.name().equals(name)) {
				return codes;
			}
		}
		throw new IllegalArgumentException("Illegal OPFlag name: " + name);
	}

}
