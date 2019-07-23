package com.furniture_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.furniture_rental.dto.CartInfoDTO;
import com.furniture_rental.util.DBConnector;

public class CartInfoDAO {
	/*
	 * ユーザー情報と紐づく商品情報を抽出
	 *
	 * @return List<CartInfoDTO>型 購入商品情報
	 */
	public List<CartInfoDTO> getCartInfoDTOList(String userId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();

		// カート情報(ユーザー情報＋商品情報)>>productIdを基準に
		String sql = "SELECT" + " cart_info.id," // id
				+ " cart_info.product_id," // 商品ID
				+ " cart_info.product_count," // 商品個数(１度に買える)
				+ " product_info.product_name," // 商品名
				+ " product_info.product_name_kana," // 商品名カナ
				+ " product_info.image_file_path," // 商品画像パス
				+ " product_info.image_file_name," // 商品画像名
				+ " product_info.price," // 値段
				+ "	cart_info.regist_date," // 購入情報登録時間
				+ "	cart_info.update_date," // 購入情報時間(UPDATE用)
				+ " (product_info.price * cart_info.product_count) AS sub_total" // １商品当たりの値段
				+ " FROM cart_info LEFT JOIN product_info" + " ON cart_info.product_id = product_info.product_id"
				+ " WHERE cart_info.user_id = ?"
				+ "	ORDER BY cart_info.update_date DESC, cart_info.regist_date DESC";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartInfoDTO dto = new CartInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setPrice(rs.getInt("price"));
				dto.setProductName(rs.getString("product_name"));
				dto.setImgFilePath(rs.getString("image_file_path"));
				dto.setImgFileName(rs.getString("image_file_name"));
				dto.setStatus(rs.getString("status"));
				dto.setSubtotal(rs.getInt("subtotal"));
				cartInfoDTOList.add(dto);
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
		return cartInfoDTOList;
	}

	public boolean existsSameProduct(String userId, int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		boolean result = false;

		String sql = "COUNT (*) AS count" + " FROM cart_info" + " WHERE user_id=? AND product_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("count") > 0) {
					result = true;
				}
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
		return result;
	}

	public int updateUser(String userId, String tempUserId, int productId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;
		String sql="UPDATE cart_info"
				+ " SET user_id"
				+ " update_date=now()"
				+ " WHERE user_id=? AND product_id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);
			ps.setInt(3, productId);
			count=ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/*
	 * 合計金額情報
	 *
	 * @return Int型
	 */
	public int getTotalPrice(String userId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int totalPrice = 0;
		String sql = "SELECT" + " SUM(cart_info.product_count* product_info.price) AS totalPrice"
				+ " FROM cart_info JOIN product_info" + " ON cart_info.product_id = product_info.product_id"
				+ "WHERE cart_info.user_id=?"
				// 同じuserIdのレコードの個数と価格を足す
				+ " GROUP BY user_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// 外部結合する()
			ps.setString(1, userId);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("total_price");
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
		return totalPrice;
	}

	/*
	 * 購入情報登録
	 *
	 * @return int型 count insert件数
	 */
	public int regist(String userId, int productId, int productCount) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;
		String sql = "INSERT INTO cart_info(user_id, product_id, product_count, price, regist_date, update_date)"
				+ " VALUES(?,?,?,NOW(),NOW())";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/*
	 * 商品追加購入
	 *
	 * @return int型 更新件数
	 */
	public int countUpdate(String userId, int productId, int productCount) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "UPDATE cart_info " + "SET product_count=(product_count+?), update_date=now() "
				+ "WHERE user_id=? AND product_id=?";

		int result = 0;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 商品購入後(決済完了後)
	public int deleteAll(String userId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "DELETE FROM cart_info WHERE user_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int deleteCartInfo(String userId, int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "DELETE FROM cart_info"
					+ " WHERE user_id=? AND product_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * 商品の重複の確認
	 *
	 * @return boolean型
	 */
	public boolean checkCartInfo(String userId, int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT COUNT (id) AS count" + " FROM cart_info" + "WHERE user_id=? AND product_id=?";
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getInt("count") > 0) {
					result = true;
				}
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
		return result;
	}

	/*
	 * 仮ユーザー情報>>ユーザー情報
	 *
	 * @return int型
	 */
	public int linkToUserId(String tempUserId, String userId, int productId) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int count = 0;
		String sql = "UPDATE cart_info SET user_id=?, update_date=now()" + " WHERE user_id=? AND product_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, tempUserId);
			preparedStatement.setInt(3, productId);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}