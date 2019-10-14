<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Admin Panel</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<style>
html, body {
	background-color: #eeeeee;
	height: 100%;
	margin: 0;
}

.wrapper {
	min-height: 100%;
	margin-bottom: -50px;
}

footer {
	height: 50px;
}

a {
	color: #212121;
}

.brand-logo {
	margin-left: 20px;
}

.card {
	margin-top: 25vh;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="AdminHeader.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="wrapper">
		<div class="row">
			<div class="col s8 offset-s2">
				<div class="row">
					<div class="col s4">
						<div class="card center">
							<div class="card-image waves-effect waves-block waves-light">
								<img class="activator" src="user.png">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4">User<br>Management
								</span>
							</div>
							<div class="card-reveal">
								<span class="card-title grey-text text-darken-4">User<br>Management<br>
									<i class="material-icons small">arrow_drop_down</i>
								</span>
								<p>
									<a href="/admin/viewallusers">View Registered Users</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col s4">
						<div class="card center">
							<div class="card-image waves-effect waves-block waves-light">
								<img class="activator" src="user.png">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4">Flight<br>Management
								</span>
							</div>
							<div class="card-reveal">
								<span class="card-title grey-text text-darken-4">Flight<br>Management<br>
									<i class="material-icons small">arrow_drop_down</i>
								</span>
								<p>
									<a href="/flight/add">Add a Flight</a><br> <a
										href="/flight/view">Show all Flights</a><br> <a
										href="/flight/search">Search a Flight</a><br> <a
										href="/flight/modify">Modify an Existing Flight</a><br> <a
										href="/flight/remove">Remove a Flight</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col s4">
						<div class="card center">
							<div class="card-image waves-effect waves-block waves-light">
								<img class="activator" src="user.png">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4">Schedule<br>Management
								</span>
							</div>
							<div class="card-reveal">
								<span class="card-title grey-text text-darken-4">Schedule<br>Management<br>
									<i class="material-icons small">arrow_drop_down</i>
								</span>
								<p>
									<a href="/getScheduleFlightPage">Schedule a Flight</a><br> <a
										href="/showScheduledFlights">Show all Scheduled Flights</a><br>
									<a href="/searchScheduledFlights">Search a Flight Schedule</a><br>
									<a href="/modifyScheduledFlight">Modify a Flight Schedule</a><br>
									<a href="/removeScheduledFlight">Unschedule a Flight</a>
								</p>
							</div>
						</div>
					</div>
				</div>
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