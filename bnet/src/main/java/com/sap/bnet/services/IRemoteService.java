package com.sap.bnet.services;

import com.sap.bnet.ws.model.PackageElement;


public interface IRemoteService {
	
	public PackageElement getPortalRequest(String streamingNo,String rand,String encode);

	public PackageElement queryCustomer(String streamingNo,PackageElement portalRequest);

	public PackageElement queryUserInfo(String streamingNo, PackageElement portalRequest);

	public PackageElement getAuthentication(String streamingNo, PackageElement portalRequest);

}
