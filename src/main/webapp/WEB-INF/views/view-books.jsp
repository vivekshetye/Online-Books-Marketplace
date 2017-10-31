<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Books</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:forEach var="i" items="${bookList }">
	<c:if test="${sessionScope.user.userID != i.user.userID }">
	<c:out value="${i.bookId}" />
	<c:out value="${i.bookName }" />
	<c:out value="${i.isbn }" /> 
	<img height="150" width="150" src='<c:out value="${i.filename}"></c:out>' />
	<a href="sendMessage?id=${i.bookId}"> Send message</a><br>
	</c:if>
</c:forEach>

</body>
</html>