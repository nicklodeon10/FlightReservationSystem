<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Unschedule Flight</title>
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
body {
	background-color: #eeeeee;
}

footer {
	width: 100%;
}

a {
	color: #212121;
}

.brand-logo {
	margin-left: 20px;
}

.card {
	margin-top: 10vh;
}

.input-field {
	margin-top: -5px;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="AdminHeader.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container">
		<div class="jumbotron text-center">
			<h1>Search Your Flight</h1>
			<p>Enter your Flight Id</p>
		</div>

		<div class="row text center">
			<form:form action="/scheduledFlightRemoveSearch" method="GET">
				<div class="input-field col s6">
					<input id="scheduled_flight_id" type="number" class="validate"
						name="scheduled_flight_id"
						value="${scheduledFlight.scheduleFlightId}"> <label
						class="active">Flight Id:</label>
					<button class="btn waves-effect waves-teal open-button col s6"
						type="submit">
						Search <i class="fa fa-search"></i>
					</button>
				</div>
			</form:form>
		</div>
		<div class="row">
			<div class="card col s4 offset-s4 center">
				<form:form action="/scheduledFlightRemove" method="POST">
					<div class="row">
						<form:form action="scheduledFlightRemove" method="GET">
							<div class="row">
								<h5>Scheduled Flight Details:</h5>
								<br>
								<div class="input-field col s12">
									<input id="schedule_flight_number" type="number"
										name="scheduled_flight_id" class="validate"
										value="${scheduledFlight.scheduleFlightId}" readonly></input>
									<label for="schedule_flight_number">Flight Number</label>
								</div>
								<div class="input-field col s12">
									<input id="source_airport" type="text" class="validate"
										name="source_airport"
										value="${scheduledFlight.getSchedule().getSourceAirport().getAirportName()}"
										readonly></input> <label for="source_airport">Source
										Airport</label>
								</div>
								<div class="input-field col s12">
									<input id="destination_airport" type="text" class="validate"
										name="destination_airport"
										value="${scheduledFlight.getSchedule().getDestinationAirport().getAirportName()}"
										readonly></input> <label for="destination_airport">Destination
										Airport</label>
								</div>
								<div class="input-field col s12">
									<input id="departure_time" type="datetime-local"
										class="validate" name="departure_time"
										value="${scheduledFlight.getSchedule().getDepartureDateTime()}"
										readonly></input> <label for="departure_time">Departure
										Time</label>
								</div>
								<div class="input-field col s12">
									<input id="arrival_time" type="datetime-local" class="validate"
										name="arrival_time"
										value="${scheduledFlight.getSchedule().getArrivalDateTime()}"
										readonly></input> <label for="arrival_time">Arrival
										Time</label>
								</div>
								<div class="input-field col s12">
									<input id="ticket_cost" type="number" class="validate"
										value="${scheduledFlight.ticketCost}" readonly></input> <label
										for="ticket_cost">Ticket Cost</label>
								</div>
								<br> <input type="submit" value="Confirm"
									class="waves-effect waves-light btn-large"></input>
							</div>
						</form:form>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>
