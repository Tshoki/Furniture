package com.furniture_rental.action;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.UserInfoDAO;
import com.furniture_rental.util.CommonUtility;
import com.furniture_rental.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;
public class CreateUserConfirmAction extends ActionSupport implements SessionAware {
		//ユーザーの入力情報
		private String familyName;
		private String firstName;
		private String familyNameKana;
		private String firstNameKana;
		private String sex;
		private String email;
		private String userId;
		private String password;

		private Map<String,Object> session;

		//入力チェックでエラーが発生した際に出力するメッセージ
		private List<String> familyNameErrorMessageList;
		private List<String> firstNameErrorMessageList;
		private List<String> familyNameKanaErrorMessageList;
		private List<String> firstNameKanaErrorMessageList;
		private List<String> emailErrorMessageList;
		private List<String> userIdErrorMessageList;
		private List<String> passwordErrorMessageList;
		private String isExistsUserErrorMessage;

		public String execute(){

			//セッション変数tempUserIdが存在しなければ、sessionTimeoutを返し、sessionError.jspへ遷移
			if(!session.containsKey("tempUserId")){
				return "sessionTimeout";
			}

			String result=ERROR;

			//セッション変数にユーザーの入力情報を格納
			session.put("familyName", familyName);
			session.put("firstName", firstName);
			session.put("familyNameKana", familyNameKana);
			session.put("firstNameKana", firstNameKana);
			session.put("sex", sex);
			session.put("email", email);
			session.put("userIdForCreateUser", userId);
			session.put("password", password);

			//文字チェック用クラスのインスタンス化
			InputChecker ic=new InputChecker();

			//文字チェックの結果のメッセージを格納
			familyNameErrorMessageList=ic.docCheck("姓",familyName,1,16,true,true,true,false,false,false);
			firstNameErrorMessageList=ic.docCheck("名",firstName,1,16,true,true,true,false,false,false);
			familyNameKanaErrorMessageList=ic.docCheck("姓ふりがな",familyNameKana,1,16,false,false,true,false,false,false);
			firstNameKanaErrorMessageList=ic.docCheck("名ふりがな",firstNameKana,1,16,false,false,true,false,false,false);
			emailErrorMessageList=ic.emailCheck("メールアドレス",email,10,32);
			userIdErrorMessageList=ic.docCheck("ユーザーID",userId,1,8,true,false,false,true,false,false);
			passwordErrorMessageList=ic.docCheck("パスワード",password,1,16,true,false,false,true,false,false);

			//エラーメッセージのListが一件以上あればERRORを返し、createUser.jspへ
			if(familyNameErrorMessageList.size()>0
			|| firstNameErrorMessageList.size()>0
			|| familyNameKanaErrorMessageList.size()>0
			|| firstNameKanaErrorMessageList.size()>0
			|| emailErrorMessageList.size()>0
			|| userIdErrorMessageList.size()>0
			|| passwordErrorMessageList.size()>0){
				return result;
			}

			UserInfoDAO userInfoDAO=new UserInfoDAO();
			//入力したユーザーIDが既に登録されていないかをチェックする
			if(userInfoDAO.isExistsUserInfo(userId)){
				isExistsUserErrorMessage="使用できないユーザーIDです。";
			}else{
				//パスワードのセキュリティ対策
				CommonUtility cu=new CommonUtility();
				password=cu.hiddenPass(password);
				result=SUCCESS;
			}
			return result;

		}

		public String getFamilyName(){
			return familyName;
		}

		public void setFamilyName(String familyName){
			this.familyName=familyName;
		}

		public String getFirstName(){
			return firstName;
		}

		public void setFirstName(String firstName){
			this.firstName=firstName;
		}

		public String getFamilyNameKana(){
			return familyNameKana;
		}

		public void setFamilyNameKana(String familyNameKana){
			this.familyNameKana=familyNameKana;
		}

		public String getFirstNameKana(){
			return firstNameKana;
		}

		public void setFirstNameKana(String firstNameKana){
			this.firstNameKana=firstNameKana;
		}

		public String getSex(){
			return sex;
		}

		public void setSex(String sex){
			this.sex=sex;
		}

		public String getEmail(){
			return email;
		}

		public void setEmail(String email){
			this.email=email;
		}

		public String getUserId(){
			return userId;
		}

		public void setUserId(String userId){
			this.userId=userId;
		}

		public String getPassword(){
			return password;
		}

		public void setPassword(String password){
			this.password=password;
		}

		public Map<String,Object> getSession(){
			return session;
		}

		@Override
		public void setSession(Map<String,Object> session){
			this.session=session;
		}

		public List<String> getFamilyNameErrorMessageList(){
			return familyNameErrorMessageList;
		}

		public void setfamilyNameErrorMessageList(List<String> familyNameErrorMessageList){
			this.familyNameErrorMessageList=familyNameErrorMessageList;
		}

		public List<String> getFirstNameErrorMessageList(){
			return firstNameErrorMessageList;
		}

		public void setFirstNameErrorMessageList(List<String> firstNameErrorMessageList){
			this.firstNameErrorMessageList=firstNameErrorMessageList;
		}

		public List<String> getFamilyNameKanaErrorMessageList(){
			return familyNameKanaErrorMessageList;
		}

		public void setFamilyNameKanaErrorMessageList(List<String> familyNameKanaErrorMessageList){
			this.familyNameKanaErrorMessageList=familyNameKanaErrorMessageList;
		}

		public List<String> getFirstNameKanaErrorMessageList(){
			return firstNameKanaErrorMessageList;
		}

		public void setFirstNameKanaErrorMessageList(List<String> firstNameKanaErrorMessageList){
			this.firstNameKanaErrorMessageList=firstNameKanaErrorMessageList;
		}

		public List<String> getEmailErrorMessageList(){
			return emailErrorMessageList;
		}

		public void setEmailErrorMessageList(List<String> emailErrorMessageList){
			this.emailErrorMessageList=emailErrorMessageList;
		}

		public List<String> getUserIdErrorMessageList(){
			return userIdErrorMessageList;
		}

		public void setUserIdErrorMessageList(List<String> userIdErrorMessageList){
			this.userIdErrorMessageList=userIdErrorMessageList;
		}

		public List<String> getPasswordErrorMessageList(){
			return passwordErrorMessageList;
		}

		public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
			this.passwordErrorMessageList=passwordErrorMessageList;
		}

		public String getIsExistsUserErrorMessage(){
			return isExistsUserErrorMessage;
		}

		public void setIsExistsUserErrorMessage(String isExistsUserErrorMessage){
			this.isExistsUserErrorMessage=isExistsUserErrorMessage;
		}
	}
