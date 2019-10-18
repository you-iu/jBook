package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Cart;
import com.khrd.dto.Product;
import com.khrd.jdbc.JDBCUtil;

public class CartDAO {
private static final CartDAO dao = new CartDAO();
	
	public static CartDAO getInstance() {
		return dao;
	}
	
	private CartDAO() {}
	
	public int insert(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		try {
			String sql="insert into cart values(null, ?,?,?)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getcCount());
			pstmt.setInt(2, cart.getProduct().getpNo());
			pstmt.setString(3, cart.getuId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
	
	public List<Cart> selectListByUId(Connection conn, String uId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="select * from cart c join product p using(p_no) where c.u_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			List<Cart> list = new ArrayList<Cart>();
			while(rs.next()) {
				Product p = new Product(
								rs.getInt("p_no"),
								rs.getString("p_title"),
								rs.getString("p_name"),
								rs.getInt("p_price"),
								rs.getString("p_detail"),
								rs.getString("p_publisher"),
								rs.getTimestamp("p_regdate"),
								rs.getString("p_file"),
								null
							);
					Cart cart = new Cart(
									rs.getInt("c_no"),
									rs.getInt("c_count"),
									p,
									rs.getString("u_id"));
					list.add(cart);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}
	
	public int delete(Connection conn , int c_no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from cart where c_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
}
