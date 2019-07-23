<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<link rel="stylesheet" href="./css/productDetails.css">
<title>商品詳細画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>商品詳細画面</h1>
	<!-- DTO型の 選択された商品の詳細 があれば表示 -->
		<s:if test="productInfoDTO!=null">

			<s:form action="AddCartAction">
				<div class="box">
					<div class="column-container">
						<img src='<s:property value="productInfoDTO.imageFilePath"/>/<s:property value="productInfoDTO.imageFileName"/>' class="item-image-box-320"/>
						<div class="main_info">
							<table class="row_table">
								<tr>
								<!-- scope="row"は『th（見出し用セル）に対応するtd（データ用セル）はrow行方向（横方向）ですよ』という意味 -->
								<th scope="row"><s:label value="商品名"/></th>
								<td><s:property value="productInfoDTO.productName"/></td>
								</tr>
								<tr>
								<th scope="row"><s:label value="商品名ふりがな"/></th>
								<td><s:property value="productInfoDTO.productNameKana"/></td>
								</tr>
								<tr>
								<th scope="row"><s:label value="値段"/></th>
								<td><s:property value="productInfoDTO.price"/>円</td>
								</tr>
								<tr>
								<th scope="row"><s:label value="購入個数"/></th>
								<td><s:select name="productCount" list="%{productCountList}"/>個</td> <!-- productCountListは購入個数。Integer型の配列 -->
								</tr>
								<tr>
								<th scope="row"><s:label value="発売会社名"/></th>
								<td><s:property value="productInfoDTO.releaseCompany"/></td>
								</tr>
								<tr>
								<th scope="row"><s:label value="発売年月日"/></th>
								<td><s:property value="productInfoDTO.releaseDate"/></td>
								</tr>
								<tr>
								<th scope="row"><s:label value="商品詳細情報"/></th>
								<td><s:property value="productInfoDTO.productDescription"/></td>
								</tr>
							</table>
							<s:hidden name="productId" value="%{productInfoDTO.productId}"/> <!-- hiddenで選択されている商品IDを渡す -->
							<s:submit value="カートに追加" class="btn_green" />
						</div>
					</div>
				</div>
			</s:form>

			<!-- 関連商品のListがあれば表示。 商品IDをparamで渡す -->
			<s:if test="relatedProductList!=null && relatedProductList.size()>0">
				<div class="box">
				<h2>【関連商品】</h2>
					<div class="product-details-recommend-box">
					<s:iterator value="relatedProductList">
						<div class="recommend-box">
							<a href='<s:url action="ProductDetailsAction">
							<s:param name="productId" value="%{productId}"/>
							</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-100"/>
							<s:property value="productName"/><br>
							</a>
						</div>
					</s:iterator>
					</div>
				</div>
			</s:if>

		</s:if>
		<!-- 選択された商品の詳細がなかった場合 -->
		<s:else>
			<div class="message bg_blue">
				商品の詳細情報がありません。
			</div>
		</s:else>
</body>
</html>