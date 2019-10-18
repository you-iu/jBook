<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width: 150px;
		height: 200px;
	}
	td{
		border: 1px solid black;
	}
	#result{
		color: red;
		font-size: 20px;
	}
</style>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<table>
	<tr>
		<td></td>
		<td>상품이름</td>
		<td>상품갯수</td>
		<td>가격</td>
		<td></td>
	</tr>
	<c:set var="totalPrice" value="0"/>
	<c:forEach var="c" items="${list }">
		<tr>
			<td><img src="${pageContext.request.contextPath }/upload/${c.product.pFile}"></td>
			<td>${c.product.pTitle }</td>
			<td>
			<input type="text" value="${c.cCount }"><br>
				<button class="btnUpdate">변경</button>
			</td>
			<td>
				<fmt:formatNumber value="${c.product.pPrice }" pattern="###,###"/>
			</td>
				<c:set var="totalPrice" value="${totalprice + c.cCount * c.product.pPrice }"/>
			<td>
				<button class="btnDelete">삭제</button>
			</td>
		</tr>
	</c:forEach>
	</table>
	<p>전체가격 :<span id="result">${totalPrice }</span></p>
	
	<script>
		$(".btnDelete").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/cart/delete.do",
				type:"get",
			/* 	data:, */
				dataType:"json",
				success:function(res){
					console.log(res);
				},
				error:function(e){
					console.log(e);
				}
			})
		})
		
		
	</script>
</body>
</html>