<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="show" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: View Flights</title>
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
<
style>body {
	background-color: #eeeeee;
}

footer {
	width: 100%;
}

a {
	color: #212121;
}

.collection {
	margin-top: 100px;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container center">
		<ul class="collection with-header">
			<li class="collection-header"><h4>Available Flights</h4></li>
			<show:forEach var="flight" items="${flightList}">
				<li class="collection-item">
					<div class="row">
						Flight Id: ${flight.flightId} <br> Flight Model:
						${flight.flightModel } <br> Carrier Name:
						${flight.carrierName } <br> Seat Capacity:
						${flight.seatCapacity }
					</div>
				</li>
			</show:forEach>
		</ul>
	</div>
	<!-- Body -->

<button onclick="goBack()" class="waves-effect waves-light btn-large">Go Back</button>
<script>
function goBack() {
  window.history.back();
}
</script>

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>
