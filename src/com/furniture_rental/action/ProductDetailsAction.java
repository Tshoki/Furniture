package com.furniture_rental.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.furniture_rental.dao.ProductInfoDAO;
import com.furniture_rental.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{
	private int productId;
	//中身はオブジェクト型(参照型)外見はInt型にしたい
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductList;
	private Map<String, Object> session;
	private ProductInfoDTO productInfoDTO=new ProductInfoDTO();

	public String execute(){
		ProductInfoDAO productInfoDAO=new ProductInfoDAO();
		productInfoDTO=productInfoDAO.getProductIdInfo(productId);
		if(productInfoDTO.getProductId()==0){
			productInfoDTO=null;
		}else{
			productCountList=new ArrayList<Integer>();
			/*5が最大数*/
			for(int i=0; i<=5; i++){
				productCountList.add(i);
			}
			//関連商品0₋3でランダムに表示
			relatedProductList=productInfoDAO.getRelatedProductList(productInfoDTO.getCategoryId(), productInfoDTO.getCategoryId(), 0, 3);
		}
		return SUCCESS;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<Integer> getProductCountList() {
		return productCountList;
	}

	public void setProductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductList() {
		return relatedProductList;
	}

	public void setRelatedProductList(List<ProductInfoDTO> relatedProductList) {
		this.relatedProductList = relatedProductList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}

	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}

}
