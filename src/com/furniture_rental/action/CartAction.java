package com.furniture_rental.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CartInfoDAO;
import com.furniture_rental.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware{
	private String userId;
	private Map<String, Object> session;
	private List<CartInfoDTO> cartInfoDTOList;
	private int totalPrice;

	public String execute(){
		String result=SUCCESS;

		if(!session.containsKey("tempUserId")&& !session.containsKey("userId")){
			return "sessionTimeout";
		}

		//ログイン時
		if((int)session.get("logined")==1){
			userId=session.get("userId").toString();
		}else{
			userId=session.get("tempUserId").toString();
		}

		CartInfoDAO cartInfoDAO=new CartInfoDAO();

		//カート情報の抽出
		cartInfoDTOList=cartInfoDAO.getCartInfoDTOList(userId);
		//合計金額
		totalPrice=cartInfoDAO.getTotalPrice(userId);

		return result;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<CartInfoDTO> getCartInfoDTOList() {
		return cartInfoDTOList;
	}

	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList) {
		this.cartInfoDTOList = cartInfoDTOList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
