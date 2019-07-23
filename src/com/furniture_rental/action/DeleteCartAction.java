package com.furniture_rental.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CartInfoDAO;
import com.furniture_rental.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware{
	private String userId;
	private int productId;
	private int productCount;
	private String[] checkbox;
	private List<CartInfoDTO> cartInfoDTOList;
	private Map<String, Object> session;
	private int totalPrice;

	public String execute(){
		String result=ERROR;
		if(!session.containsKey("userId")&& session.containsKey("temoUserId")){
			return "sessionTimeout";
		}
		if((int)session.get("logined")==1){
			userId=session.get("userId").toString();
		}else{
			userId=session.get("tempUserId").toString();
		}

		CartInfoDAO cartInfoDAO=new CartInfoDAO();
		int count=0;

		//削除対象を代入(checkbox>>productId)
		for(String productId: checkbox){
			count+=cartInfoDAO.deleteCartInfo(userId, Integer.parseInt(productId));
		}

		//上の処理終了後userIdに紐づけて対象のユーザーの対象を消す
		if(count == checkbox.length){
			cartInfoDTOList=cartInfoDAO.getCartInfoDTOList(userId);
			totalPrice=cartInfoDAO.getTotalPrice(userId);

			result=SUCCESS;
		}
		return result;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public List<CartInfoDTO> getCartInfoDTOList() {
		return cartInfoDTOList;
	}

	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList) {
		this.cartInfoDTOList = cartInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
