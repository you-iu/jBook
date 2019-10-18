<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<form action="loginForm.do" method="post">
	<fieldset>
		아이디 <input type="text" name="id"><br>
		비밀번호 <input type="password" name="password"><br>
		<input type="submit" value="전송">
	</fieldset>
</form>
