<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>Create book</title>
</head>
<body>

<nav class="navbar navbar-default backGrnd">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="goHome">Book Shelf</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="addBook">Add Book</a></li>
      <li ><a href="myShelf">My Shelf</a></li>
      <li ><a href="getMessages">Messages<b class="notifications"></b></a></li>
  
    </ul>
    <form class="navbar-form navbar-left" action="search" method="post">
      <div class="form-group">
        <input type="text" class="form-control" name="search" id="Search" placeholder="Search">
      </div>
      <div class="form-group ">
  <!-- <label for="sel1">Select list:</label> -->
  <select class="form-control" name="searchType" style="width:100%;" id="searchType">
    <option value="All">All</option>
    <option value="BookName">Book Name</option>
    <!-- <option value="ISBN">ISBN</option> -->
    
  </select>
</div>
      <button type="submit" id="ajaxSearch" class="btn btn-default">Search</button>
    </form>
    
    <ul class="nav navbar-nav navbar-right">
      <li><a><span class="glyphicon glyphicon-user"></span> Hello, ${user.userName}</a></li>
      <li><a href="/onlineBMP/logout.htm" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>

<div class="container" style="margin-top:5%;">
<h2>Create book:</h2>

<c:if test="${requestScope.bookStatus == true}">
<div class="alert alert-success">
Book added successfully
</div>

</c:if>


<form:form action="createBook" commandName="book"
		method="post" enctype="multipart/form-data">



<div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Book name:</label>
      <div class="col-sm-10">
        <form:input class="form-control" path="bookName" size="30" required="required"  placeholder="Book name" /><font color="red"><form:errors path="bookName" /></font>
      </div>
    </div>
    
    
    <div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">ISBN no:</label>
      <div class="col-sm-10">
        <form:input class="form-control" path="isbn" size="30" required="required"  placeholder="ISBN number" /><font color="red"><form:errors path="isbn" /></font>
      </div>
    </div>

<div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Author:</label>
      <div class="col-sm-10">
        <form:input class="form-control" path="author" size="30" required="required"  placeholder="Book name" /><font color="red"><form:errors path="author" /></font>
      </div>
    </div>


<div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Select photo:</label>
      <div class="col-sm-10">
        <input type="file" name="photo" class="form-control"  size="30" />
      </div>
    </div>
    
    
    <div class="form-group row">
      <div class="offset-sm-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Add book</button>
      </div>
    </div>
    
    
		<!-- <table> -->
			


			<%-- <tr>
				<td>Book Name:</td>
				<td><form:input path="bookName" size="30" required="required" />
					<!-- <font color="red"> -->
					<form:errors path="userName" /></font>
					</td> 
			</tr> --%>

			<%-- <tr>
				<td>Isbn:</td>
				<td><form:input path="isbn" size="30"
						required="required" /> 
						<!-- <font color="red"> -->
						<form:errors path="password" /></font> </td> 
			</tr> --%>
			
			
			
			
			<!-- <tr><td>Select photo: <input type="file" name="photo"/></td></tr> -->
			
			
			
			
			<tr>
				<td colspan="2"><input type="submit" value="Create book" /></td>
			</tr>
			</table>
</form:form>

</div>
</body>
</html>