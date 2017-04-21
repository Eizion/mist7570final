<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <link type="text/css" rel="stylesheet" href="resources/css/stylesheet.css"/>
        <title>MIST 7570 Final</title>
</head>
<body>

	<h1>Please register for an account</h1>
	
	<form name="registration" action="register" method="post">
    
     <label>
        Username: 
     </label>
     <input type="text" name="user" required /><br />
     <label>
        Password: 
     </label>
     <input type="password" name="password" required/><br />
     <label>
        First Name: 
     </label>
     <input type="text" name="fname" required/><br />
     <label>
        Last Name: 
     </label>
     <input type="text" name="lname" required/><br />
     <input type="submit" name="submit" value="Register">
     
  	</form>

</body>
</html>