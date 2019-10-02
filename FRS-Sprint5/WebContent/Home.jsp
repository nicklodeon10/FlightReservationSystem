<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

.brand-logo {
	margin-left: 20px;
}

.card {
	margin-top: 15vh;
}

.homeImage {
	height: 100vh;
	background-image: url('homeimage.jpeg');
	background-size: cover;
}

.card-content {
	margin-bottom: -30px;
}

.center {
	margin-top: 25vh;
}

.btn-large {
	margin-top: 15px;
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
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="logIn">Log In</a></li>
				<li><a href="signUp">Sign Up</a></li>
			</ul>
		</div>
	</nav>
	<!-- Header -->

	<!-- Body -->
	<div class="row">
		<div class="col s6 offset-s1">
			<form:form action="findFlight" method="GET">
			<div class="card">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12">
							<input placeholder="Enter Source Airport here"
								id="source_airport" type="text" class="validate" name="source_airport"> <label
								for="source_airport">Source Airport</label>
						</div>
						<div class="input-field col s12">
							<input placeholder="Enter Destination Airport here"
								id="destination_airport" type="text" class="validate" name="destination_airport"> <label
								for="destination_airport" >Destination Airport</label>
						</div>
						<div class="input-field col s12">
							<input placeholder="Enter Date of Journey here"
								id="date_of_journey" type="date" class="validate" name="doj"> <label
								for="date_of_journey">Date of Journey</label>
						</div>
					</div>
				</div>
				<input type="submit" value="findFlights" class="waves-effect waves-light btn-large"></input>
				
			</div>
			</form:form>
		</div>
		<div class="col s4 center">
			<span class="or">OR</span><br>
			<form:form action="logIn" method="GET">
				<input type="submit" value="log In" class="waves-effect waves-light btn-large"></input><br>
			</form:form>
			<form:form action="signUp" method="GET">
				<input type="submit" value="sign up" class="waves-effect waves-light btn-large"></input><br>
			</form:form>
		</div>
	</div>
	<!-- Body -->

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
		
	</script>
</body>

</html>
