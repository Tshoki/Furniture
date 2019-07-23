<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/jaguar.css">
<title>決済確認画面</title>

</head>
<body>
<jsp:include page="header.jsp" />
<script src="./js/settlementConfirm.js"></script>
<h1>決済確認画面</h1>
<s:if test="destinationInfoDTOList!=null && destinationInfoDTOList.size()>0">

<div class="message bg_blue">
	宛先情報を選択してください。
</div>
	<s:form id="settlementConfirmForm">
	<table class="column_table">
	<thead>
	<tr>
	<th><s:label value="#"/></th>
	<th><s:label value="姓"/></th>
	<th><s:label value="名"/></th>
	<th><s:label value="ふりがな"/></th>
	<th><s:label value="住所"/></th>
	<th><s:label value="電話番号"/></th>
	<th><s:label value="メールアドレス"/></th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="destinationInfoDTOList" status="st">
	<tr>
	<td>
		<s:if test="#st.index == 0">
		<input type="radio" name="id" checked="checked" value="<s:property value='id'/>"/>
		</s:if><s:else>
		<input type="radio" name="id" value="<s:property value='id'/>"/>
		</s:else>
	</td>
	<td>
		<s:property value="familyName"/><br>
	</td>
	<td>
		<s:property value="firstName"/><br>
	</td>
	<td>
		<s:property value="familyNameKana" /> <s:property value="firstNameKana" /><br>
	</td>
	<td>
		<s:property value="userAddress" />
	</td>
	<td>
		<s:property value="telNumber"/>
	</td>
	<td>
		<s:property value="email"/>
	</td>
	</s:iterator>
	</tbody>
	</table>

	<div class="center_btn_box">
	<s:submit value="決済" class="btn_green" onclick="goSettlementCompleteAction()" />
	</div>
	<div class="center_btn_box">
	<s:submit value="削除" class="btn_red" onclick="goDeleteDestinationAction()" />
	</div>
	</s:form>
</s:if>
<s:else>
<div class="message bg_blue">
宛先情報がありません。
</div>
</s:else>
<!-- 新規宛先登録ボタンは宛先情報の有無で表示切替はないが、
	決済ボタンは宛先情報の有の場合のみ表示するため、コードの可読性を考慮してformを2つにする -->

<div class="center_btn_box">
	<s:form action="CreateDestinationAction">
		<s:submit class="btn_blue" value="新規宛先登録"/>
	</s:form>
</div>
</body>
</html>