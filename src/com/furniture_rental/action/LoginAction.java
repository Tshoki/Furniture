package com.furniture_rental.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CartInfoDAO;
import com.furniture_rental.dao.UserInfoDAO;
import com.furniture_rental.dto.CartInfoDTO;
import com.furniture_rental.dto.UserInfoDTO;
import com.furniture_rental.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private String userId;
	private String password;
	private boolean savedUserIdFlg; //
	private String notFoundUserMessage;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<CartInfoDTO> cartInfoDTOList;
	private int totalPrice;
	private Map<String, Object> session;

	public String execute() {
		// セッションタイムアウト
		if (!session.containsKey("tempUserId")) {
			return "sessionTimeout";
		}

		// ユーザー登録画面から遷移
		if (!session.containsKey("createUserId")) {
			userId = session.get("createUserId").toString();
			password = session.get("password").toString();
			session.remove("createUserId");
			session.remove("password");
		}

		if (savedUserIdFlg) {
			session.put("savedUserFlg", true);
			session.put("savedUserId", userId);
		} else {
			session.remove("savedUserIdFlg");
			session.remove("password");
		}

		String result = ERROR;
		InputChecker ic = new InputChecker();

		// 半角英字、漢字、ひらがな、半角数字、カタカナ、スペース
		userIdErrorMessageList = ic.docCheck("ユーザーID", userId, 1, 16, true, true, true, false, true, false);
		passwordErrorMessageList = ic.docCheck("パスワード", password, 1, 16, true, true, true, false, true, false);

		// エラー判定時
		if (userIdErrorMessageList.size() > 0 || passwordErrorMessageList.size() > 0) {
			session.put("logined", 0);
			return result;
		}

		UserInfoDAO userDAO = new UserInfoDAO();
		// 存在確認 + ログイン認証
		if (userDAO.existsUser(userId, password) && userDAO.logined(userId, password) > 0) {
			// 紐づけ
			CartInfoDAO cartDAO = new CartInfoDAO();
			String tempUserId = session.get("tempUserId").toString();
			List<CartInfoDTO> cartInfoForTempUserId = cartDAO.getCartInfoDTOList(tempUserId);

			if (cartInfoForTempUserId != null) {
				boolean relate = LinkToCart(tempUserId, cartInfoForTempUserId);
				if (!relate) {
					result = "DBEror";
				}
			}
				if (session.containsKey("cartFlg")) {
					result = "cart";
					session.remove("cartFlg");
				} else {
					result = SUCCESS;
				}
				UserInfoDTO userDTO = new UserInfoDTO();
				session.put("userId", userDTO.getUserId());
				session.put("logined", 1);
				session.remove("tempUserId");

			} else {
				notFoundUserMessage = "ユーザーIDまたはパスワードが異なります。";
			}
			return result;
		}
		public boolean LinkToCart(String tempUserId, List<CartInfoDTO> cartInfoForTempUser){
			boolean result=false;
			int count=0;
			CartInfoDAO cartDAO=new CartInfoDAO();

			for(CartInfoDTO dto: cartInfoForTempUser){
				if(cartDAO.existsSameProduct(userId, dto.getProductId())){
					cartDAO.deleteCartInfo(tempUserId, dto.getProductId());
					count = count + cartDAO.countUpdate(userId, dto.getProductId(), dto.getProductCount());
				}else{
					count = count + cartDAO.updateUser(userId, tempUserId, dto.getProductId());
				}
			}
			if(cartInfoForTempUser.size() == count){
				cartInfoDTOList = cartDAO.getCartInfoDTOList(userId);
				totalPrice = cartDAO.getTotalPrice(userId);
				result = true;
			}
			return result;
			}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isSavedUserIdFlg() {
			return savedUserIdFlg;
		}
		public void setSavedUserIdFlg(boolean savedUserIdFlg) {
			this.savedUserIdFlg = savedUserIdFlg;
		}
		public String getNotFoundUserMessage() {
			return notFoundUserMessage;
		}
		public void setNotFoundUserMessage(String notFoundUserMessage) {
			this.notFoundUserMessage = notFoundUserMessage;
		}
		public List<String> getUserIdErrorMessageList() {
			return userIdErrorMessageList;
		}
		public void setUserIdErrorMessageList(List<String> userIdErrorMessageList) {
			this.userIdErrorMessageList = userIdErrorMessageList;
		}
		public List<String> getPasswordErrorMessageList() {
			return passwordErrorMessageList;
		}
		public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
			this.passwordErrorMessageList = passwordErrorMessageList;
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
		public Map<String, Object> getSession() {
			return session;
		}
		public void setSession(Map<String, Object> session) {
			this.session = session;
		}
}