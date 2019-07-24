<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/productList.css">
	<link rel="stylesheet" href="./css/jaguar.css">
	<title> 商品一覧画面 </title>
</head>
<body>
<jsp:include page="header.jsp" />
		<h1>商品一覧画面</h1>
		<!-- エラーがあるとき -->
		<s:if test="keywordsErrorMessageList!=null && keywordsErrorMessageList.size()>0">
			<div class="message bg_red">
				<s:iterator value="keywordsErrorMessageList">
					<s:property /><br>
				</s:iterator>
			</div>
		</s:if>

		<!-- エラーがないとき -->
		<s:elseif test="productInfoDTOList!=null && productInfoDTOList.size()>0">
		<div class="product_list_container">
			<s:iterator value="productInfoDTOList">
			<div class="product_list_box">
				<a href='<s:url action="ProductDetailsAction">
					<s:param name="productId" value="%{productId}"/>
					</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="product_list_img"/><br>
					<s:property value="productName"/><br>
					<s:property value="productNameKana"/><br>
					<s:property value="price"/>円
				</a>
			</div>
			</s:iterator>
			</div>

		</s:elseif>

		<s:else><!-- 商品情報が存在しない場合 -->
			<div class="message bg_blue">
			検索結果がありません。<!--- 指定メッセージ --->
			</div>
		</s:else>
</body>
</html>