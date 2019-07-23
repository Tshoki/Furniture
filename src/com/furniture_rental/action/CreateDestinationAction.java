package com.furniture_rental.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;

public class CreateDestinationAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private String backFlg;
		public String execute(){
			//SessionTimeOut
			if(!session.containsKey("tempUserId") && !session.containsKey("userId")){
				return "sessionTimeout";
			}

			//先に進む場合
			if(backFlg == null){
				session.remove("familyName");
				session.remove("firstName");
				session.remove("familyNameKana");
				session.remove("firstNameKana");
				session.remove("userAddress");
				session.remove("telNumber");
				session.remove("email");
			}
			return SUCCESS;
		}

		public String getBackFlg(){
			return backFlg;
		}
		public void setBackFlg(String backFlg){
			this.backFlg=backFlg;
		}
		public Map<String, Object> getSession(){
			return session;
		}
		public void setSession(Map<String, Object> session){
			this.session=session;
		}
}
