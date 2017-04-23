<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	String table = (String) request.getAttribute("table");

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href=style.css>
<title>Online Store - Product Inventory</title>
</head>
<body>
<div class=header>
<h1>Product Inventory</h1>
</div>
<div style="overflow-x:auto;">
	<%= table %>
</div>
<br />
</body>
</html>