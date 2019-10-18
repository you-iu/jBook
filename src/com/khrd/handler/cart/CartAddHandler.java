package com.khrd.handler.cart;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.CartDAO;
import com.khrd.dto.Cart;
import com.khrd.dto.Product;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class CartAddHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sCount = request.getParameter("count");
		int count = Integer.parseInt(sCount);
		String spNo = request.getParameter("pNo");
		int no = Integer.parseInt(spNo);
		String uId = (String) request.getSession().getAttribute("Auth");
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			CartDAO dao = CartDAO.getInstance();
			Product p = new Product();
			p.setpNo(no);
			Cart cart = new Cart(0,count,p,uId);
			int result = dao.insert(conn, cart);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			//객체-> json 변환
			ObjectMapper om = new ObjectMapper(); 
			String json = om.writeValueAsString(map);
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
