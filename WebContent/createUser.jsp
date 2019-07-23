<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ユーザー情報入力画面</title>
<link rel="stylesheet" href="./css/jaguar.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1>ユーザー情報入力画面</h1>

	<!--  入力にミスがあり、CreateUserConfirmActionから戻された場合に表示するメッセージ一覧-->
	<s:if
		test="familyNameErrorMessageList!=null && familyNameErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="familyNameErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="firstNameErrorMessageList!=null && firstNameErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="firstNameErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="familyNameKanaErrorMessageList!=null && familyNameKanaErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="familyNameKanaErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="firstNameKanaErrorMessageList!=null && firstNameKanaErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="firstNameKanaErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="emailErrorMessageList!=null && emailErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="emailErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="userIdErrorMessageList!=null && userIdErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="userIdErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="passwordErrorMessageList!=null && passwordErrorMessageList.size()>0">
		<div class="message bg_red">
		<s:iterator value="passwordErrorMessageList">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<s:if test="isExistsUserErrorMessage!=null && !isExistsUserErrorMessage.isEmpty()">
		<div class="message bg_red">
		<s:iterator value="isExistsUserErrorMessage">
			<s:property /><br>
		</s:iterator>
		</div>
	</s:if>

	<div>
		<s:form action="CreateUserConfirmAction">
			<table class="row_table">
				<tr>
					<th scope="row">姓</th>
					<td>
					<s:textfield name="familyName" value="%{#session.familyName}" placeholder="姓" onfocus="this.placeholder=''" onblur="this.placeholder='姓'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">名</th>
					<td>
					<s:textfield name="firstName" value="%{#session.firstName}" placeholder="名" onfocus="this.placeholder=''" onblur="this.placeholder='名'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">姓ふりがな</th>
					<td>
					<s:textfield name="familyNameKana" value="%{#session.familyNameKana}" placeholder="姓ふりがな" onfocus="this.placeholder=''" onblur="this.placeholder='姓ふりがな'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">名ふりがな</th>
					<td>
					<s:textfield name="firstNameKana" value="%{#session.firstNameKana}" placeholder="名ふりがな" onfocus="this.placeholder=''" onblur="this.placeholder='名ふりがな'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">性別</th>
					<td>
					<s:radio name="sex" list="%{#session.sexList}" value="%{#session.sex}" placeholder="性別" onfocus="this.placeholder=''" onblur="this.placeholder='性別'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">メールアドレス</th>
					<td>
					<s:textfield name="email" value="%{#session.email}" placeholder="メールアドレス" onfocus="this.placeholder=''" onblur="this.placeholder='メールアドレス'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">ユーザーID</th>
					<td>
					<s:textfield name="userId" value="%{#session.userIdForCreateUser}" placeholder="ユーザーID" onfocus="this.placeholder=''" onblur="this.placeholder='ユーザーID'"/>
					</td>
				</tr>
				<tr>
					<th scope="row">パスワード</th>
					<td>
					<s:password name="password" value="%{#session.password}" placeholder="パスワード" onfocus="this.placeholder=''" onblur="this.placeholder='パスワード'"/>
					</td>
				</tr>
			</table>
			<div class="center_btn_box">
			<s:submit class="btn_green" value="確認"/>
			</div>
		</s:form>
	</div>
</body>
</html>