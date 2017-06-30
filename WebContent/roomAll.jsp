<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会議室一覧</title>
</head>
<body>
	<h3>会議室</h3>

	<form action="roomAll" method="get">
		<input type="submit" value="翌日">
		<c:out value="${nextDate}"/>の会議室
	</form>


	<c:out value="${currentDate}"/>の会議室
	<a href="">翌日</a>
	<ul>
		<c:forEach items="${rooms}" var="room">
			<li>
				<form action="booker" name="form1" method="get">
					<input type="submit" value="${room.name}">
					<input type="hidden" name="roomName" value="${room.name}">
					<input type="hidden" name="selectedDate" value="${currentDate}">
				</form>
			</li>
		</c:forEach>
	</ul>


	<a href="logout">ログアウト</a>

</body>
</html>