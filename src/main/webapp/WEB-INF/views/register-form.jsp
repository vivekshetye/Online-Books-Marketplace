<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script   src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>


<div class="container-fluid" style="text-align:center; color:#ffffff; background-color:#01579B"><h1>Welcome to Book Shelf</h1> </div>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<%-- <a href="${contextPath}">Go Back</a><br/>

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
 --%>








<div class="container" style="margin-top:5%;">
<c:if test="${duplicateUserFlag == true }"><div class="alert alert-danger">
  <strong></strong> User already exists
</div></c:if>

  <form:form action="${contextPath}/user/createUser" commandName="user"
		method="post">
    <div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
      <div class="col-sm-10">
        <form:input class="form-control" path="userName" size="30" required="required"  placeholder="Username" /><font color="red"><form:errors path="userName" /></font>
      </div>
    </div>
    <div class="form-group row">
      <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
      <div class="col-sm-10">
        <form:password path="password" size="30" required="required"  class="form-control"  placeholder="Password" /><font color="red"><form:errors path="password" /></font>
      </div>
    </div>
    <div class="form-group row">
      <label for="inputPassword3" class="col-sm-2 col-form-label">Email</label>
      <div class="col-sm-10">
        <form:input path="email" size="30" required="required"  class="form-control"  placeholder="Email" /><font color="red"><form:errors path="email" /></font>
      </div>
    </div>
    <div class="form-group row">
      <label for="inputPassword3" class="col-sm-2 col-form-label">Contact No.</label>
      <div class="col-sm-10">
        <form:input path="contactNo" size="30" required="required"  class="form-control"  placeholder="Contact no" /><font color="red"><form:errors path="contactNo" /></font>
      </div>
    </div>
    
    <div class="form-group row">
      <div class="offset-sm-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Register</button>
      </div>
    </div>
    </form:form>
    </div>








</body>
</html>