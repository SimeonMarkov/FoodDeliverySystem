<%@page import="model.Neighbourhood"%>
<%@page import="model.dao.DBNeighbourhoodDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="ShowError.jsp" %>
<!doctype html>
<html>
<head>
<link href="${pageContext.request.contextPath}/CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/CSS/regFields.css" rel="stylesheet" type="text/css"/>
<meta charset="utf-8">
<title>Food Delivery Login</title>
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
	
	<div id="Menu">
    </div>
	
	<div id="MainBody">
	
	<fieldset  style="border:0px solid black;">
		<caption><b>Registration</b></caption>
		<form action="${pageContext.request.contextPath}/SignupServlet" method="post">
		
		
		<div class="form">
			<label for="username" class="title">Username:</label>
			<input type="text" name="username" required="required" />
			<c:if test="${!empty sessionScope.usernameError}">
				<label style="color: red;">*Потребителското име е заето</label><br />
			</c:if>
			<c:remove var="usernameError" scope="session" />
		</div>
		<div class="form">	
			<label for="password"  class="title">Password:</label>
			<input type="password" name="password" required="required" />
			<c:if test="${!empty sessionScope.weakPasswordError}">
				<label style="color: red;">*Паролата е слаба</label>
			</c:if>
			<c:remove var="weakPassword" scope="session" />
			
			<br />
		</div>	
		<div class="form">
			<label for="email" class="title">Email:</label>
			<input type="email" name="email" required="required" />
			
			<c:if test="${!empty sessionScope.usedEmailError}">
				<label style="color: red;">*Имейлът вече е зает</label><br />
			</c:if>
			<c:remove var="usedEmailError" scope="session" />
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
    
    
    
    
     <div style="margin-top:5px;">
    
    <label for="kvartal1" class="kvartal">Квартал</label>
    <% request.setAttribute("neighbourhoods", DBNeighbourhoodDAO.getInstance().getAllNeighbourhoods()); %>
    <select name="neighbourhoodOptions">
		<c:forEach var="n" items="${requestScope.neighbourhoods}">
		   <option  value="${n.getId()}" ><c:out value="${n.getName()}"></c:out></option>
		</c:forEach>
	</select><br><br>
    <label for="address1" class="kvartal">Adress</label>
    
    <textarea  class="addressField" name="fullAddress" rows="5" cols="60" required> </textarea>
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