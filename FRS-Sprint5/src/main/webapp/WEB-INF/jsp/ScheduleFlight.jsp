<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Schedule a Flight</title>
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
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container">
		<div class="row">
			<div class="card col s8 offset-s2 center">
				<form:form action="addScheduleFlight" method="POST"
					modelAttribute="scheduleFlight">
					<div class="row">
						<h5>Add Flight</h5>
						<br>
						<div class="input-field col s12">
							<form:input id="schedule_flight_number" type="number"
								class="validate" path="scheduleFlightId"></form:input>
							<label for="schedule_flight_number">Flight Number</label>
						</div>
						<div class="input-field col s12">
							<input id="source_airport" type="text" class="validate"
								name="source_airport"></input> <label for="source_airport">Source
								Airport</label>
						</div>
						<div class="input-field col s12">
							<input id="destination_airport" type="text" class="validate"
								name="destination_airport"></input> <label
								for="destination_airport">Destination Airport</label>
						</div>
						<div class="input-field col s12">
							<input id="departure_time" type="datetime-local" class="validate"
								name="departure_time" ></input> <label for="departure_time">Departure
								Time</label>
						</div>
						<div class="input-field col s12">
							<input id="arrival_time" type="datetime-local" class="validate"
								name="arrival_time"></input> <label for="arrival_time">Arrival
								Time</label>
						</div>
						<div class="input-field col s12">
							<form:input id="ticket_cost" type="number" class="validate"
								path="ticketCost"></form:input>
							<label for="ticket_cost">Ticket Cost</label>
						</div>
						<br> <input type="submit" value="Submit"
							class="waves-effect waves-light btn-large"></input>
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
