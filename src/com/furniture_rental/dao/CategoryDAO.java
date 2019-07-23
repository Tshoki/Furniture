package com.furniture_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.furniture_rental.dto.CategoryDTO;
import com.furniture_rental.util.DBConnector;

public class CategoryDAO {
	public List<CategoryDTO> getCategoryList() {
		DBConnector dbConnector = new DBConnector();
		Connection con = dbConnector.getConnection();
		List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
		String sql = "SELECT * FROM category";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setId(rs.getInt("Id"));
				dto.setCategoryId(rs.getInt("categoryId"));
				dto.setCategoryName(rs.getString("categoryName"));
				categoryList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categoryList;
	}
}