<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 〜秒後に画面遷移の記述 -->
<meta http-equiv="refresh" content=" 3; url='HomeAction'">
<link rel="stylesheet" href="./css/jaguar.css">
<title>決済完了画面</title>
</head>
<body>
<jsp:include page="header.jsp"/>
  <h1>決済完了画面</h1>
  <div class="message bg_green">
    決済が完了しました。
  </div>
</body>
</html>