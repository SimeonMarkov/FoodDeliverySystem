<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/regFields.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>FoodDelivery Profile</title>
<style type="text/css">
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

<body>
<jsp:include page="../ChooseServlet"></jsp:include>

	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<ul style="float: right">
				<li><a href="home.html">Home</a></li>
				<li><a href="search2.html">Search</a></li>
				<li><a href="faq.html">Guide</a></li>
				<li><a href="sign_up.html">Register</a></li>
				<li><a href="login.html">Login</a></li>
			</ul>
		</div>
		<div id="Menu" style="min-height: 950px"></div>
		<div id="MainBody">


			
				<h3 style="margin-left: 10px;">Моля изберете адрес</h3>
				<c:forEach var="address" items="${sessionScope.addressesOnLogged}">
					<form action="../ChooseServlet" method="post">
					<div style="margin-top: 5px; height: 180px; cursor: pointer;" onclick="$('#b${address.getAddressId()}').click()">
						<input type="hidden" name="addressId" value="${address.getAddressId()}">
						
						 <label for="kvartal1" class="kvartal">Квартал</label> 
						 
						 <input type=text name="kvartal1" value="${address.getNeighbourhood()}"
							readonly />
							
							<br> <br> <label for="address1"
							class="kvartal">Adress</label>

						<textarea class="addressField" name="address1" rows="5" cols="60"
							readonly>${address.getFullAddress()}</textarea>
						<br>
					</div>
					<input type="submit" id="b${address.getAddressId()}" style="display: none"/> 
					</form>
					<hr style="margin-left: 25px; margin-right: 25px;">
				</c:forEach>
				

			

		</div>
		<div id="Footer"></div>

	</div>
</body>
</html>