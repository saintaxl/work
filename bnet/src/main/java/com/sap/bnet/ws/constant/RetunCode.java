/**
 * 
 */
package com.sap.bnet.ws.constant;

/**
 * @author Shawn
 *
 */
public enum RetunCode {
	
	/**
	 * General code
	 */
	
	SUCCESSFUL("00000"),
	
	FAILURE("00001"),
	
	PARAMETER_NOT_ENOUGH("00002"),
	
	NOT_BUSINESS_TIME("00003"),
	
	UNAUTHORIZED("00004"),
	
	QUERY_RESULT_EMPTY("00005"),
	
	SYS_NOT_BUSINESS("00006"),
	
	PASSWORD_ERROR("00008"),
	
	/**
	 * DB code
	 */
	QUERY_DB_ERROR("00201"),
	
	INSERT_DATA_ERROR("00202"),
	
	UPDATE_DATA_ERROR("00203"),
	
	DEL_DATA_ERROR("00204"),
	
	STORED_PROCEDURE_ERROR("00205"),
	
	/**
	 * Business logic code
	 */
	
	REQUIRED_EMPTY("00401"),
	
	BUSINESS_DATA_EMPTY("00402"),
	
	DATA_FORMAT_ERROR("00403"),
	
	BUSINESS_LOGIC_ERROR("00404"),
	
	USER_UNBUND("00405"),
	
	BUND_LICENSE_OUT_LIMIT("00406"),
	
	/**
	 * Network code
	 */
	
	CONNECT_SERVER_ERROR("00601"),
	
	CLOSE_CONNECT_ERROR("00602"),
	
	SEND_DATA_ERROR("00603"),
	
	RECEIVE_DATA_ERROR("00604"),
	
	/**
	 * Product code
	 */
	
	CUSTID_ERROR("00701"),
	
	PRODUCTID_ERROR("00702"),
	
	AREACODE_ERROR("00703"),
	
	MDN_ERROR("00704"),
	
	MDN_EXISTS("00705"),
	
	MDN_NOT_PROVISIONING("00706"),
	
	MATCHUP_USED("00707"),
	
	MDN_NOT_EXISTS("00708"),
	
	IMSI_USED("00709"),
	
	ICCID_USED("00710"),
	
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
