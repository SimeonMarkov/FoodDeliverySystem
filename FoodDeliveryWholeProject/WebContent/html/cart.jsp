<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/meals.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<title>FoodDelivery Cart</title>
<style type="text/css">
#quantity {
	width: 30px;
	height: 20px;
	text-align: center;
}

.inc {
	width: 30px;
	height: 20px;
	background-color: gold;
}

.dec {
	width: 30px;
	height: 20px;
	background-color: gold;
}

.button {
	-moz-user-select: none;
	-webkit-user-select: none;
	text-align: center;
	display: inline-block;
	cursor: pointer;
}

.quantityMenu {
	position: relative;
	width: 34px;
	height: 70px;
	margin-left: 65px;
}

/*td {
		display:block;
}
*/
.title {
	float: left;
	width: 150px;
	text-align: right;
	padding-right: 20px;
}
</style>
<script src="../JS/jquery-2.2.0.min.js"></script>
<script>

	$( document ).ready(function() {
		$(".inc").click(function() {
		    updateValue(this, 1);
		});
		$(".dec").click(function() {
		    updateValue(this, -1);
		});
	});

	function updateValue(obj, delta) {
	    var item = $(obj).parent().find("input");
	    var newValue = parseInt(item.val(), 10) + delta;
	    item.val(Math.max(newValue, 1));
	}
	
	$(document).ready(function() {
	    $('.closeButton').on('click', function(e) { 
	        removeMeal(this);
	    });
	});
	
	function removeMeal(obj){
	    $(obj).closest('table').remove();
	}
	
	
</script>
</head>

<body>

	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<ul>
				<li><a href="layout.html">Home</a></li>
				<li><a href="search.html">Search</a></li>
				<li><a href="cart.jsp">Cart [${sessionScope.loggedUser.getCartSize()}] </a></li>
				<li><a href="archive.html">Archive</a>
					<ul>
						<li><a href="archive.html">My archive</a></li>
						<li><a href="archiveTrending.html">Site's archive</a></li>
					</ul></li>

				<li><a href="profile.jsp">Profile</a></li>
				<li><a href="logout.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="Menu"></div>
		<div id="MainBody">



			<c:forEach var="item" begin="1" end="7">
				<table class="cartMeal">
					<tr>
						<td class="cartCol1"><img src="images/meal_images/meat.jpg"
							alt="" width="90" height="90"></td>
						<td class="cartCol2">
							<div>
								<span class="mealName"> Име на ястие ${item}</span>
								<input class="closeButton" type="button" style="position: relative; left:260px; background-color: gold;" value="x"/> <br>
							</div>
							<div class="divIngredients">
								<span class="mealIngredients"> Съставки: ala,bala,nica</span>
							</div>

						</td>
						<td class="cartCol3">
							<div class="quantityMenu">
								<div class="inc button" style="cursor: pointer;">+</div>
								<input id="quantity" name="quantity" type="text" value="1"></input><br />
								<div class="dec button" style="cursor: pointer;">-</div>
							</div>
							<div class="divPrice">
								<span>Цена: 12.56 </span>
							</div>
						</td>
					
					</tr>
				</table>
			</c:forEach>





			<div
				style="width: 600px; margin-left: 25px; margin-top: 5px; border: solid #8C8153; border-width: 1px; background-color: #C9C36D; text-align: center; padding-top: 5px; padding-bottom: 5px">
				<span>Обща стойност: 37.68</span>
			</div>
			<c:forEach var="addresses" begin="1" end="3">
			<div
				style="width: 600px; margin-left: 25px; margin-right: 25px; margin-top: 10px; border: solid #8C8153; border-width: 1px; background: #ECE08C; text-align: center;">
				<p>Test address value: ${addresses} <input type="radio" name="addressChoice"/></p>
				<p>Квартал: Борово</p>
				<p>Адрес: ул.Бай Благой 26, бл.56, вх.А, ет.5, ап.24</p>

			</div>
			</c:forEach>
			<input type="button" value="ПОТВЪРДИ ПОРЪЧКА" name="order"
				style="width: 602px; margin-left: 25px; margin-right: 25px; margin-top: 10px; border: solid #8C8153; border-width: 1px; padding-top: 10px; padding-bottom: 10px; background-color: #C16845; color: #F0EFDF; cursor: pointer;">

		</div>
		<div id="Footer"></div>

	</div>


</body>
</html>