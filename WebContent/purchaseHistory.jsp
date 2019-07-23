<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<script src="./js/jaguar.js"></script>
<title> 商品購入履歴画面 </title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>商品購入履歴画面</h1>
<s:if test="purchaseHistoryInfoDTOList!=null && purchaseHistoryInfoDTOList.size()>0">
<table class="column_table settlement_w">
		<tr>
			<th><s:label value="商品名"/></th>
			<th><s:label value="ふりがな"/></th>
			<th><s:label value="商品画像"/></th>
			<th><s:label value="発売会社名"/></th>
			<th><s:label value="発売年月日"/></th>
			<th><s:label value="値段"/></th>
			<th><s:label value="個数"/></th>
			<th><s:label value="合計金額"/></th>
			<th><s:label value="宛先名前"/></th>
			<th><s:label value="宛先住所"/></th>
		</tr>
<s:iterator value="purchaseHistoryInfoDTOList">
		<tr>
			<td><s:property value="productName"/></td>
			<td><s:property value="productNameKana"/></td>
			<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="50px" height="50px"/></td>
			<td><s:property value="releaseCompany"/></td>
			<td><s:property value="releaseDate"/></td>
			<td><s:property value="price"/>円</td>
			<td><s:property value="productCount"/>個</td>
			<td><s:property value="subtotal"/>円</td>
			<td><s:property value="familyName"/><span>　</span><s:property value="firstName"/></td>
			<td><s:property value="userAddress"/></td>
		</tr>
</s:iterator>
</table>
<div class="center_btn_box">
<s:form action="DeletePurchaseHistoryAction">
	<s:submit class="btn_red" value="履歴削除"/>
</s:form>
</div>
</s:if>
<s:else>
<div class="message bg_blue">
商品購入履歴情報がありません。
</div>
</s:else>
</body>
</html>