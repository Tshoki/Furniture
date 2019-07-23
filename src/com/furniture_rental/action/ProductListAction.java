package com.furniture_rental.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.furniture_rental.dao.CategoryDAO;
import com.furniture_rental.dao.ProductInfoDAO;
import com.furniture_rental.dto.CategoryDTO;
import com.furniture_rental.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;


public class ProductListAction extends ActionSupport implements SessionAware{
private List<ProductInfoDTO> productInfoList;
private Map<String, Object> session;

public String execute(){
	ProductInfoDAO productInfo=new ProductInfoDAO();
	productInfoList=productInfo.getProductInfoListAll();

	/*カテゴリ選ばなかった場合
	 * nullをいれる
	 * */
	if(!session.containsKey("categoryList")){
		List<CategoryDTO> categoryList=new ArrayList<CategoryDTO>();
		CategoryDAO category=new CategoryDAO();
		try{
			//dtoにカテゴリ情報を送る
			categoryList=category.getCategoryList();
		}catch (NullPointerException e) {
			categoryList = null;
		}
		session.put("categoryList", categoryList);
	}
	return SUCCESS;
}

public List<ProductInfoDTO> getProductInfoList() {
	return productInfoList;
}

public void setProductInfoList(List<ProductInfoDTO> productInfoList) {
	this.productInfoList = productInfoList;
}

public Map<String, Object> getSession() {
	return session;
}

public void setSession(Map<String, Object> session) {
	this.session = session;
}

}
