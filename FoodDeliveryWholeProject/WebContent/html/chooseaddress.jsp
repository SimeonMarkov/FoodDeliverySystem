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

<body>

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


			<form action="../ChooseServlet" method="post">
			<h2>Choose your address:</h2>
				<c:forEach var="address" items="${sessionScope.addressesOnLogged}">
					<div style="margin-top: 5px; height: 180px; cursor: pointer;">
						<input style="margin-left: 60px;" type="radio"
							name="chosenAddress" value="${address.getNeighbourhood()}"/><br> <label for="kvartal1"
							class="kvartal">Квартал</label> <input type=text name="kvartal1"
							value="${address.getNeighbourhood()}" readonly /><br> <br>
						<label for="address1" class="kvartal">Adress</label>

						<textarea class="addressField" name="address1" rows="5" cols="60"
							readonly>${address.getFullAddress()}</textarea>
						<br>
					</div>
					<hr style="margin-left: 25px; margin-right: 25px;">
				</c:forEach>
				<div class="form">
					<input type="submit" value="Submit" name="submit"
						style="font-size: 20px; letter-spacing: 2px; width: 102px; height: 30px; border: 1px solid #8C8153; background-color: #C16845; color: #F0EFDF; float: right; cursor: pointer;" />
				</div>

			</form>

		</div>
		<div id="Footer"></div>

	</div>
</body>
</html>