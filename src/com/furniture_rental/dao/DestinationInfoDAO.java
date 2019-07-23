package com.furniture_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.furniture_rental.dto.DestinationInfoDTO;
import com.furniture_rental.util.DBConnector;

public class DestinationInfoDAO {
	public int insert(String userId, String familyName, String firstName, String familyNameKana, String firstNameKana, String userAddress, String telNumber, String email){

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int count=0;

	/*データの格納*/
	  String sql="INSERT INTO destination_info(user_id, family_name, first_name, family_name_kana, first_name_kana, user_address, tel_number, email, regist_date, update_date) "
				  + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, now(), now()) ";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, familyName);
			ps.setString(3, firstName);
			ps.setString(4, familyNameKana);
			ps.setString(5, firstNameKana);
			ps.setString(6, userAddress);
			ps.setString(7, telNumber);
			ps.setString(8, email);

			count=ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{	//throw対策
			try{
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
		return count;
		}
    public List<DestinationInfoDTO> getDestinationInfo(String userId){
	DBConnector db=new DBConnector();
	Connection con=db.getConnection();
	List<DestinationInfoDTO> destinationInfoDTOList=new ArrayList<DestinationInfoDTO>();
	String sql="SELECT id, family_name, first_name, family_name_kana, first_name_kana, email, tel_number, user_address "
			+ "FROM destination_info "
			+ "WHERE user_id= ? ";
	try{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userId);
		ResultSet rs=ps.executeQuery();

	while(rs.next()){
		DestinationInfoDTO DID=new DestinationInfoDTO();
		DID.setId(rs.getInt("id"));
		DID.setFamilyName(rs.getString("family_name"));
		DID.setFirstName(rs.getString("first_name"));
		DID.setFamilyNameKana(rs.getString("family_name_kana"));
		DID.setFirstNameKana(rs.getString("first_name_kana"));
		DID.setEmail(rs.getString("email"));
		DID.setTelNumber(rs.getString("tel_number"));
		DID.setUserAddress(rs.getString("user_address"));
		destinationInfoDTOList.add(DID);
	}
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
	}try{
		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return destinationInfoDTOList;
    }

    public int deleteDestination(int id){
    	DBConnector db=new DBConnector();
		Connection con=db.getConnection();

    	String sql="DELETE FROM destination_info WHERE id=?";
    	int count=0;
    	try {
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setInt(1, id);
    		count=ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally{
    	try{
    		con.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	}
    	return count;
    }
}
