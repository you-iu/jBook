<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="../include/header.jsp"%>
	<section>
	<form action="add.do" method="post" enctype="multipart/form-data">
	책 제목 <input type="text" name="title"><br>
	책 가격 <input type="text" name="price"><br>
	저자 <input type="text" name="name"><br>
	출판사 <input type="text" name="publisher"><br>
	출판날짜 <input type="date" name="date"><br><!-- yyyy-MM-dd -->
	책종류 <select name="type">
		<option>선택하세요</option>
		<option value="1">IT</option>
		<option value="2">인문</option>
		<option value="3">문학</option>
		<option value="4">여행</option>
	</select>
	<input type="file" name="file"><br>
	상세설명<textarea rows="10" cols="60" name="detail">
	</textarea><br>
	<input type="submit" value="등록">
	</form>
	</section>
	<%@ include file="../include/footer.jsp"%>