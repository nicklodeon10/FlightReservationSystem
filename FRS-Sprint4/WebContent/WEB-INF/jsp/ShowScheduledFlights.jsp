<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="show" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: View Scheduled Flights</title>
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
			<li class="collection-header"><h4>Scheduled Flights</h4></li>
			<show:forEach var="scheduledFlight" items="${scheduledFlightList}">
				<li class="collection-item">
					<div class="row">
						Flight Id: ${scheduledFlight.scheduleFlightId} <br> Ticket
						Cost: ${scheduledFlight.ticketCost} <br> Source Airport:
						${scheduledFlight.getSchedule().getSourceAirport().getAirportName()}
						<br> Destination Airport:
						${scheduledFlight.getSchedule().getDestinationAirport().getAirportName()}
						<br> Departure Time:
						${scheduledFlight.getSchedule().getArrivalDateTime()} <br>
						Arrival Time:
						${scheduledFlight.getSchedule().getDepartureDateTime()} <br>
						Carrier: ${scheduledFlight.getFlight().getCarrierName()} <br>
						Model: ${scheduledFlight.getFlight().getFlightModel() } <br>
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
