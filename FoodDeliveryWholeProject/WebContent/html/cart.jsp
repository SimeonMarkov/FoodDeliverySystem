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
	$(document).ready(function() {
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

	function removeMeal(obj) {
		$(obj).closest('table').remove();
	}
</script>
<script>
	(function() {
		"use strict";

		var $products = $("#products"), $total = $("#total");

		function updateTotal(addVal) {
			var current = parseFloat($total.html());
			$total.html(current + addVal);
		}

		function buttonEvent(btnClass, sign) {
			$products
					.on(
							"click",
							btnClass,
							function() {
								var $this = $(this), $num = $this
										.siblings(".num"), num = parseInt($num
										.html()), newNum = num + 1 * sign, initPrice, $price, price;

								if (newNum <= 0) {
									return;
								}

								$price = $this.siblings(".price");
								price = parseFloat($price.html());
								initPrice = $price.data("initial");

								$num.html(newNum);
								$price.html(price + (initPrice * sign));
								updateTotal(initPrice * sign);
							});
		}

		function bindEvents() {
			buttonEvent(".plus", 1);
			buttonEvent(".minus", -1);
		}

		function renderProducts(products) {
			var total = 0;

			products.forEach(function(prod) {
				var $product = $("<div>").addClass("product").data("name",
						prod.name), $name = $("<div>").addClass("name").html(
						prod.name), $price = $("<div>").addClass("price").data(
						"initial", prod.price).html(prod.price), $num = $(
						"<div>").addClass("num").html(prod.num), $plusBtn = $(
						"<button>").addClass("plus").html("+"), $minusBtn = $(
						"<button>").addClass("minus").html("-");

				$product.append($name, $price, $num, $plusBtn, $minusBtn);
				$products.append($product);

				total += prod.price;
			});

			$total.html(total);
		}

		function getProducts() {
			// Simulating AJAX request
			setTimeout(function() {
				var products = [ {
					name : "musaka",
					price : 15,
					num : 1
				}, {
					name : "pacha",
					price : 11.5,
					num : 1
				}, {
					name : "pizza",
					price : 5.5,
					num : 1
				} ];

				renderProducts(products);
			}, 0);
		}

		bindEvents(); // Binding events before rendering
		getProducts(); // Renderering the products
	}());
</script>
</head>

<body>

	<div id="Container">
		<div id="Header"></div>
		<div id="Buttons">
			<ul>
				<li><a href="layout.html">Home</a></li>
				<li><a href="search.html">Search</a></li>
				<li><a href="cart.jsp">Cart
						[${sessionScope.loggedUser.getCartSize()}] </a></li>
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


			<% //TODO:For upload %>
			<c:forEach var="meal" items="${sessionScope.loggedUser.getBasket()}">
				<table class="cartMeal">
					<tr>
						<td class="cartCol1"><img src="images/meal_images/meat.jpg"
							alt="" width="90" height="90"></td>
						<td class="cartCol2">
							<div>
								<span class="mealName"> ${meal.getName()}</span> <input
									class="closeButton" type="button"
									style="position: relative; left: 260px; background-color: gold;"
									value="x" /> <br>
							</div>
							<div class="divIngredients">
								<span class="mealIngredients"> Съставки: ${meal.getIngredients()}</span>
							</div>

						</td>
						<td class="cartCol3">
							<div class="quantityMenu">
								<div class="inc button" style="cursor: pointer;">+</div>
								<input id="quantity" name="quantity" type="text" value="1"></input><br />
								<div class="dec button" style="cursor: pointer;">-</div>
							</div>
							<div class="divPrice">
								<span>Цена: ${meal.getPrice()} </span>
							</div>
						</td>

					</tr>
				</table>
			</c:forEach>





			<div
				style="width: 600px; margin-left: 25px; margin-top: 5px; border: solid #8C8153; border-width: 1px; background-color: #C9C36D; text-align: center; padding-top: 5px; padding-bottom: 5px">
				<span>Обща стойност: ${session.loggedUser.getTotalPriceOfCart()}</span>
			</div>
			<c:forEach var="address" items="${sessionScope.currentUseAddress}">
				<div style="margin-top: 5px; height: 180px; margin-left: 20px; cursor: pointer;">
					<input style="margin-left: 60px;" type="radio" name="chosenAddress"
						value="${address.getNeighbourhood()}" /><br> 
						<label
						for="kvartal1" class="kvartal">Квартал</label>
						 <input type=text name="kvartal1" value="${address.getNeighbourhood()}" readonly /><br>
					<br> <label for="address1" class="kvartal">Адрес</label>

					<textarea class="addressField" name="address1" rows="5" cols="60"
						readonly>${address.getFullAddress()}</textarea>
					<br>
				</div>
				<hr style="margin-left: 25px; margin-right: 25px;">
			</c:forEach>
			<input type="button" value="ПОТВЪРДИ ПОРЪЧКА" name="order"
				style="width: 602px; margin-left: 25px; margin-right: 25px; margin-top: 10px; border: solid #8C8153; border-width: 1px; padding-top: 10px; padding-bottom: 10px; background-color: #C16845; color: #F0EFDF; cursor: pointer;">

		</div>
		<div id="Footer"></div>

		

	</div>


</body>
</html>