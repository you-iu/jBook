package com.khrd.handler.product;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;

import com.khrd.dao.ProductDAO;

import com.khrd.dto.Product;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ProductReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sNo = request.getParameter("no");
		int p_no = Integer.parseInt(sNo);
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection(); 
			ProductDAO dao = ProductDAO.getInstance();
			Product product = dao.selectByNo(conn, p_no);
			request.setAttribute("product", product);
			
			return "/WEB-INF/view/product/productRead.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	
	}

}
