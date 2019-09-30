<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	<style>
body {
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
</style>
</head>

<body>
	<!-- Header -->
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="home" class="brand-logo"> <i class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
			
		</div>
	</nav>
	<!-- Header -->
	
	
	<div class="row">
		<div class="col s6 offset-s3">
			<div class="card">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12">
							<input placeholder="Enter Source Airport here"
								id="source_airport" type="text" class="validate"> <label
								for="source_airport">Source Airport</label>
						</div>
						<div class="input-field col s12">
							<input placeholder="Enter Destination Airport here"
								id="destination_airport" type="text" class="validate"> <label
								for="destination_airport">Destination Airport</label>
						</div>
						<div class="input-field col s12">
							<input placeholder="Enter Date of Journey here"
								id="date_of_journey" type="date" class="validate"> <label
								for="date_of_journey">Date of Journey</label>
						</div>
						
						<div class="input-field col s12">
							<input placeholder="Enter Number Of Passengers"
								id="number_of_passengers" type="number" class="validate"> <label
								for="date_of_journey">Number Of Passengers</label>
						</div>
						
					</div>
				</div>
				<div class="card-action grey darken-4">
					<a href="#"> <strong>Book Flight</strong>
					</a>
				</div>
			</div>
		</div>
	
	
	
	
	
	
	
	
	
	
	<!-- Footer -->
	<footer class="page-footer grey lighten-3">
		<div class="container"></div>
		<div class="footer-copyright grey darken-4">
			<div class="container">
				� 2019 Flight Reservation System <a
					class="grey-text text-lighten-4 right" href="#!">About Us</a>
			</div>
		</div>
	</footer>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>