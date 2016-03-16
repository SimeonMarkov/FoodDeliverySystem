<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>Food Delivery</title>
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
</head>

<body>
	<jsp:include page="/MenuServlet"></jsp:include>
	<c:if test="${notLogged}">
		<c:redirect url="login.jsp">
			<c:param name="URL" value="${requestScope.U}?restId=${param.restId}"/>
		</c:redirect>
	</c:if>
	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<ul>
				<li><a href="layout.html">Home</a></li>
				<li><a href="search.html">Search</a></li>
				
				<li><a id="cartButton" href="cart.jsp">Cart [0]</a></li>
				
				<li><a href="archive.html">Archive</a>
					<ul>
						<li><a href="archive.html">My archive</a></li>
						<li><a href="archiveTrending.html">Site's archive</a></li>
					</ul></li>

				<li><a href="profile.jsp">Profile</a></li>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="Menu">
			<ul>
				<c:forEach var="mt" items="${requestScope.mealType}">
					<li><a
						href="${request.getRequestURL()}?restId=${param.restId}&mealId=${mt.getId()}">${mt.getType()}</a></li>
				</c:forEach>
				<!--<li><a href="pizzas.html">Pizza</a></li>
				<li><a href="pastas.html">Pasta</a></li>
				<li><a href="salads.html">Salad</a></li>
				<li><a href="soups.html">Soup</a></li>
				<li><a href="bbq.html">BBQ</a></li>
				<li><a href="pork.html">Pork</a></li>
				<li><a href="beef.html">Beef</a></li>
				<li><a href="chicken.html">Chicken</a></li>
				<li><a href="#">Fish</a></li> -->
			</ul>
		</div>

		<div id="MainBody">
			<c:forEach var="meal" items="${requestScope.menu}">
				<table class="cartMeal">
					<tr>
						<td class="cartCol1"><img src="../images/meat.jpg" alt=""
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
