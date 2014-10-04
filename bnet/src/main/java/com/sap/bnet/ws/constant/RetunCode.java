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
	SUCCESSFUL("00000","交易成功"),
	
	@XmlEnumValue("00001")
	FAILURE("00001","交易失败"),
	
	@XmlEnumValue("00002")
	PARAMETER_NOT_ENOUGH("00002","参数要素不全"),
	
	@XmlEnumValue("00003")
	NOT_BUSINESS_TIME("00003","非业务时间"),
	
	@XmlEnumValue("00004")
	UNAUTHORIZED("00004","业务未授权"),
	
	@XmlEnumValue("00005")
	QUERY_RESULT_EMPTY("00005","查询结果为空"),
	
	@XmlEnumValue("00006")
	SYS_NOT_BUSINESS("00006","系统无此业务"),
	
	@XmlEnumValue("00008")
	PASSWORD_ERROR("00008","查询密码错误"),
	
	/**
	 * DB code
	 */
	@XmlEnumValue("00201")
	QUERY_DB_ERROR("00201","查询数据库异常"),
	
	@XmlEnumValue("00202")
	INSERT_DATA_ERROR("00202","插入数据异常"),
	
	@XmlEnumValue("00203")
	UPDATE_DATA_ERROR("00203","修改数据异常"),
	
	@XmlEnumValue("00204")
	DEL_DATA_ERROR("00204","删除数据异常"),
	
	@XmlEnumValue("00205")
	STORED_PROCEDURE_ERROR("00205","过程执行异常"),
	
	/**
	 * Business logic code
	 */
	
	@XmlEnumValue("00401")
	REQUIRED_EMPTY("00401","关键数据项不能为空"),
	
	@XmlEnumValue("00402")
	BUSINESS_DATA_EMPTY("00402","业务数据内容不存在"),
	
	@XmlEnumValue("00403")
	DATA_FORMAT_ERROR("00403","数据格式不正确"),
	
	@XmlEnumValue("00404")
	BUSINESS_LOGIC_ERROR("00404","业务逻辑执行错误"),
	
	@XmlEnumValue("00405")
	USER_UNBUND("00405","有用户未解绑"),
	
	@XmlEnumValue("00406")
	BUND_LICENSE_OUT_LIMIT("00406","绑定用户已超上限"),
	
	/**
	 * encode not match
	 */
	
	@XmlEnumValue("00501")
	VERIFY_ERROR("00501","加密数据包验证错误"),
	
	/**
	 * Network code
	 */
	
	@XmlEnumValue("00601")
	CONNECT_SERVER_ERROR("00601","连接服务异常"),
	
	@XmlEnumValue("00602")
	CLOSE_CONNECT_ERROR("00602","断开服务异常"),
	
	@XmlEnumValue("00603")
	SEND_DATA_ERROR("00603","发送数据失败"),
	
	@XmlEnumValue("00604")
	RECEIVE_DATA_ERROR("00604","接收数据失败"),
	
	/**
	 * Product code
	 */
	@XmlEnumValue("00701")
	CUSTID_ERROR("00701","CustID错误"),
	
	@XmlEnumValue("00702")
	PRODUCTID_ERROR("00702","ProductID错误"),
	
	@XmlEnumValue("00703")
	AREACODE_ERROR("00703","AreaCode不存在"),
	
	@XmlEnumValue("00704")
	MDN_ERROR("00704","MDN错误"),
	
	@XmlEnumValue("00705")
	MDN_EXISTS("00705","MDN已存在"),
	
	@XmlEnumValue("00706")
	MDN_NOT_PROVISIONING("00706","MDN未开通"),
	
	@XmlEnumValue("00707")
	MATCHUP_USED("00707","MDN与CustID对应关系错误"),
	
	@XmlEnumValue("00708")
	MDN_NOT_EXISTS("00708","MDN不存在"),
	
	@XmlEnumValue("00709")
	IMSI_USED("00709","IMSI已使用"),
	
	@XmlEnumValue("00710")
	ICCID_USED("00710","ICCID已使用"),
	
	@XmlEnumValue("00711")
	UIMID_USED("00711","UIMID已使用"),
	
	@XmlEnumValue("00712")
	MATCHUP_CUSTID("00712","MDN与CustID对应关系错误"),
	
	
	
	;
	
	private String code ;
	
	private String description;
	
	private RetunCode(String code,String description) {
		this.code = code;
		this.description = description;
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
