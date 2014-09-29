package com.sap.bnet.services;

import java.util.Map;

import com.sap.bnet.constant.Operator;

public interface IOrderServices {
	
	public Map<Operator,Object> addOrder(String streamingNo,String rand,String encode);

}
