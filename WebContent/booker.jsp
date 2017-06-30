<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約画面</title>
</head>
<body>
<a href="./roomAll">会議室一覧へ</a><br />


<br />
<form action="booker" method="post">
会議室　<c:out value="${bookingRoom.name}"></c:out> <br />
予約者　<c:out value="${loginUser.name}"></c:out> <br />
日付　<c:out value="${selectedDate}"></c:out> <br />
時間帯　
<select name="bookingStart">
	<c:forEach items="${times}" var="time">
		<fmt:parseDate var="date" value="${time.availableTime}" pattern="yyyy-MM-dd HH:mm:ss.S" />
		<option value="${time.availableTime}">
			<fmt:formatDate value="${date}" pattern="HH:mm" />
		</option>
	</c:forEach>
</select>
から
<select name="bookingEnd">
	<c:forEach items="${times}" var="time">
		<fmt:parseDate var="date" value="${time.availableTime}" pattern="yyyy-MM-dd HH:mm:ss.S" />
		<option value="${time.availableTime}">
			<fmt:formatDate value="${date}" pattern="HH:mm" />
		</option>
	</c:forEach>
</select>
<br />
<br />

	<input type="submit" name="booking" value="予約する">
	<input type="hidden" name="userId" value="${loginUser.id }">
	<input type="hidden" name="roomName" value="${bookingRoom.name}">
	<input type="hidden" name="reservedDate" value="${selectedDate}">
</form>

<br />

<table>
	<c:forEach items="${reservations}" var="reservation">
	<c:if test="${(bookingRoom.name == reservation.roomName)}">
	<tr>
		<th>時間帯</th> <th>予約者</th> <th>操作</th>
	</tr>

	<tr>
		<td>
			<fmt:parseDate var="date" value="${reservation.bookingStart}" pattern="yyyy-MM-dd HH:mm:ss.S" />
			<fmt:formatDate value="${date}" pattern="HH:mm" />
				 -
			<fmt:parseDate var="date" value="${reservation.bookingEnd}" pattern="yyyy-MM-dd HH:mm:ss.S" />
			<fmt:formatDate value="${date}" pattern="HH:mm" />
		</td>
		<td>
			<c:forEach items="${users}" var="user">
				<c:if test="${user.id == reservation.userId}">
					<c:out value="${user.name}" />
				</c:if>
			</c:forEach>
		</td>
		<td>
			<form action="cancel" method="post">
				<input type="submit" name="0" value="予約取消">
				<input type="hidden" name="reservationId" value="${reservation.id}">
			</form>
		</td>
	</tr>
	</c:if>
	</c:forEach>
	</table>
</body>
</html>