<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<style>
body {
	background-color: #eeeeee;
}

footer {
	position: absolute;
	bottom: 0;
	width: 100%;
}

a {
	color: #212121;
}
</style>
</head>

<body>
	<!-- Header -->
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="home" class="brand-logo"> <i
				class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
		</div>
	</nav>
	<!-- Header -->

	<!-- Body -->

	<div class="container">
		<form:form action="newPassengerAdd" method="POST"
			modelAttribute="passenger">
			<div class="row" id="container">
				<div class="col s6 offset-s3 card">
					Passenger 1 Details:<br> <input type="text" hidden="true"
						name="schedule_flight_id" value="${flightId}" readonly />
					<form:input placeholder="Enter Passenger Name" id="passenger_name"
						type="text" class="validate" path="passengerName"></form:input>
					<label for="passenger_name">Enter Name of the Passenger:</label>
					<form:input placeholder="Enter UIN" id="passenger_uin"
						type="number" class="validate" path="passengerUIN"></form:input>
					<label for="passenger_uin">Enter UIN of the Passenger:</label>
					<form:input placeholder="Enter Passenger Age" id="passenger_age"
						type="text" class="validate" path="passengerAge"></form:input>
					<label for="passenger_age">Enter Age of the Passenger:</label>
				</div>
			</div>
			<input type="submit" value="Add Another Passenger"
				class="waves-effect waves-light btn-large"></input>
		</form:form>
		<a href="saveBooking">Confirm Booking</a>
	</div>

	<!-- Body -->

	<!-- Footer -->
	<footer class="page-footer grey lighten-3">
		<div class="container"></div>
		<div class="footer-copyright grey darken-4">
			<div class="container">
				© 2019 Flight Reservation System <a
					class="grey-text text-lighten-4 right" href="#!">About Us</a>
			</div>
		</div>
	</footer>
	<!-- Footer -->
</body>

</html>