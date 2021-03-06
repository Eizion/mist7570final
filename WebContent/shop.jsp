<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	String table = (String) request.getAttribute("table");

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/stylesheet.css"/>
<title>Online Store - Product Inventory</title>
</head>
<body>

	<div class="header">
    	<div class="nav">    		
    		<ul id="nav_ul">
				<li class="nav_li"><a href="welcome.jsp">Home</a></li>
				<li class="nav_li"><a href="shop">Shop</a></li>
				<li class="nav_li"><a href="cart">Cart</a></li>
				<li class="nav_li"><a href="login?logout=true">Logout</a></li>
			</ul>			
		</div>		
		<h2 style="font-family: Verdana">MIST 7570 Final</h2>        
	</div>
	
	<div class="wrapper">
	
		<h1>Product Inventory</h1>
		${msg }

		<div style="overflow-x:auto;">
			<%= table %>
		</div>

		<div class="push"></div>

	</div>

	<footer class="footer">
		<p>MIST 7570 Final by Daniel Crittenden and Joe Vo</p>
	</footer>
	
</body>
</html>