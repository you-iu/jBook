package com.khrd.handler.cart;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.CartDAO;
import com.khrd.dto.Cart;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class CartViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Connection conn = null;
		HttpSession session = request.getSession();
		String uId = (String)request.getSession().getAttribute("Auth");
		try {
			conn = ConnectionProvider.getConnection();
			CartDAO dao = CartDAO.getInstance();			
			List<Cart> list = dao.selectListByUId(conn, uId);
			request.setAttribute("list", list);
			return "/WEB-INF/view/cart/cartList.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
