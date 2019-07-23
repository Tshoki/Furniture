package com.furniture_rental.action;

import java.util.List;
import java.util.Map;
import com.furniture_rental.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class CreateDestinationConfirmAction extends ActionSupport implements SessionAware {
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String userAddress;
	private String telNumber;
	private String email;

	private List<String> familyNameErrorMessageList; // 結果はListで返される
	private List<String> firstNameErrorMessageList;
	private List<String> familyNameKanaErrorMessageList;
	private List<String> firstNameKanaErrorMessageList;
	private List<String> userAddressErrorMessageList;
	private List<String> telNumberErrorMessageList;
	private List<String> emailErrorMessageList;

	private Map<String, Object> session;

	public String execute() {
		if (!session.containsKey("tempUserId") && !session.containsKey("userId")) { // SessionTimeOut
			return "sessionTimeout";
		}
		String result = ERROR;
		session.put("familyName", familyName);
		session.put("firstName", firstName);
		session.put("familyNameKana", familyNameKana);
		session.put("firstNameKana", firstNameKana);
		session.put("userAddress", userAddress);
		session.put("telNumber", telNumber);
		session.put("email", email);

		InputChecker ic = new InputChecker();
		// フォームの内容ﾁｪｯｸ(項目名、値、最小文字数、最大文字数、半角英字、漢字、ひらがな、半角数字、カタカナ、スペース)
		familyNameErrorMessageList = ic.docCheck("姓", familyName, 1, 16, true, true, true, false, false, false);
		firstNameErrorMessageList = ic.docCheck("名", firstName, 1, 16, true, true, true, false, false, false);
		familyNameKanaErrorMessageList = ic.docCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false,
				false);
		firstNameKanaErrorMessageList = ic.docCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false,
				false);
		userAddressErrorMessageList = ic.docCheck("住所", userAddress, 10, 50, true, true, true, true, true, false);
		telNumberErrorMessageList = ic.docCheck("電話", telNumber, 10, 13, false, false, false, true, false, false);
		// （項目名、値、最小文字数、最大文字数）
		emailErrorMessageList = ic.emailCheck("メールアドレス", email, 10, 32);

		// エラーメッセージの有無
		if (familyNameErrorMessageList.size() > 0 || firstNameErrorMessageList.size() > 0
				|| familyNameKanaErrorMessageList.size() > 0 || firstNameKanaErrorMessageList.size() > 0
				|| userAddressErrorMessageList.size() > 0 || telNumberErrorMessageList.size() > 0
				|| emailErrorMessageList.size() > 0) {
			result = ERROR;
		} else {
			result = SUCCESS;
		}
		return result;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	////////////////// errorMessageList//////////////////////////////
	public List<String> getFamilyNameErrorMessageList() {
		return familyNameErrorMessageList;
	}

	public void setFamilyNameErrorMessageList(List<String> familyNameErrorMessageList) {
		this.familyNameErrorMessageList = familyNameErrorMessageList;
	}

	public List<String> getFirstNameErrorMessageList() {
		return firstNameErrorMessageList;
	}

	public void setFirstNameErrorMessageList(List<String> firstNameErrorMessageList) {
		this.firstNameErrorMessageList = firstNameErrorMessageList;
	}

	public List<String> getFamilyNameKanaErrorMessageList() {
		return familyNameKanaErrorMessageList;
	}

	public void setFamilyNameKanaErrorMessageList(List<String> familyNameKanaErrorMessageList) {
		this.familyNameKanaErrorMessageList = familyNameKanaErrorMessageList;
	}

	public List<String> getFirstNameKanaErrorMessageList() {
		return firstNameKanaErrorMessageList;
	}

	public void setFirstNameKanaErrorMessageList(List<String> firstNameKanaErrorMessageList) {
		this.firstNameKanaErrorMessageList = firstNameKanaErrorMessageList;
	}

	public List<String> getUserAddressErrorMessageList() {
		return userAddressErrorMessageList;
	}

	public void setUserAddressErrorMessageList(List<String> userAddressErrorMessageList) {
		this.userAddressErrorMessageList = userAddressErrorMessageList;
	}

	public List<String> getTelNumberErrorMessageList() {
		return telNumberErrorMessageList;
	}

	public void setTelNumberErrorMessageList(List<String> telNumberErrorMessageList) {
		this.telNumberErrorMessageList = telNumberErrorMessageList;
	}

	public List<String> getEmailErrorMessageList() {
		return emailErrorMessageList;
	}

	public void setEmailErrorMessageList(List<String> emailErrorMessageList) {
		this.emailErrorMessageList = emailErrorMessageList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
