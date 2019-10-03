<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Remove Flight</title>
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
		<div class="jumbotron text-center">
			<h1>Remove a Flight</h1>
			<p>Enter Flight Id</p>
		</div>

		<div class="row text center">
			<form:form action="flightRemoveSearch" method="GET">
				<div class="input-field col s6">
					<input id="id" type="number" class="validate" name="flight_id"
						value="${flight.flightNumber}"> <label class="active">Flight
						Id</label>
					<button class="btn waves-effect waves-teal open-button col s6"
						type="submit">
						Search <i class="fa fa-search"></i>
					</button>
				</div>
			</form:form>
		</div>
		<div class="row">
			<div class="card col s4 offset-s4 center">
				<form:form action="flightRemove" method="POST">
					<div class="row">
						<ul class="collection with-header">
							<li class="collection-item">
								<div class="row">
									<h5>Flight Details</h5>
									<br>
									<div class="input-field col s12">
										<input name="flight_id" id="flight_number" type="number"
											class="validate" value="${flight.flightNumber}" readonly></input>
										<label for="flight_number">Flight Number</label>
									</div>
									<div class="input-field col s12">
										<input id="flight_model" type="text" class="validate"
											value="${flight.flightModel}" readonly></input> <label
											for="flight_model">Flight Model</label>
									</div>
									<div class="input-field col s12">
										<input id="carrier_name" type="text" class="validate"
											value="${flight.carrierName}" readonly></input> <label
											for="carrier_name">Carrier Name</label>
									</div>
									<div class="input-field col s12">
										<input id="seat_capacity" type="number" class="validate"
											value="${flight.seatCapacity}" readonly></input> <label
											for="seat_capacity">Seat Count</label>
									</div>
								</div>
							</li>
						</ul>
						<br> <input type="submit" value="Confirm"
							class="waves-effect waves-light btn-large"></input>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>
