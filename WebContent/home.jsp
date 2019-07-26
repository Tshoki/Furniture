<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/jaguar.css">
<link rel="stylesheet" href="./css/productList.css">
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>ホーム</h1>
		<div class="product_list_container">
			<s:iterator value="productInfoDTOList">
				<div class="product_list_box">
					<a
						href='<s:url action="ProductDetailsAction">
					<s:param name="productId" value="%{productId}"/></s:url>'><img
						src='
					<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
						class="product_list_img" /><br> <s:property
							value="productName" /><br> <s:property
							value="productNameKana" /><br> <s:property value="price" />円
					</a>
				</div>
			</s:iterator>
		</div>
</body>
</html>