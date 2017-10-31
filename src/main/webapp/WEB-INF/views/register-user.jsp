<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/createUser" commandName="user"
		method="post">

		<table>
			


			<tr>
				<td>User Name:</td>
				<td><form:input path="userName" size="30" required="required" />
					<font color="red"><form:errors path="userName" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30"
						 required="required" /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			
			
			
			
			
			<tr>
				<td colspan="2"><input type="submit" value="Register User" /></td>
			</tr>
		</table>

	</form:form>



</body>
</html>