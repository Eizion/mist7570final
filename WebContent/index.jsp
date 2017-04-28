<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <link type="text/css" rel="stylesheet" href="resources/css/stylesheet.css"/>
        <title>MIST 7570 Final</title>
</head>
<body>

	<div class="header">
    	<div class="nav">    				
		</div>		
		<h2 style="font-family: Verdana">MIST 7570 Final</h2>        
	</div>
    
    <h1>Log In</h1>
    
    <form id="loginForm" action="login" method="post">
	
		<label>Username</label>
		<input type="text" name="username" required /><br>
		<label>Password</label>
		<input type="password" name="password" required /><br>
		<input type="submit" value="Login">
		${errorMessage}
	</form>
	
	<br>
	<a href="registerForm.jsp">register</a>
	
		<div class="footer">
		<p>MIST 7500 Final by Daniel Crittenden and Joe Vo</p>
	</div>
	
</body>
</html>