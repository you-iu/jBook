package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Product;
import com.khrd.dto.Type;
import com.khrd.jdbc.JDBCUtil;

public class ProductDAO {
	private static final ProductDAO dao = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return dao;
	}
	
	private ProductDAO() {}
	
	public int insert(Connection conn, Product product ) {
		PreparedStatement pstmt = null;	
		try {
			String sql = "insert into product values (null,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getpTitle());
			pstmt.setString(2, product.getpName());
			pstmt.setInt(3, product.getpPrice());
			pstmt.setString(4, product.getpDetail());
			pstmt.setString(5, product.getpPublisher());
			pstmt.setTimestamp(6, new Timestamp(product.getpRegdate().getTime()));
			pstmt.setString(7, product.getpFile());
			pstmt.setInt(8, product.getType().gettNo());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
	public List<Product> selectList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = null;
		try {
			String sql ="select * from product p join type t using(t_no)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Product pro = new Product(rs.getInt("p_no"), 
						rs.getString("p_title"),
						rs.getString("p_name"), 
						rs.getInt("p_price"),
						rs.getString("p_detail"), 
						rs.getString("p_publisher"),
						rs.getTimestamp("p_regdate"),
						rs.getString("p_file"), 
						new Type(rs.getInt("t_no"),rs.getString("t_name")));
				list.add(pro);
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
	
	public Product selectByNo(Connection conn, int p_no){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql ="select * from product p join type t using(t_no) where p.p_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Product pro = new Product(rs.getInt("p_no"), 
						rs.getString("p_title"),
						rs.getString("p_name"), 
						rs.getInt("p_price"),
						rs.getString("p_detail"), 
						rs.getString("p_publisher"),
						rs.getTimestamp("p_regdate"),
						rs.getString("p_file"), 
						new Type(rs.getInt("t_no"),rs.getString("t_name")));
				return pro;
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
