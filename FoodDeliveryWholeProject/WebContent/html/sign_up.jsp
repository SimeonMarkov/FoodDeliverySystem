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
<title>Food Delivery Login</title>
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
	
	<div id="Menu">
    </div>
	
	<div id="MainBody">
	
	<fieldset  style="border:0px solid black;">
		<caption><b>Registration</b></caption>
		<form action="../SignupServlet" method="post">
		
		
		<div class="form">
			<label for="username" class="title">Username:</label>
			<input type="text" name="username" required="required" />
			<% if(request.getAttribute("usernameError") == null) { %><label style="color: red;">*Потребителското име е заето</label><br /><% } request.removeAttribute("usernameError"); %>
		</div>
		<div class="form">	
			<label for="password"  class="title">Password:</label>
			<input type="password" name="password" required="required" /><br />
		</div>	
		<div class="form">
			<label for="email" class="title">Email:</label>
			<input type="email" name="email" required="required" /><% if(request.getAttribute("emailError") == null) { %><label style="color: red;">*Имейлът вече е зает</label><br /><% }request.removeAttribute("emailError"); %><br />
		</div>
		<div class="form">
			<label for="question"  class="title">Secret question:</label>
			<input type="text" name="question" required="required" /><br />
		</div>
		<div class="form">
			<label for="answer"  class="title">Secret answer:</label>
			<input type="text" name="answer" required="required" /><br />
		</div>
         <fieldset  style="border:0px solid black;">
         <hr style="margin-left:25px;margin-right:25px;">
    <caption><b>Адрес</b></caption>
    <datalist id="kvartal">
    <option value="Борово">
    <option value="Дружба">
    <option value="Надежда">
    <option value="Гео Милев">
    <option value="Хаджи Димитър">
    </datalist> 
    
    
    
     <div style="margin-top:5px;">
    
    <label for="kvartal1" class="kvartal">Квартал</label>
    <input list="kvartal" name="kvartal1" required ><br><br>
    <label for="address1" class="kvartal">Adress</label>
    
    <textarea  class="addressField" name="address1" value="Борово" rows="5" cols="60" required> </textarea>
    <br>
    </div>
    </fieldset>
		<div class="form">
        	<input type="submit" value="Submit" style="font-size:20px; letter-spacing:2px; width:102px;height:30px;border:1px solid #8C8153;background-color:#C16845;color:#F0EFDF; float:right;cursor:pointer;"/>
		</div>
	 		
		</form>
	</fieldset>
	
	<hr />
	</div>
    <div id="Footer"></div>

</div>
	
</body>
</html>