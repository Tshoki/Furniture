package com.furniture_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.furniture_rental.dto.ProductInfoDTO;
import com.furniture_rental.util.DBConnector;

public class ProductInfoDAO {

	/*
	 * すべての商品情報の取得(すべて)
	 * @return List<ProductInfoDTO>型
	 * */
	public List<ProductInfoDTO> getProductInfoListAll(){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();
		String sql="SELECT * FROM product_info";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				productInfoDTOList.add(dto);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return productInfoDTOList;
}

	/*
	 * 商品IDをもとに商品情報を呼び出す（単体）＞＞商品詳細
	 * */
	public ProductInfoDTO getProductIdInfo(int productId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO productInfoDTO=new ProductInfoDTO();
		String sql="SELECT * FROM product_info"
				+ " WHERE product_id=?";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return productInfoDTO;
}

	public List<ProductInfoDTO> getRelatedProductList(int categoryId, int productId, int limitOffset, int limitRowCount){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();
		String sql="SELECT * FROM product_info"
				+ " WHERE cstegory_id=? AND product_id=?"
				+ " NOT IN(?)"	//not in(?)..指定した要素を含まない値の取得
				+ " ORDER BY RAND() limit ?,?";	//rand...表示順をランダムに
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.setInt(2, categoryId);
			ps.setInt(3, limitOffset);
			ps.setInt(4, limitRowCount);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				productInfoDTOList.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	/******
	 * 検索用>>商品名、カナから検索可能
	 * @param keywordsList String型 キーワード配列
	 * @return List<ProductInfoDTO>型
	 ******/
	public List<ProductInfoDTO> getKeywordInfo(String[] keywordsList){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();
		String sql="SELECT * FROM product_info";

		boolean initializeFlg=true;

		//検索ワードが空でない場合
		if(!(keywordsList[0].equals(""))){
			//keywordsListを１つずつkeywordにいれて実行
			for(String keyword: keywordsList){
				if(initializeFlg){
					sql +=" WHERE(product_name LIKE '%"+keyword+ "%'"
							+ " OR product_name_kana like '%" +keyword+ "%')";
					initializeFlg=false;
				}else{
					sql+=" OR (product_name LIKE '%"+keyword+"%')"
							+" OR product_name_kana like '%" +keyword+ "%')";
				}
			}
		}
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				productInfoDTOList.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//カテゴリとキーワードから検索
	public List<ProductInfoDTO> getCategoryIdAndKeywordInfo(String[] keywaordsList, String categoryId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql="SELECT * FROM product_info"
				+ " WHERE category_id="+categoryId;
		boolean initializeFlg=true;

		if(!(keywaordsList[0].equals(""))){
			for(String keyword : keywaordsList){
				if(initializeFlg){
					sql +=" AND ((product_name LIKE '%"+ keyword +"% OR product_name_kana LIKE '%"+ keyword +"%')";
					initializeFlg=false;
				}else{
					sql +=" OR (product_name LIKE '%"+ keyword +"% OR product_name_kana LIKE '%"+ keyword +"%')";
				}
			}
			sql +=")";	//検索ワードがある場合
		}
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				productInfoDTOList.add(dto);
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return productInfoDTOList;
	}
}