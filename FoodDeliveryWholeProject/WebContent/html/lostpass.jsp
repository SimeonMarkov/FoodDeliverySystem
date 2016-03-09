<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <ul style="float:right">
        <li><a href="home.html">Home</a></li>
		<li><a href="search2.html">Search</a></li>
		<li><a href="faq.html">Guide</a></li>		
		<li><a href="sign_up.html">Register</a></li>
		<li><a href="login.html">Login</a></li>
		</ul>            
    </div>           
     
	<div id="Menu" style="min-height:950px"></div>
	<div id="MainBody">

	
    <fieldset  style="border:0px solid black;">
    <caption><b>Забравена парола</b></caption>
         <form action="../ForgottenPass" method="post">
         <div class="form">	
         
			<label for="username"  class="title">Username:</label>
			<input type="text" name="username" value="<%= ((User)session.getAttribute("failLog")).getUsername() %>" readonly>  <br>
		</div>
    	
        <div class="form">
			<label for="question"  class="title">Secret question:</label>
			<input style="width:250px" type="text" name="question" value="<%= ((User)session.getAttribute("failLog")).getSecretQuestion() %>" readonly/><br />
		</div>
		<div class="form">
			<label for="answer"  class="title">Secret answer:</label>
			<input style="width:250px" type="text" name="answer" required/><br />
		</div>
        <br>
       
         <p style="text-align:center">Отговорете на секретният си въпрос и новогенерираната ви парола ще бъде изпратена на вашия e-mail</p>
            <br>
			<input type="submit" value="Изпрати" name="submit" style="font-size:20px; letter-spacing:2px; width:102px;height:30px;border:1px solid #8C8153;background-color:#C16845;color:#F0EFDF; margin-left:275px" />
		</form>
   </fieldset>
           
           
		</div>
     <div id="Footer"></div>

</div>
   
</body>
</html>