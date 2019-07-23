package com.furniture_rental.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.DestinationInfoDAO;
import com.furniture_rental.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware{
private int id;
private List<DestinationInfoDTO> destinationInfoDTOList;
private Map<String, Object> session;

public String execute(){
	if(!session.containsKey("tempUserId")&&session.containsKey("userId")){
		return "sessionTimeout";
	}
	String result=ERROR;
	DestinationInfoDAO destinationInfoDAO=new DestinationInfoDAO();
	//idで紐づけられたデータを消すため
	int count=destinationInfoDAO.deleteDestination(id);

	//削除対象が存在するとき
	if(count >0){
		setDestinationInfoDTOList(destinationInfoDAO.getDestinationInfo(session.get("userId").toString()));
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

public List<DestinationInfoDTO> getDestinationInfoDTOList() {
	return destinationInfoDTOList;
}

public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
	this.destinationInfoDTOList = destinationInfoDTOList;
}

}
