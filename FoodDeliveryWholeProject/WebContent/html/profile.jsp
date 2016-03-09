<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<li><a href="cart.html">Cart</a></li>
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

	<form action="../ProfileServlet" method="post">
    <fieldset  style="border:0px solid black;">
    <caption><b>Profile</b></caption>
         <div class="form">
			<label for="username"  class="title">Username:</label>
			<input type="text" name="username" value="<% out.println(((User)request.getSession().getAttribute("loggedUser")).getUsername()); %>" readonly> <br>
		</div>
    	<div class="form">	
			<label for="Old password"  class="title">Old password:</label>
			<input type="password" name="Old password" required /><br />
		</div>	
        <div class="form">	
			<label for="New password"  class="title">New password:</label>
			<input type="password" name="New password" required /><br />
		</div>	
        <div class="form">	
			<label for="Retype new password"  class="title">Retype new password:</label>
			<input type="password" name="Retype new password" required /><br />
		</div>	
		
        <div class="form">
			<label for="email" class="title">Email:</label>
			<input type="email" name="email" required="required" value="<% out.println(((User)request.getSession().getAttribute("loggedUser")).getEmail()); %>"/><br />
		</div>
   </fieldset>
   <br><br><hr />
    <fieldset  style="border:0px solid black;">
    <caption><b>Адреси</b></caption>
    <datalist id="kvartal">
    <option value="Борово">
    <option value="Дружба">
    <option value="Надежда">
    <option value="Гео Милев">
    <option value="Хаджи Димитър">
    </datalist> 
    <div style="margin-top:5px;height:180px;">
    
    <label class="kvartal">Избран</label>
    <input type="radio"  name="izbran" checked>
    <br><br>
    <label for="kvartal1" class="kvartal">Квартал</label>
    <input list="kvartal" name="kvartal1" value="Борово"><br><br>
    <label for="address1" class="kvartal">Adress</label>
    
    <textarea  class="addressField" name="address1" rows="5" cols="60"> ул.Бай Благой 26, бл.56, вх.А, ет.5, ап.24</textarea>
    <br>
    </div>
    <hr style="margin-left:25px;margin-right:25px;">
     <div style="margin-top:5px;height:180px;">
    
    <label class="kvartal">Избран</label>
    <input type="radio" name="izbran">
    <br><br>
    <label for="kvartal1" class="kvartal">Квартал</label>
    <input list="kvartal" name="kvartal1" value="Дружба"><br><br>
    <label for="address1" class="kvartal">Adress</label>
    
    <textarea  class="addressField" name="address1" rows="5" cols="60">ул.Баба Злата 54 , бл.32 , вх.Б , ап.35 </textarea>
    <br>
    </div>
    <hr style="margin-left:25px;margin-right:25px;">
    
     <div style="margin-top:5px;">
      <label class="kvartal">Избран</label>
    <input type="radio" name="izbran">
    <br><br>
    <label for="kvartal1" class="kvartal">Квартал</label>
    <input list="kvartal" name="kvartal1" ><br><br>
    <label for="address1" class="kvartal">Adress</label>
    
    <textarea  class="addressField" name="address1" rows="5" cols="60"></textarea>
    <br>
    </div>
    </fieldset>
    
    <br><br><hr>
    <div class="form">
			<input type="submit" value="Запази" name="submit" style="font-size:20px; letter-spacing:2px; width:102px;height:30px;border:1px solid #8C8153;background-color:#C16845;color:#F0EFDF; float:right;cursor:pointer;" onClick="window.location='layout.html';" />
		</div>
		</form>
	</div>
    <div id="Footer"></div>

</div>
</body>
</html>