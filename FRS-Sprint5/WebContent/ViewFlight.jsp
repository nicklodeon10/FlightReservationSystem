<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="show" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: View Flights</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<style type="text/css">
<
style>body {
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

.collection {
	margin-top: 100px;
}
</style>
</head>

<body>
	<!-- Header -->
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="home" class="brand-logo"> <i class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#">Log In</a></li>
				<li><a href="#">Sign Up</a></li>
			</ul>
		</div>
	</nav>
	<!-- Header -->

	<!-- Body -->
	<div class="container center">
		<ul class="collection with-header">
			<li class="collection-header"><h4>Available Flights</h4></li>
			<show:forEach var="scheduleFlight" items="${scheduleFlightList}">
			<li class="collection-item">
				<div class="row">
					<div class="col s5">
						<div class="carrierName">Carrier Name: ${scheduleFlight.getFlight().getCarrierName()}</div>
						<div class="flightModel">FlightModel: ${scheduleFlight.getFlight().getFlightModel()}</div>
						<div class="flightNumber">Flight Number: ${scheduleFlight.getFlight().getFlightNumber()}</div>
						<div class="availableSeats">Available Seats: ${scheduleFlight.getAvailableSeats()}</div>
					</div>
					<div class="col s5">
						<div class="sourceAirport">Source: ${scheduleFlight.getSchedule().getSourceAirport().getAirportName()}</div>
						<div class="destinationAirport">Destination: ${scheduleFlight.getSchedule().getDestinationAirport().getAirportName()}</div>
						<div class="departureTime">Departure: ${scheduleFlight.getSchedule().getDepartureDateTime().toString()}</div>
						<div class="arrivalTime">Arrival: ${scheduleFlight.getSchedule().getArrivalDateTime().toString()}</div>
					</div>
					<div class="col s2">
						<a href="#"> <i class="small material-icons">airplanemode_active</i>
						</a>
					</div>
				</div>
			</li>
			</show:forEach>
		</ul>
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
	<script>
		
	</script>
</body>

</html>
