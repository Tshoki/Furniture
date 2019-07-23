<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<title>宛先情報入力画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>宛先情報入力画面</h1>
	<!-- エラーメッセージ出力 -->
	<s:if
		test="familyNameErrorMessageList!= null && familyNameErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="familyNameErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="firstNameErrorMessageList!= null && firstNameErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="firstNameErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="familyNameKanaErrorMessageList!= null && familyNameKanaErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="familyNameKanaErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="firstNameKanaErrorMessageList!= null && firstNameKanaErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="firstNameKanaErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="userAddressErrorMessageList!= null && userAddressErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="userAddressErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="telNumberErrorMessageList!= null && telNumberErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="telNumberErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>
	<s:if
		test="emailErrorMessageList!= null && emailErrorMessageList.size()> 0">
		<div class="message bg_red">
			<s:iterator value="emailErrorMessageList">
				<s:property /><br>
			</s:iterator>
		</div>
	</s:if>

	<!-- 宛先情報入力フォーム -->
	<s:form action="CreateDestinationConfirmAction">
		<table class="row_table">
			<tr>
				<th>姓</th>
				<td><s:textfield name="familyName"
						value="%{#session.familyName}" placeholder="姓" onfocus="this.placeholder=''" onblur="this.placeholder='姓'"/></td>
			</tr>
			<tr>
				<th>名</th>
				<td><s:textfield name="firstName" value="%{#session.firstName}" placeholder="名" onfocus="this.placeholder=''" onblur="this.placeholder='名'"/></td>
			</tr>
			<tr>
				<th>姓ふりがな</th>
				<td><s:textfield name="familyNameKana"
						value="%{#session.familyNameKana}" placeholder="姓ふりがな" onfocus="this.placeholder=''" onblur="this.placeholder='姓ふりがな'"/></td>
			</tr>
			<tr>
				<th>名ふりがな</th>
				<td><s:textfield name="firstNameKana"
						value="%{#session.firstNameKana}" placeholder="名ふりがな" onfocus="this.placeholder=''" onblur="this.placeholder='名ふりがな'"/></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><s:textfield name="userAddress"
						value="%{#session.userAddress}" placeholder="住所" onfocus="this.placeholder=''" onblur="this.placeholder='住所'"/></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><s:textfield name="telNumber" value="%{#session.telNumber}"
						placeholder="電話番号" onfocus="this.placeholder=''" onblur="this.placeholder='電話番号'"/></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><s:textfield name="email" value="%{#session.email}"
						placeholder="メールアドレス" onfocus="this.placeholder=''" onblur="this.placeholder='メールアドレス'"/></td>
			</tr>
		</table>
		<div class="center_btn_box">
		<s:submit class="btn_green" value="確認" />
		</div>
	</s:form>
</body>
</html>