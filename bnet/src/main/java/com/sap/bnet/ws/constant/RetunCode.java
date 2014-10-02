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
public enum RetunCode {
	
	/**
	 * General code
	 */
	@XmlEnumValue("00000")
	SUCCESSFUL("00000"),
	
	@XmlEnumValue("00001")
	FAILURE("00001"),
	
	@XmlEnumValue("00002")
	PARAMETER_NOT_ENOUGH("00002"),
	
	@XmlEnumValue("00003")
	NOT_BUSINESS_TIME("00003"),
	
	@XmlEnumValue("00004")
	UNAUTHORIZED("00004"),
	
	@XmlEnumValue("00005")
	QUERY_RESULT_EMPTY("00005"),
	
	@XmlEnumValue("00006")
	SYS_NOT_BUSINESS("00006"),
	
	@XmlEnumValue("00008")
	PASSWORD_ERROR("00008"),
	
	/**
	 * DB code
	 */
	@XmlEnumValue("00201")
	QUERY_DB_ERROR("00201"),
	
	@XmlEnumValue("00202")
	INSERT_DATA_ERROR("00202"),
	
	@XmlEnumValue("00203")
	UPDATE_DATA_ERROR("00203"),
	
	@XmlEnumValue("00204")
	DEL_DATA_ERROR("00204"),
	
	@XmlEnumValue("00205")
	STORED_PROCEDURE_ERROR("00205"),
	
	/**
	 * Business logic code
	 */
	
	@XmlEnumValue("00401")
	REQUIRED_EMPTY("00401"),
	
	@XmlEnumValue("00402")
	BUSINESS_DATA_EMPTY("00402"),
	
	@XmlEnumValue("00403")
	DATA_FORMAT_ERROR("00403"),
	
	@XmlEnumValue("00404")
	BUSINESS_LOGIC_ERROR("00404"),
	
	@XmlEnumValue("00405")
	USER_UNBUND("00405"),
	
	@XmlEnumValue("00406")
	BUND_LICENSE_OUT_LIMIT("00406"),
	
	/**
	 * encode not match
	 */
	
	@XmlEnumValue("00501")
	VERIFY_ERROR("00501"),
	
	/**
	 * Network code
	 */
	
	@XmlEnumValue("00601")
	CONNECT_SERVER_ERROR("00601"),
	
	@XmlEnumValue("00602")
	CLOSE_CONNECT_ERROR("00602"),
	
	@XmlEnumValue("00603")
	SEND_DATA_ERROR("00603"),
	
	@XmlEnumValue("00604")
	RECEIVE_DATA_ERROR("00604"),
	
	/**
	 * Product code
	 */
	@XmlEnumValue("00701")
	CUSTID_ERROR("00701"),
	
	@XmlEnumValue("00702")
	PRODUCTID_ERROR("00702"),
	
	@XmlEnumValue("00703")
	AREACODE_ERROR("00703"),
	
	@XmlEnumValue("00704")
	MDN_ERROR("00704"),
	
	@XmlEnumValue("00705")
	MDN_EXISTS("00705"),
	
	@XmlEnumValue("00706")
	MDN_NOT_PROVISIONING("00706"),
	
	@XmlEnumValue("00707")
	MATCHUP_USED("00707"),
	
	@XmlEnumValue("00708")
	MDN_NOT_EXISTS("00708"),
	
	@XmlEnumValue("00709")
	IMSI_USED("00709"),
	
	@XmlEnumValue("00710")
	ICCID_USED("00710"),
	
	@XmlEnumValue("00711")
	UIMID_USED("00711"),
	
	
	
	;
	
	private String code ;
	
	private RetunCode(String code) {
		this.code = code;
	}
	
	public static RetunCode getNameByCode(String code) {
		for (RetunCode codes : RetunCode.values()) {
			if (codes.code.equals(code)) {
				return codes;
			}
		}
		throw new IllegalArgumentException("Illegal RetunCode code: " + code);
	}
	
	public static RetunCode getCodeByName(int name) {
		for (RetunCode codes : RetunCode.values()) {
			if (codes.name().equals(name)) {
				return codes;
			}
		}
		throw new IllegalArgumentException("Illegal RetunCode name: " + name);
	}

}
