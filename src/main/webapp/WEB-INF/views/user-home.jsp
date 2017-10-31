<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page buffer="8192kb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script   src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/style.css" />

<meta http-equiv="cache-control" content="no-cache"> <!-- tells browser not to cache -->
<meta http-equiv="expires" content="0"> <!-- says that the cache expires 'now' -->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home</title>
<script   src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

</head>

<style type="text/css">

a.eachBook {
text-decoration:none;
}

</style>

<body>


<!-- <script>

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
			alert(book.bookName);
			$(".searchResult").add("p").text(book.bookName);
			
		},
		error: function(error) {
			alert(error);
		}
	})
	
});
</script> -->




<script type="text/javascript">

/* window.location.replace(location.href); */
 
 

$(document).ready(function() {
	
	
	
	$(document).on('click','#ajaxSearch',function(event){
		
		event.preventDefault();
		var searchType = $('select[name=searchType]').val();
		var value= $('#Search').val();
		var field = {"searchData":value};
		alert(searchType);
		
		if(searchType == 'All') {
			alert("inside all search type");
			$.ajax({
				
				
				url: "searchAll",
				data: field,
				dataType: 'json',
				type: "POST",
				success: function(data){
					alert("inside success");
					var book = data;
					if(book.length < 1) {
						$(".alert").removeClass("hide");
					} else if ( $(".alert").hasClass != true ) {
						$(".alert").addClass("hide");
					}
					$(".allBooks").addClass("hide");
					alert(book.length);
					$(".searchResult").empty();
					for(var i=0; i < book.length; i++) {
						$(".searchResult").append("<div class=\"col-md-3\"><a class=\"eachBook\" href=\"sendMessage?id="+book[i].bookId+" \"> <p style=\"text-align:center \"><img src=\""+book[i].filename+" \" height=\"400\" width=\"250\" /></p> <p style=\"text-align:center \">"+book[i].bookName+" </p></a>  ");
					}
					
					
				},
				error: function(error) {
					alert("error");
				}
			})
			
		} else {
		
		$.ajax({
		
			
			url: "search",
			data: field,
			dataType: 'json',
			type: "POST",
			success: function(data){
				alert("inside success");
				var book = data;
				
				if(book.length < 1) {
				$(".alert").removeClass("hide");
				} else if ( $(".alert").hasClass != true ) {
					$(".alert").addClass("hide");
				}
				
				$(".allBooks").addClass("hide");
				alert(book.length);
				
				$(".searchResult").empty();
				for(var i=0; i < book.length; i++) {
					alert(i+" iteration");
					$(".searchResult").append("<div class=\"col-md-3\"><a class=\"eachBook\" href=\"sendMessage?id="+book[i].bookId+" \"> <p style=\"text-align:center \"><img src=\""+book[i].filename+" \" height=\"400\" width=\"250\" /></p> <p style=\"text-align:center \">"+book[i].bookName+" </p></a>  ");
					
					/* $(".searchResult").add("div")).addClass("col-md-3")).add("p")).css("text-align", "center")).html("<img src=\""+book[i].filename+"\" height=\"200\" width=\"150\" />")); */
					/* ((($(".searchResult div.col-md-3").add("p")).css("text-align", "center")).html(book[i].bookName)); */
				}
				
				
			},
			error: function(error) {
				alert("error");
			}
		})
		
	}
		
	});
	
	
	
	
	
	/* var historyCount = $("input[name=history]").val();
	alert("back trigger");
	if(historyCount == "0") {
		$.ajax({
			
			
			url: "getNotifications",
			data:{},
			dataType: 'json',
			type: "GET",
			success: function(data){
				
				
				console.log(data);
				$(".notifications").text(data);
				
			},
			error: function(error) {
				alert("inside error ajax");
				alert(error);
			}
		});
	} */
	

	

})

</script>


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

<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>

<%-- <h1>Hi, ${user.userName}</h1>

<a href="addBook">Add Book</a>
<a href="viewBooks">View Books</a>
<a href="getMessages">Messages <b class="notifications"></b></a> --%>

<div class="container">
<div class="alert alert-danger hide" style="width:50%;">
  <strong> No books found..!!</strong>
</div>
</div>

<div class="allBooks container">
 <c:choose>
	
	<c:when test="${fn: length(bookList) gt 0 }"> 
	<div class="row">
	<c:forEach var="i" items="${bookList }">
	<c:if test="${user.userID != i.user.userID }">
	<div class="col-md-3">
	<a href="sendMessage?id=${i.bookId}" class="eachBook">
	
	<p><img height="400" width="250" src='<c:out value="${i.filename}"/>' /></p>
	<%-- <c:out value="${i.bookId}" /> --%>
	<p style="text-align:center"><c:out value="${i.bookName }" /></p>
	<%-- <c:out value="${i.isbn }" /> --%> 
	
	 
	
	</a>
	</div>
	</c:if>
	</c:forEach>
	</div>
	 </c:when>
	 
	 <c:otherwise>
	 	<h2>No books found</h2>
	 </c:otherwise>

</c:choose> 
</div>

<div class="container" >
<div class="searchResult row"></div>
</div>







<!-- <br><br>
<input type="text" class="history" name="history" value="0"/>  -->


</body>
</html>