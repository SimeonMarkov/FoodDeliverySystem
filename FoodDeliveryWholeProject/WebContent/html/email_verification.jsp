<%@page import="model.dao.DBUserDAO"%>
<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="model.dao.DBUserDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8" />
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/regFields.css" rel="stylesheet" type="text/css"/>
<meta charset="utf-8">
<title>FoodDelivery Profile</title>
<style type="text/css">


</style>
</head>

<body>

<div id="Container">
  <div id="Header"></div>
	
   <div id="Buttons">
    <ul style="float: right">
				<li><a href="home.jsp">Home</a></li>
				<li><a href="search.jsp">Search</a></li>
				<li><a href="faq.html">Guide</a></li>
				<li><a href="sign_up.jsp">Register</a></li>
				<li><a href="login.jsp">Login</a></li>
			</ul>       
    </div>           
     
	<div id="Menu" style="min-height:950px"></div>
	<div id="MainBody">
		<p>An email wih a new password has been sent to <c:out value="${sessionScope.email}"/>.</p>
		<% response.setHeader("Refresh", "7; url=home.html"); %>
	</div>
     <div id="Footer"></div>

</div>
   
</body>
</html>