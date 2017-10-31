<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page buffer="8192kb" %>
<html>
<head>
	<title>Home</title>

</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/style.css" />

<script src="js/login.js"></script>

<script>

$(document).on('click','#ajaxSearch',function(event){
	
	event.preventDefault();
	var value= $('#Search').val();
	var field = {"searchData":value};
	alert("inside alert");
	
	
	
	$.ajax({
	
		
		url: "search",
		data: field,
		dataType: 'json',
		type: "POST",
		success: function(data){
			var book = data;
			alert("ala bc book!!!");
			alert(book.length);
			$(".searchResult").add("p").text(book.bookName);
			
		},
		error: function(error) {
			alert(error);
		}
	})
	
});
</script>

<body>

<nav class="navbar navbar-default backGrnd">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Book Shelf</a>
    </div>
    <ul class="nav navbar-nav">
      <!-- <li class="active"><a href="#">Home</a></li> -->
  
    </ul>
    <form class="navbar-form navbar-left" action="search" method="post">
      <div class="form-group">
        <input type="text" class="form-control" name="search" id="Search" placeholder="Search">
      </div>
      <div class="form-group ">
  <!-- <label for="sel1">Select list:</label> -->
  <select class="form-control" style="width:100%;" id="sel1">
    <option value="All">All</option>
    <option value="BookName">Book Name</option>
    <option value="ISBN">ISBN</option>
    
  </select>
</div>
      <button type="submit" id="ajaxSearch" class="btn btn-default">Search</button>
    </form>
    
    <ul class="nav navbar-nav navbar-right">
      <li><a href="user/add.htm"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="login" ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>

<%-- <c:forEach var="i" items="${bookList }">
	<c:out value="${i.bookId}" />
	<c:out value="${i.bookName }" />
	<c:out value="${i.isbn }" /> <br>
	
</c:forEach>
<br>
<br> --%>

<!-- <form action="search" method="post">
<input type="text" name="search" id="Search">
<input type="submit" id="ajaxSearch" value="Search">
</form> -->

<%-- <div class="searchResult"></div>

<c:if test="${book != null }">
	<c:out value="${book.bookName }"></c:out>
	<c:out value="${book.isbn }"></c:out>
</c:if>

<br>
<br>
<a href="user/add.htm">Register a new User</a><br/>

	<h2>Login</h2>
	<form action="user/login" method="post">
	
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input name="userName" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form> --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
