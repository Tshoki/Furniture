package com.furniture_rental.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CartInfoDAO;
import com.furniture_rental.dao.DestinationInfoDAO;
import com.furniture_rental.dto.CartInfoDTO;
import com.furniture_rental.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {
	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String, Object> session;

	public String execute() {
		String result = ERROR;
		String userId = null;

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();

		if((int)session.get("logined")==1){
			userId=session.get("userId").toString();
			//userIdを使ってカート情報を検索
			cartInfoDTOList=cartInfoDAO.getCartInfoDTOList(userId);
			session.put("cartInfoDTOList", cartInfoDTOList);

			//住所情報の紐づけ
			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
    		destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(userId);

    		result = SUCCESS;
		}else{
			//未ログイン状態>>ログイン画面に遷移
			session.put("cartFlg", "1");
			result ="logined";
		}
		return result;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}