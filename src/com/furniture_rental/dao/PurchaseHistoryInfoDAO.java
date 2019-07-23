package com.furniture_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.furniture_rental.dto.PurchaseHistoryInfoDTO;
import com.furniture_rental.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	/*****************
	 * ユーザーIDに紐づく購入履歴を引っ張る
	 * @return List<ParchaseHistoryInfoDTO>型
	 * */

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String userId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List <PurchaseHistoryInfoDTO> purchaseHistoryList=new ArrayList<PurchaseHistoryInfoDTO>();

		String sql="SELECT phi.id AS id," /*id*/
				+ " phi.user_id AS user_id,"	/*ユーザーID*/
				+ " pi.product_id AS product_id,"	/*商品ID*/
				+ " pi.product_count AS product_count,"	/*商品個数*/
				+ " pi.product_name AS product_name"	/*商品名*/
				+ " pi.product_name_kana AS product_name_kana"	/*商品名カナ*/
				+ " pi.product_description AS product_description"	/*商品詳細*/
				+ " pi.category_id as category_id," /* カテゴリID */
				+ " pi.price"	/*価格*/
				+ " pi.image_file_name AS image_file_name,"	/*商品画像名*/
				+ " pi.image.file.path AS image_file_path,"	/*商品画像パス*/
				+ " phi.price AS price"	/*値段*/
				+ " phi.price * product_count AS subtotal,"	/*小計*/
				+ " phi.regist_date AS regist_date,"	/*登録日時*/
				+ " phi.update_date AS update_date,"	/*更新日時*/
				+ " di.family_name as family_name," /* 姓 */
				+ " di.first_name as first_name," /* 名 */
				+ " di.user_address as user_address" /* 住所 */
				+ " FROM purchase_history_info as phi"
				+ " LEFT JOIN product_info as pi"
				+ " ON phi.product_id = pi.product_id"
				+ " LEFT JOIN destination_info as di"
				+ " ON phi.destination_id = di.id"
				+ " WHERE phi.user_id = ?"
				+ " ORDER BY regist_date DESC";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PurchaseHistoryInfoDTO dto=new PurchaseHistoryInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setSubtotal(rs.getInt("subtotal"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setUserAddress(rs.getString("user_address"));
				purchaseHistoryList.add(dto);
			}
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		try{
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	return purchaseHistoryList;
}

	/******
	 * 商品購入情報登録
	 * @param detinationId 宛先ID
	 * @param price 値段 (not価格)
	 * @return int型 更新件数
	 * */
	public int regist(String userId, int productId, int productCount, int destinationId, int price){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql="INSERT INTO purchase_history_info(user_id, product_id, product_count, destination_id, price"
				+ "regist_date, update_date"
				+ "VALUES(?, ?, ?, ?, ?, now(), now())";
		int count=0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, destinationId);
			ps.setInt(5, price);
			count=ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
	public int deleteAll(String userId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql="DELETE FORM purchase_history"
				+ "WHERE user_id";
		int count=0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			count=ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
}