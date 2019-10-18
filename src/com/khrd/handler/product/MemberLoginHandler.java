package com.khrd.handler.product;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.UserDAO;
import com.khrd.dto.User;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class MemberLoginHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/member/loginForm.jsp";
		}else if(request.getMethod().equalsIgnoreCase("post")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			Connection conn = null;

			try {
				conn = ConnectionProvider.getConnection();
				UserDAO dao = UserDAO.getInstance();
				User u = dao.selectById(conn, id);
				if(u == null) {
					request.setAttribute("IdnotMatch", true);
					return "/WEB-INF/view/member/loginForm.jsp"; 
				}else if(u.getuPassword().equals(password)== false) {
					request.setAttribute("passNotMatch", true);
					return "/WEB-INF/view/member/loginForm.jsp"; 
				}
			
				HttpSession session = request.getSession();
				session.setAttribute("Auth", u.getuId());
				
				response.sendRedirect(request.getContextPath()+"/product/list.do");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
