<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="ISO-8859-1">
<head>
<title>Modify Scheduled Flight</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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
	<div class="container center">
		<div class="jumbotron text-center">
			<h1>Modify a Scheduled Flight</h1>
			<p>Enter Flight Id</p>
		</div>

		<div class="row text center">
			<form:form action="modifyScheduledFlightSearch" method="GET">
				<div class="input-field col s6">
					<input id="id" type="number" class="validate"
						name="modify_schedule_flight_id"> <label class="active">Flight
						Id</label>
					<button class="btn waves-effect waves-teal open-button col s6"
						type="submit">
						Search <i class="fa fa-search"></i>
					</button>
				</div>
			</form:form>
		</div>
		<ul class="collection with-header">
			<li class="collection-header"><h4>Modify Scheduled Flight</h4></li>
			<li class="collection-item">
				<div class="row">
					<div class="col s8 offset-s2 center">
						<form:form action="scheduledFlightModify" method="POST"
							modelAttribute="scheduleFlight">
							<div class="row">
								<br>
								<div class="input-field col s12">
									<form:input id="schedule_flight_number" type="number"
										class="validate" path="scheduleFlightId"
										value="${scheduledFlightData.scheduleFlightId}"
										readonly="true"></form:input>
									<label for="schedule_flight_number">Flight Number</label>
								</div>
								<div class="input-field col s12">
									<input id="source_airport" type="text" class="validate"
										name="source_airport"
										value="${scheduledFlightData.getSchedule().getSourceAirport().getAirportCode()}"></input>
									<label for="source_airport">Source Airport</label>
								</div>
								<div class="input-field col s12">
									<input id="destination_airport" type="text" class="validate"
										name="destination_airport"
										value="${scheduledFlightData.getSchedule().getDestinationAirport().getAirportCode()}"></input>
									<label for="destination_airport">Destination Airport</label>
								</div>
								<div class="input-field col s12">
									<input id="departure_time" type="datetime-local"
										class="validate" name="departure_time"
										value="${scheduledFlightData.getSchedule().getDepartureDateTime()}"></input>
									<label for="departure_time">Departure Time</label>
								</div>
								<div class="input-field col s12">
									<input id="arrival_time" type="datetime-local" class="validate"
										name="arrival_time"
										value="${scheduledFlightData.getSchedule().getArrivalDateTime()}"></input>
									<label for="arrival_time">Arrival Time</label>
								</div>
								<div class="input-field col s12">
									<form:input id="ticket_cost" type="number" class="validate"
										path="ticketCost" value="${scheduledFlightData.ticketCost}"></form:input>
									<label for="ticket_cost">Ticket Cost</label>
								</div>
								<br> <input type="submit" value="Submit"
									class="waves-effect waves-light btn-large"></input>
							</div>
						</form:form>
					</div>
				</div>
			</li>
		</ul>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
</body>
</html>