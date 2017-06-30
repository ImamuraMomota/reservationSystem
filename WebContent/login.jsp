<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h2>会議室予約システム</h2>

	<form action="login" method="post">
		<table>
			<tr>
				<th>ログインID</th>
				<td><input type="text" name="loginId" value="${loginId}"></td>
			</tr>

			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" value="${password}"></td>
			</tr>

			<tr>
				<td colspan="2"><button type="submit">ログインする</button></td>
		</table>
	</form>

	<div class="copyright" align=center>Copyright(c)Momota Imamura</div>

</body>
</html>