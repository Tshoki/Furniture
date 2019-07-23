package com.furniture_rental.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.furniture_rental.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import com.furniture_rental.dto.CategoryDTO;
import com.furniture_rental.dao.CategoryDAO;

public class HomeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute() {

		if (!session.containsKey("userId")) {
			CommonUtility common = new CommonUtility();
			session.put("tempUserId", common.getRadomValue());
		}
		// ログイン
		if (!session.containsKey("logined")) {
			session.put("logined", 0);
		}
		// 検索
		if (!session.containsKey("categoryList")) {
			List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
			CategoryDAO categoryDAO = new CategoryDAO();
			try {
				categoryList = categoryDAO.getCategoryList();
			} catch (NullPointerException e) {
				categoryList = null;
			}
			session.put("categoryList", categoryList);
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
