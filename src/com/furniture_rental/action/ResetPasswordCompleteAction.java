package com.furniture_rental.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware{
private Map<String, Object> session;
	public String execute(){
	String result=ERROR;
	UserInfoDAO dao=new UserInfoDAO();

	int count=dao.resetPassword(session.get("rePassword").toString(), session.get("newPassword").toString());
	if(count>0){
		result=ERROR;
	}
	
	
	session.remove("rePassword");
	session.remove("newPassword");
	return result;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
