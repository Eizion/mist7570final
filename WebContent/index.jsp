<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <link type="text/css" rel="stylesheet" href="resources/css/stylesheet.css"/>
        <title>MIST 7570 Final</title>
</head>
<body>
    
    <h1>Log In</h1>
    
    <form id="loginForm" action="Login" method="POST">
	
		<input type="text" name="username" value="Username" required><br>
		<input type="password" name="password" value="Password" required><br>
		<input type="submit" value="Login"><br>

	</form>
	
</body>
</html>