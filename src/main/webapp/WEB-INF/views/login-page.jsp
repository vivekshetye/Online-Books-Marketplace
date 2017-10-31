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
<title>Login page</title>
</head>
<body>

<div class="container-fluid" style="text-align:center; color:#ffffff; background-color:#01579B"><h1>Welcome to Book Shelf</h1> </div>

<div class="container" style="margin-top:5%">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue</h1>
            <div class="account-wall">
               
                <form class="form-signin" action="user/login" method="post">
                <input type="text" class="form-control" name="userName" placeholder="Username" required="required" autofocus="autofocus">
                <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                
                <span class="clearfix"></span>
                </form>
            </div>
            <a href="user/add.htm" class="text-center new-account">Register </a>
        </div>
    </div>
</div>

</body>
</html>