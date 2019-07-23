<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<title>マイページ画面</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<h1>マイページ画面</h1>
	<s:if test="userInfoDTO!=null">
	<s:form action="PurchaseHistoryAction"><!-- 遷移先 -->
		<table class="row_table">
			<tr>
				<th scope="row"><s:label value="姓"/></th>
				<td><s:property value="userInfoDTO.familyName"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="名"/></th>
				<td><s:property value="userInfoDTO.firstName"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="ふりがな"/></th>
				<td><s:property value="userInfoDTO.familyNameKana"/><span>　</span><s:property value="userInfoDTO.firstNameKana"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="性別"/></th>
				<td><s:if test="userInfoDTO.sex==1">女性</s:if><s:else>男性</s:else></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="メールアドレス"/></th>
				<td><s:property value="userInfoDTO.email"/></td>
			</tr>
		</table>
			<div class="center_btn_box">
				<s:submit class="btn_blue" value="購入履歴"/>
			</div>
	</s:form>
	</s:if>
	<s:else>
		<div class="message bg_blue">
			ユーザー情報がありません。
		</div>
	</s:else>
</body>
</html>