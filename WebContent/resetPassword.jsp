<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<title>パスワード再設定画面</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>パスワード再設定画面</h1>

	<s:if test="userIdErrorMessageList!=null && userIdErrorMessageList.size()>0">
	<div class="message bg_red">
		<s:iterator value="userIdErrorMessageList"><s:property/>
		<br></s:iterator>
		</div>
	</s:if>

	<s:if test="passwordErrorMessageList!=null && passwordErrorMessageList.size()>0">
	<div class="message bg_red">
		<s:iterator value="passwordErrorMessageList"><s:property/>
		<br></s:iterator>
		</div>
	</s:if>

	<s:if test="newPasswordErrorMessageList!=null && newPasswordErrorMessageList.size()>0">
	<div class="message bg_red">
		<s:iterator value="newPasswordErrorMessageList"><s:property/>
		<br></s:iterator>
		</div>
	</s:if>

	<s:if test="reConfirmationNewPasswordErrorMessageList!=null && reConfirmationNewPasswordErrorMessageList.size()>0">
	<div class="message bg_red">
		<s:iterator value="reConfirmationNewPasswordErrorMessageList"><s:property/>
		<br></s:iterator>
		</div>
	</s:if>

	<s:if test="passwordIncorrectErrorMessage!=null && !passwordIncorrectErrorMessage.isEmpty()">
	<div class="message bg_red">
		<s:property value="passwordIncorrectErrorMessage"/>
		<br>
	</div>
	</s:if>

	<s:if test="newPasswordIncorrectErrorMessage!=null && !newPasswordIncorrectErrorMessage.isEmpty()">
	<div class="message bg_red">
		<s:property value="newPasswordIncorrectErrorMessage"/>
		<br>
	</div>
	</s:if>

<s:form action="ResetPasswordConfirmAction">
	<table class="row_table">
		<tr>
			<th scope="row"><s:label value="ユーザID"/></th>
			<td><s:textfield name="userId" value="%{#session.userIdForResetPassword}" placeholder="ユーザーID" onfocus="this.placeholder=''" onblur="this.placeholder='ユーザーID'"/></td>
		</tr>
		<tr>
			<th scope="row"><s:label value="現在のパスワード"/></th>
			<td><s:password name="password" value="" placeholder="現在のパスワード" onfocus="this.placeholder=''" onblur="this.placeholder='現在のパスワード'"/></td>
		</tr>
		<tr>
			<th scope="row"><s:label value="新しいパスワード"/></th>
			<td><s:password name="newPassword" value="" placeholder="新しいパスワード" onfocus="this.placeholder=''" onblur="this.placeholder='新しいパスワード'"/>
		<tr>
			<th scope="row"><s:label value="新しいパスワード（再確認）"/></th>
			<td><s:password name="reConfirmationPassword" value="" placeholder="新しいパスワード（再確認)" onfocus="this.placeholder=''" onblur="this.placeholder='新しいパスワード（再確認)'"/></td>
		</tr>
	</table>
	<div class="center_btn_box">
		<s:submit class="btn_green" value="確認" />
	</div>
</s:form>
</div>
</body>
</html>

