<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ page import="java.util.*" %>
   <%@ page import="model.*" %>
   <%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="CSS/800px.css" rel="stylesheet" type="text/css"  media="screen and (max-width: 800px)" />
<link href="CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="CSS/pagesSwitcher.css" rel="stylesheet" type="text/css" />
<link href="CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>Food Delivery Архив</title>

<script src="JS/jquery-2.2.0.min.js"></script>


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
				<li><a href="#">My archive</a></li>
				<li><a href="archiveTrending.html">Site's archive</a></li>
			</ul>
		</li>
		
		<li><a href="profile.html">Profile</a></li>
		<li><a href="home.html">Logout</a></li>
		</ul>          
    </div>

<div id="Menu">
    </div>	
	
	<div id="MainBody">
	
	
	<%
	User u = new User("gosho");
	session.setAttribute("loggedUser", u);
	// logina trqq da e napravil tova
	User user = (User) session.getAttribute("loggedUser");
	user.refreshOrders();
	String pageNumberS = request.getParameter("page");
	int pageNumber = 1;
	if(pageNumberS!=null) {
		pageNumber = Integer.parseInt(pageNumberS);
	}
	pageNumber--;
	int orderNumber = 0;
	for(Order o : user.getOrdersArchive()) {
		if(orderNumber/6<pageNumber) {
			continue;
		} else if(orderNumber/6>pageNumber) {
			break;
		}
		orderNumber++;	
		out.println("<table class=\"cartMeal\" style=\"cursor: pointer;\" "); 
		out.println("onClick=\"(function(){");
		int mealNumber = 0;
		for(Meal m : o.getMeals()) {
			mealNumber++;
			out.println("$('#skrit"+orderNumber+mealNumber+"').fadeToggle('slow');");
		}
		out.println("})()\">");
		%>
		<tr>
        	<td class="cartCol1">
            <img src="images/restaurant_images/don_vito.gif" alt="" width="90" height="90" />
            </td>
            <td class="cartCol2" >
            <span class="mealName"> Ordered on:<%out.print(new SimpleDateFormat("dd.MM.yy HH:mm").format(o.getDate())); %> </span>
            <br />
           
            </td>
			
			<td class="cartCol2" >
            <span class="mealName"> Total price: <%out.print(new DecimalFormat("#.00").format(o.getPrice())); %> лв. </span>
            <br />
           
            </td>
            <td class="cartCol3">
            
            </td>
            
        </tr>     
    </table><%
		mealNumber=0;
    	for(Meal m : o.getMeals()) {
    		mealNumber++;
    		%>
    		 <table  id="<%out.print("skrit"+orderNumber+mealNumber); %>" class="cartMeal" style="display:none">
    	<tr>
        	<td class="cartCol1">
            <img src="images/meal_images/meat.jpg" alt="" width="90" height="90">
            </td>
            <td class="cartCol2">
            <div>
            <span class="mealName"><%out.print(m.getName());%> </span>
            <br>
            </div><div class="divIngredients">
            <span class="mealIngredients"> Съставки: <%out.print(m.ingradientsToString()); %> </span>
            </div> 
			
			
            </td>
			
            <td class="cartCol3">
			
             <div class="divPrice">
               <span>Цена:<%out.print(new DecimalFormat("#.00").format(m.getPrice())); %></span>
            	
            </div>
			
            </td>
            
        </tr>     
    </table>
    
    		<%
    		
    	}
    }
	%>
   
    
	<div class="pagesSwitcher">
 
		<a><b><%out.print(pageNumber+1);%>1</b></a>
		<a href="archive_page_2.html" ><b>2</b></a>
		<a href="archive_page_3.html" ><b>3</b></a>
		<a href="archive_page_4.html" ><b>4</b></a>
		<a href="archive_page_5.html" ><b>5</b></a>
		<% // TODO: dynamic pages  %>
	</div>
 
	</div>
	<div id="Footer"></div>

</div>
</body>
</html>