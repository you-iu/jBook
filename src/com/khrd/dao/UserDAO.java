package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.khrd.dto.User;
import com.khrd.jdbc.JDBCUtil;

public class UserDAO {
	private static final UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		return dao;
	}
	
	private UserDAO() {}
	
	public User selectById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql= "select * from user where u_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				User u = new User(rs.getString("u_id"),
						rs.getString("u_name"),
						rs.getString("u_password")
						);
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}
	
}
