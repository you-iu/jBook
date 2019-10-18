<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection conn = null;
		try{
			String jdbcDriver = "jdbc:apache:commons:dbcp:j_book";//커넥션 풀의 이름
			conn = DriverManager.getConnection(jdbcDriver);
			
			if(conn != null){
				out.print("커넥션 풀에 연결하였습니다.");
			}else{
				out.print("커넥션 풀에 연결을 실패하였습니다.");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			if(conn != null){
				conn.close();
			}
		}
	%>
</body>
</html>