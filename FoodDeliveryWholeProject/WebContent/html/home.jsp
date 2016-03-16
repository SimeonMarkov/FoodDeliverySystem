<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>Food Delivery</title>

</head>

<body>
	<jsp:include page="/HomeServlet" />
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
				<li><a href="cart.jsp">Cart [${sessionScope.loggedUser.getCartSize()}] </a></li>
				<li><a href="archive.jsp">Archive</a>
					<ul>
						<li><a href="archive.jsp">My archive</a></li>
						<li><a href="archive.jsp?type=trending">Site's archive</a></li>
					</ul></li>

				<li><a href="profile.jsp">Profile</a></li>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
			</c:otherwise>
			</c:choose>
			
		</div>
		<div id="Menu">
			<!--TODO: replace links with rest.types to visualize only that type of rest @ mainbody-->
			<ul>
				<c:forEach var="rt" items="${requestScope.restTypes}">
					<li><a href="${request.getRequestURL()}?restId=${rt.getId()}">${rt.getName()}</a></li>
				</c:forEach>
				<!--<li><a href="pizzas.html">Pizza</a></li>
				<li><a href="pastas.html">Pasta</a></li>
				<li><a href="salads.html">Salad</a></li>
				<li><a href="soups.html">Soup</a></li>
				<li><a href="bbq.html">BBQ</a></li>
				<li><a href="pork.html">Pork</a></li>
				<li><a href="beef.html">Beef</a></li>
				<li><a href="chicken.html">Chicken</a></li>
				<li><a href="fish.html">Fish</a></li>-->
			</ul>
		</div>

		<div id="MainBody">
			<c:forEach var="row" items="${requestScope.restRows}">
				<table class="cartMeal">
					<tr>
						<c:forEach var="col" items="${row}">
							<td
								style="width: 50%; border: solid #66562F; border-width: 1px; cursor: pointer"
								onClick="window.location='menu.jsp?restId=${col.getRestId()}';">
								<div style="float: left; width: 90px;">
									<img src="../images/restaurant_images/don_vito.gif" alt=""
										width="90" height="90">
								</div>
								<div style="float: right; width: 220px">
									<span class="mealName"> ${col.getName()} </span> <br>
									<div class="divIngredients">
										<span class="mealIngredients"> Тип: ${col.getTypes()}</span>
									</div>
								</div>
							</td>
						</c:forEach>
						<c:if test="${fn:length(row) lt 2}">
							<td style="width: 50%; border: solid #66562F; border-width: 1px;">
							</td>
						</c:if>

					</tr>
				</table>
			</c:forEach>

		</div>
		<div id="Footer"></div>

	</div>
</body>
</html>
