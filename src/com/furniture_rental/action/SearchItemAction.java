package com.furniture_rental.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.CategoryDAO;
import com.furniture_rental.dao.ProductInfoDAO;
import com.furniture_rental.dto.CategoryDTO;
import com.furniture_rental.dto.ProductInfoDTO;
import com.furniture_rental.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware{
private String categoryId;
private String keywords;
private List<String> keywordsErrorMessageList;
private List<ProductInfoDTO> productInfoDTOList;
private Map<String, Object> session;

public String execute(){
	if(categoryId == null){
		categoryId="1";
	}
	InputChecker ic=new InputChecker();

	if(StringUtils.isBlank(keywords)){
		keywords="";
	}else{
		//全角空白／空白２個以上を半角スペースとみなす
		keywords=keywords.replaceAll("　", " ").replaceAll("\\s{2, }",  " ").trim();
	}
	if(!keywords.equals("")){
		keywordsErrorMessageList=ic.docCheck("検索ワード", keywords, 0, 50, true, true, true, true, true, true);
		if(keywordsErrorMessageList.size()>0){
			return SUCCESS;
		}
	}
	ProductInfoDAO dao=new ProductInfoDAO();
	switch(categoryId){
	case "1":
		//split>>文字列を分割
		productInfoDTOList=dao.getKeywordInfo(keywords.split(" "));
		break;

	default:
		productInfoDTOList=dao.getCategoryIdAndKeywordInfo(keywords.split(" "), categoryId);
		break;
	}
	if(!session.containsKey("categoryDTOList")){
		List<CategoryDTO> categoryDTOList= new ArrayList<CategoryDTO>();
		CategoryDAO categoryDAO=new CategoryDAO();
		try{
			categoryDTOList=categoryDAO.getCategoryList();
		}catch(NullPointerException e){
			categoryDTOList=null;
		}
		session.put("categoryDTOList", categoryDTOList);
	}
	return SUCCESS;

}

public String getCategoryId() {
	return categoryId;
}

public void setCategoryId(String categoryId) {
	this.categoryId = categoryId;
}

public String getKeywords() {
	return keywords;
}

public void setKeywords(String keywords) {
	this.keywords = keywords;
}

public List<String> getKeywordsErrorMessageList() {
	return keywordsErrorMessageList;
}

public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList) {
	this.keywordsErrorMessageList = keywordsErrorMessageList;
}

public List<ProductInfoDTO> getProductInfoDTOList() {
	return productInfoDTOList;
}

public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
	this.productInfoDTOList = productInfoDTOList;
}

public Map<String, Object> getSession() {
	return session;
}

public void setSession(Map<String, Object> session) {
	this.session = session;
}

}
