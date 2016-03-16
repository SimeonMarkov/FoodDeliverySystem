<%@ page import="model.User"%>
<%@ page import="model.dao.DBNeighbourhoodDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li><a href="cart.jsp">Cart</a></li>
				<li><a href="archive.html">Archive</a>
					<ul>
						<li><a href="archive.html">My archive</a></li>
						<li><a href="archiveTrending.html">Site's archive</a></li>
					</ul></li>

				<li><a href="profile.jsp">Profile</a></li>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="Menu" style="min-height: 950px"></div>
		<div id="MainBody">

			<form action="../ProfileServlet" method="post">
				<fieldset style="border: 0px solid black;">
					<caption>
						<b>Profile</b>
					</caption>
					<div class="form">
						<label for="username" class="title">Username:</label> <input
							type="text" name="username"
							value="<%=session.getAttribute("loggedUser") != null ? ((User) session
					.getAttribute("loggedUser")).getUsername() : "KK"%>"
							readonly> <br>
					</div>
					<div class="form">
						<label for="Old password" class="title">Old password:</label> <input
							type="password" name="Old password" required />
						<%
							if (session.getAttribute("wrongPass") != null) {
						%>
						<label style="color: red;">Грешна текуща парола!</label>
						<%
							}
							session.removeAttribute("wrongPass");
						%><br />
					</div>
					<div class="form">
						<label for="New password" class="title">New password:</label> <input
							type="password" name="New password" required />
						<%
							if (session.getAttribute("mismatchPass") != null) {
						%>
						<label style="color: red">Двете пароли се различават!</label>
						<%
							}
							session.removeAttribute("mismatchPass");
						%><br />
					</div>
					<div class="form">
						<label for="Retype new password" class="title">Retype new
							password:</label> <input type="password" name="Retype new password"
							required /><br />
					</div>

					<div class="form">
						<label for="email" class="title">Email:</label>
						<%
							if (session.getAttribute("loggedUser") == null) {
								response.sendRedirect("ShowError.jsp");
							}
						%>
						<input type="email" name="email" required="required"
							value="<%=session.getAttribute("loggedUser") != null ? ((User) session
					.getAttribute("loggedUser")).getEmail() : "NaN"%>" /><br />
					</div>
				</fieldset>
				<br> <br>
				<hr />
				<fieldset style="border: 0px solid black;">
					<caption>
						<b>Адреси</b>
					</caption>
					
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
					
					
					<div style="margin-top: 5px;">
						<h3>Add address</h3>
						<label for="kvartal1" class="kvartal">Квартал</label>
						<%
							request.setAttribute("neighbourhoods", DBNeighbourhoodDAO
									.getInstance().getAllNeighbourhoods());
						%>
						<select name="neighbourhoodOptions">
							<c:forEach var="n" items="${requestScope.neighbourhoods}">
								<option value="${n}"><c:out value="${n}"></c:out></option>
							</c:forEach>
						</select><br>
						<br> <label for="address1" class="kvartal">Adress</label>

						<textarea class="addressField" name="fullAddress" rows="5"
							cols="60" required> </textarea>
						<br>
					</div>
				</fieldset>

				<br> <br>
				<hr>
				<div class="form">
					<input type="submit" value="Запази" name="submit"
						style="font-size: 20px; letter-spacing: 2px; width: 102px; height: 30px; border: 1px solid #8C8153; background-color: #C16845; color: #F0EFDF; float: right; cursor: pointer;" />
				</div>
			</form>
		</div>
		<div id="Footer"></div>

	</div>
</body>
</html>