<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<title>パスワード再設定確認確認</title>
</head>
<body>
<script src="./js/resetPassword.js"></script>
<jsp:include page="header.jsp"/>
<div id="contents">
	<h1>パスワード再設定確認画面</h1>
	<s:form id="resetPasswordForm">
		<table class="row_table">
			<tr>
				<th scope="row"><s:label value="ユーザーID"/></th>
				<td><s:property value="userId"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="新しいパスワード"/></th>
				<td><s:property value="concealedPassword"/></td>
			</tr>
		</table>
		<div class="center_btn_box">
			<s:submit class="btn_green" value="パスワード再設定" onclick="goResetPasswordCompleteAction()"/>
		</div>
		<div class="center_btn_box">
			<s:submit class="btn_blue" value="戻る" onclick="goResetPasswordAction()"/>
		</div>
		<s:hidden id="backFlg" name="backFlg" value=""/>
	</s:form>
</div>
</body>
</html>