<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width:300px;
		height: 300px;
	}
	h3{
		color: red;
	}
</style>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
<section>
	<img src="${pageContext.request.contextPath }/upload/${product.pFile}">
	
	<h1>${product.pTitle }</h1>
	${product.pName } | ${product.pPublisher } | ${product.pRegdate } | 판매부수
	<br>
	
	<h3>판매가 ${product.pPrice }</h3>
	상품개수
	
	<select name="s" id="count">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
	</select>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/cart/view.do"><input type="button" value="장바구니" id="btnCart" data-pNo="${product.pNo }"></a>
	<input type="button" value="주문하기"><br>
	<br> ${product.pDetail }
	</section>
	<script>
		$("#btnCart").click(function(){
			var count = $("#count").val();
			var pNo = $(this).attr("data-pNo");
			
			$.ajax({
				url:"${pageContext.request.contextPath}/cart/add.do",
				type:"get",
				data:{"count":count, "pNo":pNo},
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