package com.furniture_rental.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import com.furniture_rental.dto.UserInfoDTO;
import com.furniture_rental.util.DBConnector;

public class UserInfoDAO {
	/*上でもいいのでは？？？
	 * DBConnector db=new DBConnector();
	Connection con=db.getConnection();
	**************************************/
	//マイページ用//
	public UserInfoDTO getUserInfo(String userId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		UserInfoDTO dto=new UserInfoDTO();
		String sql="SELECT * FROM user_info WHERE user_id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			//userIdでDBを検索
			ps.setString(1, userId);

			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setSex(rs.getInt("sex"));
				dto.setEmail(rs.getString("email"));
				dto.setLogined(rs.getInt("logined"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}try{
			con.close();
		}catch(SQLException e){
				e.printStackTrace();
		}
			return dto;
	}

	//ユーザー情報登録
	public int createUser(String familyName, String firstName, String familyNameKana, String firstNameKana, String sex, String email, String userId, String password){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int count=0;
		String sql="INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana, "
				+ "first_name_kana, sex, email, status, logined, regist_date, update_date) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,? now(), now())";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, familyName);
			ps.setString(4, firstName);
			ps.setString(5, familyNameKana);
			ps.setString(6, firstNameKana);
			ps.setString(7, sex);
			ps.setString(8, email);
			ps.setInt(9, 0);
			ps.setInt(0, 1);
			count=ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
				e.printStackTrace();
		}
			return count;
	}

	//ログイン機能
	public boolean existsUser(String userId, String password){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		boolean result = false;
		String sql="SELECT COUNT (*) AS count FROM user_info WHERE user_id=? AND password=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//count...同じユーザーID&&パスワードが見つかった件数
				if(rs.getInt("count") > 0){
					result=true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//新規ユーザー登録（userIdが被っていないか）
	public boolean isExistsUserInfo(String userId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		boolean result=false;
		String sql="SELECT COUNT (*) AS count FORM suer_info WHERE user_id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//count...同じユーザーIDが見つかった件数
				if(rs.getInt("count") > 0){
					result=true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//??????ユーザー情報呼び出し
	public UserInfoDTO getUserInfo(String userId, String password){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		UserInfoDTO dto=new UserInfoDTO();
		String sql="SELECT * FROM user_info WHERE user_id=? AND password=? ";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setPassword(rs.getString("password"));
				dto.setFamilyName(rs.getString("familyName"));
				dto.setFirstName(rs.getString("firstName"));
				dto.setFamilyNameKana(rs.getString("familyNameKana"));
				dto.setFirstNameKana(rs.getString("firstNameKana"));
				dto.setSex(rs.getInt("sex"));
				dto.setEmail(rs.getString("email"));
				dto.setLogined(rs.getInt("logined"));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dto;
	}

	public int resetPassword(String userId, String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int result = 0;

		String sql = "UPDATE user_info SET password = ?, update_date = now() WHERE user_id = ?";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, userId);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}try{
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//ログイン認証
	public int logined(String userId, String password){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int result=0;
		String sql="UPDATE user_info SET logined=1, update_date=now() WHERE user_id=? AND password=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			//更新した件数を返す(ログイン1、未ログイン0)
			result=ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}

	//ログアウト
	public int logout(String userId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int result=0;
		String sql="UPDATE user_info SET logined=0, update_date=now() WHERE user_id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			//更新した件数を返す(ログイン1、未ログイン0)
			result=ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
		}
		}