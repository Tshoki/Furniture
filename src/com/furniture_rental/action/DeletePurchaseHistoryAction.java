package com.furniture_rental.action;

import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.furniture_rental.dao.PurchaseHistoryInfoDAO;
import com.furniture_rental.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{
	private List<PurchaseHistoryInfoDTO> purcahasedList;
	private Map<String, Object> session;
	public String execute(){
		if(!session.containsKey("tempUserId")&& !session.containsKey("userId")){
			return "sessionTimeout";
		}

		String result=ERROR;
		PurchaseHistoryInfoDAO purchasedInfo=new  PurchaseHistoryInfoDAO();
		int count=purchasedInfo.deleteAll(String.valueOf(session.get("userId")));

		if(count>0){
			purcahasedList=purchasedInfo.getPurchaseHistoryList(String.valueOf(session.get("userId")));
			result=SUCCESS;
		}
		return result;
	}
	public List<PurchaseHistoryInfoDTO> getPurcahasedList() {
		return purcahasedList;
	}
	public void setPurcahasedList(List<PurchaseHistoryInfoDTO> purcahasedList) {
		this.purcahasedList = purcahasedList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
