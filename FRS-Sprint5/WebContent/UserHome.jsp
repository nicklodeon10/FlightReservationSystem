<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>UserHome</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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
	<center>
	<h5>What Do You Want To Do?</h5>
	</center>
	
	<div class="row" align="center" padding-left=100px>
    <div class="col s12 m6 offset-s3" align="center" padding-left=100px>
      <div class="card blue-grey darken-1" align="center">
                <div class="card-action" align="center">
          <ul class="collection with-header waves-effect waves-block waves-light" position="center">
        
        <li class="collection-item"><div align="center">Book A Flight<a href="book_flight" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div align="center">View My Booking<a href="view_flight" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div align="center">Cancel Booking<a href="cancel_flight" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        
      </ul>
        </div>
      </div>
    </div>
  </div>
	
	 
	
		<!--<div class="col s6 offset-s3">
			<div class="card center">
				<div class="card-image waves-effect waves-block waves-light">
					<img class="activator" src="flight.png">
				</div>
				
				
					<p>
						<a href="addFlight">Book A Flight</a><br>
						<a href="showFlights">View My Booking</a><br>
						<a href="searchFlight">Cancel Booking</a><br>
						
					</p>
				</div>
			</div>
		</div>

	-->
	
	
	
	
	
	
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