<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!doctype html>
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/regFields.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8" />
<title>FoodDelivery Profile</title>
</head>
<body>
<div id="Container">
  <div id="Header"></div>
	<div id="Buttons">
   <ul>
        <li><a href="layout.html">Home</a></li>
		<li><a href="search.html">Search</a></li>
		<li><a href="cart.jsp">Cart</a></li>
		<li><a href="archive.html">Archive</a>
			<ul>
				<li><a href="archive.html">My archive</a></li>
				<li><a href="archiveTrending.html">Site's archive</a></li>
			</ul>
		</li>
		
		<li><a href="profile.jsp">Profile</a></li>
		<li><a href="logout.jsp">Logout</a></li>
		</ul>          
    </div>  
	<div id="Menu" style="min-height:950px"></div>
	<div id="MainBody">

	<h1>Oops...</h1>
	<p>Something went wrong.Please,try to log in.</p>
	<% response.setHeader("Refresh", "4;url=home.html"); %>
	<p>Redirecting to home page in 4 seconds...</p>
	</div>
    <div id="Footer"></div>

</div>
</body>
</html>