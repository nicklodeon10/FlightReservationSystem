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
			<a href="home" class="brand-logo"> <i
				class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
		</div>
	</nav>
	<!-- Header -->



	<div class="row card">
		<div class="col s6 offset-s3">
			<div class="input-field col s12">
				<input placeholder="Enter UIN" id="passenger_uin" type="text"
					class="validate"> <label for="passenger_uin">Enter
					UIN of the Passenger:</label>
			</div>
			<div class="input-field col s12">
				<input placeholder="Enter Passenger Name" id="passenger_name"
					type="text" class="validate"> <label for="passenger_name">Enter
					Name of the Passenger:</label>
			</div>
			<div class="input-field col s12">
				<input placeholder="Enter Passenger Age" id="passenger_age"
					type="text" class="validate"> <label for="passenger_age">Enter
					Age of the Passenger:</label>
			</div>
			<div class="col s3 offset-s6">

				<button class="btn pmd-btn-fab pmd-ripple-effect btn-light"
					type="button" onclick="increasePassenger()">
					<i class="material-icons pmd-sm">add</i>
				</button>

			</div>
		</div>
	</div>



	<!-- Footer -->
	<footer class="page-footer grey lighten-3">
		<div class="container"></div>
		<div class="footer-copyright grey darken-4">
			<div class="container">
				© 2019 Flight Reservation System <a
					class="grey-text text-lighten-4 right" href="#!">About Us</a>
			</div>
		</div>
	</footer>
	<!-- Footer -->
	<script>
		var passengerCount=1;
		var data='<div class="row card">
		<div class="col s6 offset-s3">
		<div class="input-field col s12">
			<input placeholder="Enter UIN" id="passenger_uin" type="text"
				class="validate"> <label for="passenger_uin">Enter
				UIN of the Passenger:</label>
		</div>
		<div class="input-field col s12">
			<input placeholder="Enter Passenger Name" id="passenger_name"
				type="text" class="validate"> <label for="passenger_name">Enter
				Name of the Passenger:</label>
		</div>
		<div class="input-field col s12">
			<input placeholder="Enter Passenger Age" id="passenger_age"
				type="text" class="validate"> <label for="passenger_age">Enter
				Age of the Passenger:</label>
		</div>
		<div class="col s3 offset-s6">

			<button class="btn pmd-btn-fab pmd-ripple-effect btn-light"
				type="button" onclick="increasePassenger()">
				<i class="material-icons pmd-sm">add</i>
			</button>

		</div>
	</div>
		</div>';
		function increasePassenger(){
			passengerCount++;
			console.log(passengerCount);
			document.write(data);
		}
	</script>
</body>

</html>