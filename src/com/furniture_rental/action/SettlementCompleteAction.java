package com.furniture_rental.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CartInfoDAO;
import com.furniture_rental.dao.PurchaseHistoryInfoDAO;
import com.furniture_rental.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware {
	private int id;
	private Map<String, Object> session;

	public String execute(){
	if(!session.containsKey("tempUserId")&&!session.containsKey("userId"))
	{
		return "sessionTimeout";
	}
	String result = ERROR;

	String userId = session.get("userId").toString();
	@SuppressWarnings("unchecked")

	List<CartInfoDTO> cartInfoDTOList = (List<CartInfoDTO>) session.get("cartInfoDTOList");

	PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
	int count = 0;for(CartInfoDTO dto:cartInfoDTOList){
		count += purchaseHistoryInfoDAO.regist(userId, dto.getProductId(), dto.getProductCount(), dto.getPrice(), id);
	}
	if(count>0){
		CartInfoDAO cartInfoDAO=new CartInfoDAO();
		count=cartInfoDAO.deleteAll(String.valueOf(session.get(userId)));
		result=SUCCESS;
	}
	return result;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}