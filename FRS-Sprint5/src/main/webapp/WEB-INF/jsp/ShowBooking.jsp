<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
html, body {
	background-color: #eeeeee;
	height: 100%;
	margin: 0;
}

.wrapper {
	min-height: 100%;
	margin-bottom: -50px;
}

footer {
	height: 50px;
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
	<div class="wrapper">
		<div class="container" style="margin-top: 25px;">
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
					</div>
					<div class="col s4">
						Passenger Details:<br>
						<show:forEach var="passenger" items="${booking.passengerList}">
						PNR: ${passenger.pnrNumber}<br>
						Passenger Name: ${passenger.passengerName}<br>
						Passenger Age: ${passenger.passengerAge}<br>
						Passenger UIN: ${passenger.passengerUIN}<br>
						</show:forEach>
						<br>
					</div>
					<div class="col s2">
						<div class="row">
							<div class="col s12">
								<show:if test="${!booking.bookingState}">
									<button class="waves-effect waves-light btn-large red">Cancelled</button>
								</show:if>
							</div>
						</div>
						<div class="row">
							<div class="col s12">
								<form:form action="/booking/download" method="GET">
									<input type="text" hidden="true" name="booking_id"
										value="${booking.bookingId}" />
									<button type="submit"
										class="waves-effect waves-light btn-large green">Download
										eTicket</button>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<hr>
			</show:forEach>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
</body>
</html>