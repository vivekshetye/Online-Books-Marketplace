<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>My messages</title>
<script   src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
</head>
<body>


<nav class="navbar navbar-default backGrnd">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Book Shelf</a>
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

<%-- <c:if test="${sessionScope.user != null }">

<script type="text/javascript">
$(document).ready(function() {
var timer, delay = 3000; //5 minutes counted in milliseconds.

/* timer = setInterval(function(){ */
    $.ajax({
      data: {},
      type: 'GET',
      dataType: 'json',
      url: 'getMessagesAjax',
      success: function(data){
        //alert(data.length);
    	  for(var i=0; i < data.length; i++) {
    		  var htmlContent = htmlContent + " <p>Sender:"+data[i].sender+"</p><p>Title:"+data[i].title+"</p><p>Message:"+data[i].message+"</p><br>";
    	  }
    	  $('.messages').html(htmlContent);
      }
    });
/* }, delay); */
})
</script></c:if> --%>


<div class="container">

<c:forEach var="i" items="${messageList}">

<h2>Request for: ${i.book.bookName}</h2>
<%-- <c:choose>
	<c:when test="${i.sender.userID == user.userID}">
	
	<strong><c:out value="${i.sender.userName}"></c:out>:</strong>
	
	</c:when>
	
	<c:otherwise>
	<strong><c:out value="${i.receiver.userName }"></c:out>:</strong></c:otherwise>
	
</c:choose> --%>

<strong><c:out value="${i.sender.userName}"></c:out>:</strong>
&nbsp;<c:out value="${i.message}"></c:out><br><br>


<c:if test="${fn: length(replyList) gt 0}">



<c:forEach var="j" items="${replyList}">

<c:if test="${j.message.mesageId == i.mesageId }"> 

<%-- <c:choose>
	<c:when test="${j.senderRply.userID == user.userID}">
	
	<strong><c:out value="${j.senderRply.userName}"></c:out>:</strong>
	
	</c:when>
	
	<c:otherwise>
	<strong><c:out value="${j.receiverRply.userName }"></c:out>:</strong></c:otherwise>
	
</c:choose> --%>
<strong><c:out value="${j.senderRply.userName}"></c:out>:</strong>
&nbsp;<c:out value="${j.replyMessage}"></c:out><br><br>
</c:if> 
</c:forEach> 
</c:if>






<form:form action="createReply" commandName="reply"
		method="post">

		<table>
			

<tr><td><input type="hidden" name="messageId" value="${i.mesageId}" required="required" /><td></tr>
	
	
	<c:choose>
	
	<c:when test="${i.sender.userID == user.userID }">
		<tr><td><input type="hidden" name="senderId" value="${i.receiver.userID}" required="required" /><td></tr>
	</c:when>
	
	<c:otherwise>
	<tr><td><input type="hidden" name="senderId" value="${i.sender.userID}" required="required" /><td></tr>
	</c:otherwise>
	
	</c:choose>
	
	
			

			<tr>
				<td>Message</td>
				<td><form:input path="replyMessage" size="100"
						required="required" /><%--  <font color="red"><form:errors
							path="password" /></font> --%></td>
			</tr>

			
			
			<tr>
				<td colspan="2"><input type="submit" value="Send message" /></td>
			</tr>
		</table>

	</form:form>






</c:forEach> 

</div>
<br><br>



<c:forEach var="k" items="${sentMessages}">

Sent messages
<c:choose>
	<c:when test="${k.sender.userID == user.userID}">
	
	Sent to: <c:out value="${k.receiver}"></c:out><br>
	
	</c:when>
	
	<c:otherwise>
	Sender:<c:out value="${k.sender.userID }"></c:out><br></c:otherwise>
	
</c:choose>
Title:<c:out value="${k.title }"></c:out><br>
Message:<c:out value="${k.message}"></c:out><br><br>
<b>***********************s</b>
<c:if test="${fn: length(k.replies) gt 0}">
<b>---------------------------</b>
<c:forEach var="l" items="${replySent}">
<c:if test="${l.message.mesageId == k.mesageId }"> 

<c:choose>
	<c:when test="${l.sender.userID == session.user.userID}">
	
	Sent to: <c:out value="${l.receiver.userID}"></c:out><br>
	
	</c:when>
	
	<c:otherwise>
	Sender:<c:out value="${l.sender.userID }"></c:out> @@@@@@@@@<br></c:otherwise>
	
</c:choose>

Message:<c:out value="${l.message}"></c:out>@@@@@@@<br><br>
</c:if> 
</c:forEach> 
</c:if>

</c:forEach> 



<div class="messages">

</div>


</body>
</html>