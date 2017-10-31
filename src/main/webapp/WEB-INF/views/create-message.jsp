<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script   src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<title>Create message</title>
</head>
<c:set var="book" value="${book}" scope="request"/>
<script type="text/javascript">

$(document).ready(function() {
	
	/* $( ".messageToggle" ).toggle(function() {
		  $(".messageToggle").text("Cancel message");
		  
		  $(".messageBody").removeClass("hide");
		}, function() {
			$(".messageToggle").text("Send message");
			  
			  $(".messageBody").addClass("hide");
		}); */
	
	
	$(".messageToggle").click(function() {
		/* if($(".messageToggle").val() == 'Send Message') {
			$(".messageToggle").text("Cancel Message");
		} 
		if($(".messageToggle").val() == 'Cancel Message') {
			$(".messageToggle").text("Send Message");
		} */
		
		$(this).text(function(i, text){
	          return text === "Send Message" ? "Cancel Message" : "Send Message";
	      })
		$(".messageBody").toggle();
	})
	
})

</script>
<body>





<nav class="navbar navbar-default backGrnd">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Book Shelf</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="addBook">Add Book</a></li>
      <li ><a href="myShelf">My Shelf</a></li>
      <li ><a href="getMesages">Messages<b class="notifications"></b></a></li>
  
    </ul>
    
    <%-- <form class="navbar-form navbar-left" action="search" method="post">
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
    </form> --%>
    
    <ul class="nav navbar-nav navbar-right">
      <li><a><span class="glyphicon glyphicon-user"></span> Hello, ${user.userName}</a></li>
      <li><a href="/onlineBMP/logout.htm" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<div class="container">

<div class="row">

<div class="col-md-4"><img alt="" height="400" width="250" src="${book.filename}"/></div>
<div class="col-md-8">
<h2>${book.bookName}</h2>
<p>ISBN no. ${book.isbn}</p>
<p>Author : <em>${book.author}</em></p>
<p><button class="messageToggle">Send Message</button></p>

<div class="messageBody" style="display:none;"><form:form action="createMessage" commandName="message"
		method="post">

		<table>
		
			<tr>
				<td>Message</td>
				<td><form:textarea path="message" size="1000"
						required="required" /><%--  <font color="red"><form:errors
							path="password" /></font> --%></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Send" /></td>
			</tr>
		</table>

	</form:form></div>
	
	<c:if test="${messageFlag == true }"><div class="alert alert-success">
  <strong> Message sent successfully..!!</strong>
</div></c:if>

</div>

</div>

</div>




<%-- <h2>Create message</h2>

	<form:form action="createMessage" commandName="message"
		method="post">

		<table>
		
			<tr>
				<td>Message</td>
				<td><form:input path="message" size="100"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Send message" /></td>
			</tr>
		</table>

	</form:form> --%>

</body>
</html>