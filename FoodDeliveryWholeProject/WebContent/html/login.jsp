<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
<!doctype html>
<html>
<head>
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
    <ul style="float:right">
        <li><a href="home.html">Home</a></li>
		<li><a href="search2.html">Search</a></li>
		<li><a href="faq.html">Guide</a></li>		
		<li><a href="sign_up.jsp">Register</a></li>
		<li><a href="login.html">Login</a></li>
		</ul>            
    </div>  
	<div id="Menu" style="min-height:950px"></div>
		<div id="MainBody">
	
	<fieldset  style="border:0px solid black;">
		<caption><b>Login</b></caption>
		<form action="../LoginServlet" method="post">
		<% if(request.getAttribute("loginError") == null) {%>
		<p style="color: red;">Грешно потребителско име или парола! </p>
		<% } %>
		<div class="form">
			<label for="username" class="title">Username:</label>
			<input type="text" name="username" required="required" /><br />
		</div>
		<div class="form">	
			<label for="password"  class="title">Password:</label>
			<input type="password" name="password" required="required" /><br />
		</div>	
         <br>
        <a href="sign_up.jsp" style="margin-left:140px;">Създай нов акаунт</a>
        <a href="lostpass.jsp" style="margin-left:10px;">Забравена парола</a>
		<div class="form">
        	<input type="submit" value="Submit" name="submit" style="font-size:20px; letter-spacing:2px; width:102px;height:30px;border:1px solid #8C8153;background-color:#C16845;color:#F0EFDF; float:right;cursor:pointer;"/>
		</div>
		
		</form>
	 		
		
	</fieldset>
	
	<hr />
	</div>
</body>
</html>