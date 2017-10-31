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
<title>My Shelf</title>

</head>

<style type="text/css">

a.eachBook {
text-decoration:none;
}

</style>
<body>



<nav class="navbar navbar-default backGrnd">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="goHome">Book Shelf</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="addBook">Add Book</a></li>
      <li class="active"><a href="myShelf">My Shelf</a></li>
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

 <%-- <c:forEach var="i" items="${bookList}">

	Book ID: ${i.bookId } <br>
	Book name: ${i.bookName} <br><br><br>

</c:forEach> --%>

 <div class="allBooks container">
 <c:choose>
	
	<c:when test="${fn: length(bookList) gt 0 }"> 
	<div class="row">
	<c:forEach var="i" items="${bookList }">
	<div class="col-md-3">
	
	<c:if test="${user.userID == i.user.userID }">
	<p><img height="400" width="250" src='<c:out value="${i.filename}"/>' /></p>
	
	<p style="text-align:center"><c:out value="${i.bookName }" /></p>
	
	<a href="deleteBook?id=${i.bookId }" style="text-decoration:none"><button class="btn btn-primary">Delete book</button></a>
	
	 
	</c:if>
	
	</div>
	</c:forEach>
	</div>
	 </c:when>
	 
	 <c:otherwise>
	 	<h2>No books found</h2>
	 </c:otherwise>

</c:choose> 
</div> 



</body>
</html>