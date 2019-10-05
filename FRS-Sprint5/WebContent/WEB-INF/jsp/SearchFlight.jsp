<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="ISO-8859-1">
<head>
<title>Search Flight</title>
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
			<h1>Search a Flight</h1>
			<p>Enter Flight Id</p>
		</div>

		<div class="row text center">
			<form:form action="flightSearch" method="GET">
				<div class="input-field col s6">
					<input id="id" type="number" class="validate" name="flight_id">
					<label class="active">Flight Id:</label>
					<button class="btn waves-effect waves-teal open-button col s6"
						type="submit">
						Search <i class="fa fa-search"></i>
					</button>
				</div>
			</form:form>
		</div>
		<ul class="collection with-header">
			<li class="collection-header"><h4>Available Flights</h4></li>
			<li class="collection-item">
				<div class="row">
					Flight Id: ${flight.flightNumber} <br> Flight Model:
					${flight.flightModel } <br> Carrier Name: ${flight.carrierName }
					<br> Seat Capacity: ${flight.seatCapacity }
				</div>
			</li>
		</ul>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
</body>
</html>
