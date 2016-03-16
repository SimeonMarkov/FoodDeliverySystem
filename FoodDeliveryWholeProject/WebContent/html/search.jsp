<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>Food Delivery Search</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
function callAddToCart(p) {
	dataString = "mealId=" + p;
	$.ajax({
		type: "POST",
		url: "../AddToCartServlet",
		data: dataString,
		dataType : "json",
		
		success: function(data,textStatus,jqXHR){
			$("#cartButton").text("Cart [" + data.count +"]");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Something really bad happened " + textStatus + " - " + errorThrown);
		}
	});
		
	
}
</script>

<style>
td {
	border: 1px solid black;
	width: 216px;
	height: 180px;
}

table {
	border-collapse: collapse;
}
</style>





</head>

<body>
	<jsp:include page="/SearchServlet" />

	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<c:choose>
				<c:when test="${empty sessionScope.loggedUser}">
					<ul style="float: right">
						<li><a href="home.jsp">Home</a></li>
						<li><a href="search.jsp">Search</a></li>
						<li><a href="faq.html">Guide</a></li>
						<li><a href="sign_up.jsp">Register</a></li>
						<li><a href="login.jsp">Login</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li><a href="home.jsp">Home</a></li>
						<li><a href="search.jsp">Search</a></li>
						<li><a href="cart.jsp" id="cartButton">Cart
								[${sessionScope.loggedUser.getCartSize()}] </a></li>
						<li><a href="archive.jsp">Archive</a>
							<ul>
								<li><a href="archive.jsp">My archive</a></li>
								<li><a href="archive.jsp?type=trending">Site's archive</a></li>
							</ul></li>

						<li><a href="profile.jsp">Profile</a></li>
						<li><a href="../LogoutServlet">Logout</a></li>
					</ul>
				</c:otherwise>
			</c:choose>

		</div>


		<div id="Menu">
			<!--<ul>
		<li><a href="pizzas.html">Pizza</a></li>
		<li><a href="pastas.html">Pasta</a></li>
		<li><a href="salads.html">Salad</a></li>
		<li><a href="soups.html">Soup</a></li>
		<li><a href="bbq.html">BBQ</a></li>
		<li><a href="pork.html">Pork</a></li>
		<li><a href="beef.html">Beef</a></li>
		<li><a href="chicken.html">Chicken</a></li>
		<li><a href="fish.html">Fish</a></li>
	</ul>-->
		</div>

		<div id="MainBody">
			<c:if test="${not empty requestScope.errorPrice}">
			  <p style="color: red;text-align: center;font-size: large;">Невалидна цена , моля коригирайте!</p>
			</c:if>
			<form action="search.jsp" method="POST"
				style="width: 600px; padding-bottom: 5px; height: auto; margin-left: 25px; margin-right: 25px; margin-top: 10px; border: solid #8C8153; border-width: 1px; background: #ECE08C; text-align: center;">

				<strong>I'd like</strong>
				 <select id="mtValue" name="mtValue">
					<!-- all mealtypes -->
					
						<option value="-1">Всички</option>
					<c:forEach var="mt" items="${requestScope.mealTypes}">
						<option value="${mt.getId()}">${mt.getType()}</option>
					</c:forEach>
				</select> <strong>from</strong> 
				<select id="restValue" name="restValue">
					<!-- all resturants -->
					<option value="-1">Всички</option>
					<c:forEach var="rest" items="${requestScope.restoranti}">
						<option value="${rest.getRestId()}">${rest.getName()}</option>
					</c:forEach>
				</select>
				
				
				 <strong>for at most</strong>
				 
				 <input type="text" name="price" id="price" /> BGN <br />





				<div style="margin-top: 30px;">
					<strong>Search by ingredients</strong>

					<div>
						<!-- all ingredients -->
						<c:forEach var="row" items="${requestScope.ingredients}">
							<c:forEach var="col" items="${row}">
								<input style="margin-left: 12px;" type="checkbox" name="c1"
									value="${col.getId()}">${col.getName()}
							</c:forEach>
							<br>
						</c:forEach>

					</div>
				</div>

				<br />
				<div class="form">
					<input type="submit" value="Search"
						style="font-size: 30px; letter-spacing: 5px; width: 202px; height: 40px; border: 1px solid #8C8153; background-color: #C16845; color: #F0EFDF; cursor: pointer;">
				</div>
			</form>
			<c:forEach var="meal" items="${requestScope.results}">
				<table class="cartMeal">
					<tr>
						<td class="cartCol1"><img src="data:image/gif;base64,${meal.getPhotoBytes()}"alt=""
							width="90" height="90"></td>
						<td class="cartCol2">
							<div>
								<span class="mealName"> ${meal.getName()} </span> <br>
							</div>
							<div class="divIngredients">
								<span class="mealIngredients"> Съставки:
									${meal.ingradientsToString()}</span>
							</div>
						</td>
						<td class="cartCol3">
							<div class="divPrice">
								<span>Цена: <fmt:formatNumber value="${meal.getPrice()}"
										type="number" minFractionDigits="2" />
								</span> <input type="button" value="Поръчай"
									style="vertical-align: bottom; text-align: center" onclick="callAddToCart(${meal.getMealId()});">
							</div>
						</td>

					</tr>
				</table>
			</c:forEach>
		</div>
		
		<div id="Footer"></div>

	</div>
</body>
</html>