package com.furniture_rental.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() {
		String userId = String.valueOf(session.get("userId"));
		boolean savedUserId = Boolean.valueOf(String.valueOf(session.get("savedUserId")));

		UserInfoDAO dao = new UserInfoDAO();
		int count = dao.logout(userId);

		if (count > 0) {
			session.clear();
			if (savedUserId) {
				session.put("savedUserIdFlg", savedUserId);
				session.put("savedUserId", userId);
			}
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
