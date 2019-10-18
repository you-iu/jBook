<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	ul{
		
	}
	li{
		width: 230px;
		height: 400px;
		float: left;
		padding:10px;
		list-style: none;
	}
	a{
		text-decoration:none;
	}
	img {
		width: 200px;
		height: 300px;
	}
	.title{
		display : block;
		color: black;
		font-weight: bold;
		font-size: 25px;	
	}
	.name{
		font-size: 15px;
		display : block;
		color: gray;
	}
	.price{
		font-size: 20px;
		color: black;
		font-weight: bold;
		display : block;
	}
</style>
	<%@ include file="../include/header.jsp"%>
	<section>
	<c:if test="${Auth != null }">
	             ${Auth }님 반갑습니다.
	             <a href="${pageContext.request.contextPath }/member/loginOut.do">[로그아웃]</a>
	<%-- 	<a href="${pageContext.request.contextPath }/member/loginForm.do">[로그인]</a>
		<a href="#">[회원가입]</a> --%>
	</c:if>
	<c:if test="${Auth == null }">
		<a href="${pageContext.request.contextPath }/member/loginForm.do">[로그인]</a>
		<a href="#">[회원가입]</a>
	</c:if>
<%-- <a href="${pageContext.request.contextPath }/member/loginForm.do">[로그인]</a><a href="#">[회원가입]</a> --%>
			<!-- 	/jbook -->
	<ul>
	<c:forEach var="p" items="${list }">
		<li>
			<a href="${pageContext.request.contextPath }/product/read.do?no=${p.pNo }">
				<img src="${pageContext.request.contextPath }/upload/${p.pFile}">
				<span class="title">${p.pTitle }</span>
				<span class="name">${p.pName }|${p.pPublisher }</span>
				<span class="price">${p.pPrice }</span>
			</a>
		</li>
	</c:forEach>
	</ul>
	</section>
<%@ include file="../include/footer.jsp"%>
