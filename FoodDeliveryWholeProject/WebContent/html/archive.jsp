<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html >
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pagesSwitcher.css" rel="stylesheet" type="text/css" />
<link href="../CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>Food Delivery Архив</title>

<script src="../JS/jquery-2.2.0.min.js"></script>


</head>

<body>

	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<ul>
				<li><a href="layout.html">Home</a></li>
				<li><a href="search.html">Search</a></li>
				<li><a href="cart.jsp">Cart</a></li>
				<li><a href="archive.jsp?type=user">Archive</a>
					<ul>
						<li><a href="archive.jsp?type=user">My archive</a></li>
						<li><a href="archive.jsp?type=trend">Site's archive</a></li>
					</ul></li>

				<li><a href="profile.html">Profile</a></li>
				<li><a href="home.html">Logout</a></li>
			</ul>
		</div>

		<div id="Menu"></div>

		<div id="MainBody">


			<%
				// User u = new User("gosho");
				// session.setAttribute("loggedUser", u);
				// logina trqq da e napravil tova
				String tpe = request.getParameter("type");
				ArrayList<Order> archive = null; 
				final int rowByPage = 2;
				if(tpe.equals("user")) {
					User user = (User) session.getAttribute("loggedUser");
					user.refreshOrders();
					archive = user.getOrdersArchive();
				} else {
					archive = Order.getAllOrders();
				}
				
				String pageNumberS = request.getParameter("page");
				
				
				int pageNumber = 1;
				if (pageNumberS != null) {
					pageNumber = Integer.parseInt(pageNumberS);
				}
				pageNumber--;
				int orderNumber = 0;
				for (Order o : archive) {
					if (orderNumber / rowByPage < pageNumber) {
						orderNumber++;
						continue;
					} else if (orderNumber / rowByPage > pageNumber) {
						break;
					}
					orderNumber++;
					out.println("<table class=\"cartMeal\" style=\"cursor: pointer;\" ");
					out.println("onClick=\"(function(){");
					int mealNumber = 0;
					for (Meal m : o.getMeals()) {
						mealNumber++;
						out.println("$('#skrit" + orderNumber + mealNumber + "').fadeToggle('slow');");
					}
					out.println("})()\">");
			%>
			<tr>
				<td class="cartCol1">
					<img src="data:image/gif;base64,<% out.print(o.getRestaurant().getPhotoBytes()); %>" alt="" width="90"
					height="90" /></td>
				<td class="cartCol2"><span class="mealName"> Ordered
						on:<%
					out.print(new SimpleDateFormat("dd.MM.yy HH:mm").format(o.getDate()));
				%>
				</span> <br /></td>

				<td class="cartCol2"><span class="mealName"> Total
						price: <%
					out.print(new DecimalFormat("#.00").format(o.getPrice()));
				%>
						лв.
				</span> <br /></td>
				<td class="cartCol3"></td>

			</tr>
			</table>
			<%
				mealNumber = 0;
					for (Meal m : o.getMeals()) {
						mealNumber++;
			%>
			<table id="<%out.print("skrit" + orderNumber + mealNumber);%>"
				class="cartMeal" style="display: none">
				<tr>
					<td class="cartCol1"><img src="data:image/gif;base64,<%out.print(m.getPhotoBytes());%>"
						alt="" width="90" height="90"></td>
					<td class="cartCol2">
						<div>
							<span class="mealName">
								<%
									out.print(m.getName());
								%>
							</span> <br>
						</div>
						<div class="divIngredients">
							<span class="mealIngredients"> Съставки: <%
								out.print(m.ingradientsToString());
							%>
							</span>
						</div>


					</td>

					<td class="cartCol3">

						<div class="divPrice">
							<span>Цена:<%
								out.print(new DecimalFormat("#.00").format(m.getPrice()));
							%></span>

						</div>

					</td>

				</tr>
			</table>

			<%
				}
				}
			%>


			<div class="pagesSwitcher">
						<!--  <a href="url">link text</a>  -->
				
						<%
							pageNumber++;
							if(pageNumber>1) {
							out.println("<a href=\"archive.jsp?type="+ tpe +"&page="+1+"\"><b>&lt;&lt;</b></a>&nbsp;"); 
							int p = pageNumber==1 ? 1: pageNumber-1;
							out.println("<a href=\"archive.jsp?type="+ tpe +"&page="+p+"\"><b>&lt;</b></a>&nbsp;"); 
							} else {
								out.println("&lt;&lt;&nbsp;&lt;&nbsp;");
							}
							out.print(pageNumber+"&nbsp;");
							if(archive.size()>pageNumber*rowByPage) {
								int p = archive.size()/rowByPage;
								if(archive.size()%rowByPage!=0) {
									p++;
								}
								out.println("<a href=\"archive.jsp?type="+ tpe +"&page="+(pageNumber+1)+"\"><b>&gt;</b></a>&nbsp;");
								out.println("<a href=\"archive.jsp?type="+ tpe +"&page="+p+"\"><b>&gt;&gt;</b></a>&nbsp;"); 
								
							} else {
								out.println("&gt;&nbsp;&gt;&gt;");
							}
						%>
				
			</div>

		</div>
		<div id="Footer"></div>

	</div>
</body>
</html>