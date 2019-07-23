package com.furniture_rental.action;


import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.PurchaseHistoryInfoDAO;
import com.furniture_rental.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{
private List<PurchaseHistoryInfoDTO> purchaseInfoList;
private Map<String, Object> session;

public String execute(){
	if(!session.containsKey("userId")&&!session.containsKey("tempUserid")){
		return "sessionTimeout";
	}

	PurchaseHistoryInfoDAO purchasehistoryInfoDAO=new PurchaseHistoryInfoDAO();
	purchaseInfoList=purchasehistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("userId")));
	return SUCCESS;
}

public List<PurchaseHistoryInfoDTO> getPurchaseInfoList() {
	return purchaseInfoList;
}

public void setPurchaseInfoList(List<PurchaseHistoryInfoDTO> purchaseInfoList) {
	this.purchaseInfoList = purchaseInfoList;
}

public Map<String, Object> getSession() {
	return session;
}

public void setSession(Map<String, Object> session) {
	this.session = session;
}

}
