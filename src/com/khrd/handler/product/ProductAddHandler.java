package com.khrd.handler.product;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ProductDAO;
import com.khrd.dto.Product;
import com.khrd.dto.Type;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductAddHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/product/addForm.jsp";
		}else if(request.getMethod().equalsIgnoreCase("post")) {
			//upload 폴더 만들기
			String uploadPath = request.getRealPath("upload");
			File dir = new File(uploadPath);
			if(dir.exists() == false) {//폴더 존재여부 확인
				dir.mkdir();
			}
			
			int size = 1024*1024*10;//10M
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath,size,"UTF-8",new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title");
			int price = Integer.parseInt(multi.getParameter("price"));
			String name = multi.getParameter("name");
			String publisher = multi.getParameter("publisher");
			int type = Integer.parseInt(multi.getParameter("type"));
			String detail = multi.getParameter("detail");
			String file = multi.getFilesystemName("file");
			String date = multi.getParameter("date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date regDate = sdf.parse(date);
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				ProductDAO dao = ProductDAO.getInstance();
				Product product = new Product(0,title,name,price,detail,publisher,regDate,file,new Type(type,null));
				dao.insert(conn, product);
				
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
