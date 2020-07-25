<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<link rel="icon" href="http://47.102.212.18/iDouBan/icon/iDouBan_favicon.ico" type="image/x-icon">
</head>
<body>
<!-- 发送邮件找回密码 -->
	<form action="/iDouBan/FindBackServlet">
		填写用户名<br>
		<input type= "text" name ="send_email"><br>
		<input type="submit" >
	</form>
</body>
</html>