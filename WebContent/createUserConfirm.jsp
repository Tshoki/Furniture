<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script src="./js/createUser.js"></script>
<link rel="stylesheet" href="./css/jaguar.css">
<title>ユーザー情報入力確認画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>ユーザー情報入力確認画面</h1>
	<div>
		<s:form id="createUserForm">
			<table class="row_table">
				<tr>
					<th scope="row">姓</th>
					<td><s:property value="familyName" /></td>
				</tr>

				<tr>
					<th scope="row">名</th>
					<td><s:property value="firstName" /></td>
				</tr>

				<tr>
					<th scope="row">姓ふりがな</th>
					<td><s:property value="familyNameKana" /></td>
				</tr>

				<tr>
					<th scope="row">名ふりがな</th>
					<td><s:property value="firstNameKana" /></td>
				</tr>

				<tr>
					<th scope="row">性別</th>
					<td><s:property value="sex" /></td>
				</tr>

				<tr>
					<th scope="row">メールアドレス</th>
					<td><s:property value="email" /></td>
				</tr>

				<tr>
					<th scope="row">ユーザーID</th>
					<td><s:property value="userId" /></td>
				</tr>

				<tr>
					<th scope="row">パスワード</th>
					<td><s:property value="password" /></td>
				</tr>
			</table>
				<!-- 登録ボタン押下で、createUser.jsのgoCreateUserCompleteActionメソッドを実行 -->
				<div class="center_btn_box">
				<s:submit value="登録" class="btn_green" onclick="goCreateUserCompleteAction()" />
				</div>
				<!-- 戻るボタン押下で、createUser.jsのgoCreateUserActionメソッドを実行 -->
				<div class="center_btn_box">
				<s:submit value="戻る" class="btn_blue" onclick="goCreateUserAction()" />
				</div>
				<s:hidden id="backFlg" name="backFlg" value="" />
		</s:form>
	</div>
</body>
</html>