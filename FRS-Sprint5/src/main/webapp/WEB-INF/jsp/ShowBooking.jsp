<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="show" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<title>Bookings</title>
<style>
body {
	background-color: #eeeeee;
}

footer {
	width: 100%;
}

a {
	color: #212121;
}
</style>
</head>


<body>

	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->
	<div class="container">
		<show:forEach var="booking" items="${bookings}">
			<div class="row">
				<div class="col s6">
					Booking Id: ${booking.bookingId}<br> Booking Time:
					${booking.bookingDate }<br> Booking Cost:
					${booking.ticketCost}<br> Flight Number:
					${booking.getScheduleFlight().getScheduleFlightId()}<br>
					Flight
					Carrier:${booking.getScheduleFlight().getFlight().getCarrierName()}<br>
					Flight Model:
					${booking.getScheduleFlight().getFlight().getFlightModel()}<br>
					Source:
					${booking.getScheduleFlight().getSchedule().getSourceAirport().getAirportName()}<br>
					Destination:
					${booking.getScheduleFlight().getSchedule().getDestinationAirport().getAirportName()}<br>
					Departure:
					${booking.getScheduleFlight().getSchedule().getDepartureDateTime()}<br>
					Arrival:
					${booking.getScheduleFlight().getSchedule().getArrivalDateTime()}<br>
					Booking State:
					${booking.bookingState}
				</div>
				<div class="col s6">
					Passenger Details:<br>
					<show:forEach var="passenger" items="${booking.passengerList}">
						PNR: ${passenger.pnrNumber}<br>
						Passenger Name: ${passenger.passengerName}<br>
						Passenger Age: ${passenger.passengerAge}<br>
						Passenger UIN: ${passenger.passengerUIN}<br>
					</show:forEach>
					<br>
				</div>
			</div>
		</show:forEach>
	</div>



	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
</body>
</html>