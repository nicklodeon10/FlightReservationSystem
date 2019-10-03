<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="show" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container center">
		<ul class="collection with-header">
			<li class="collection-header"><h4>Available Flights</h4></li>
			<show:forEach var="scheduleFlight" items="${scheduleFlightList}">
				<li class="collection-item">
					<div class="row">
						<div class="col s5">
							<div class="carrierName">Carrier Name:
								${scheduleFlight.getFlight().getCarrierName()}</div>
							<div class="flightModel">FlightModel:
								${scheduleFlight.getFlight().getFlightModel()}</div>
							<div class="flightNumber">Flight Number:
								${scheduleFlight.getFlight().getFlightNumber()}</div>
							<div class="availableSeats">Available Seats:
								${scheduleFlight.getAvailableSeats()}</div>
						</div>
						<div class="col s5">
							<div class="sourceAirport">Source:
								${scheduleFlight.getSchedule().getSourceAirport().getAirportName()}</div>
							<div class="destinationAirport">Destination:
								${scheduleFlight.getSchedule().getDestinationAirport().getAirportName()}</div>
							<div class="departureTime">Departure:
								${scheduleFlight.getSchedule().getDepartureDateTime().toString()}</div>
							<div class="arrivalTime">Arrival:
								${scheduleFlight.getSchedule().getArrivalDateTime().toString()}</div>
						</div>
						<div class="col s2">
							<form:form action="addPassenger" method="GET">
								<input type="text" hidden="true" name="schedule_flight_id"
									value="${scheduleFlight.getFlight().getFlightNumber()}" />
								<input type="submit" value="Book"
									class="waves-effect waves-light btn-small green"></input>
							</form:form>
						</div>
					</div>
				</li>
			</show:forEach>
		</ul>
	</div>
	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>
