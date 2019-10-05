<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

.popup {
	position: relative;
	display: inline-block;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* The actual popup */
.popup .popuptext {
	visibility: hidden;
	width: 250px;
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 8px 0;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
}

/* Toggle this class - hide and show the popup */
.popup .show {
	visibility: visible;
	-webkit-animation: fadeIn 1s;
	animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
@
-webkit-keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
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
									id="source_airport" type="text" class="validate"
									name="source_airport"> <label for="source_airport">Source
									Airport</label>
							</div>
							<div class="input-field col s12">
								<input placeholder="Enter Destination Airport here"
									id="destination_airport" type="text" class="validate"
									name="destination_airport"> <label
									for="destination_airport">Destination Airport</label>
							</div>
							<div class="input-field col s12">
								<input placeholder="Enter Date of Journey here"
									id="date_of_journey" type="date" class="validate" name="doj">
								<label for="date_of_journey">Date of Journey</label>
							</div>
						</div>
					</div>
					<input type="submit" value="findFlights"
						class="waves-effect waves-light btn-large"></input>
					<div class="popup right red" onclick="myFunction()">
						View Airport Codes <span class="popuptext" id="myPopup"> Airport
							Codes<br> Code:BLR, Name:Kempegowda International Airport,
							Location: Bengaluru<br> Code:DEL, Name:Indira Gandhi
							International Airport, Location: Delhi<br> Code:HYD,
							Name:Rajiv Gandhi International Airport, Location: Hyderabad<br>
							Code:IXC, Name:Chandigarh International Airport , Location:
							Chandigarh<br> Code:MAA, Name:Chennai International Airport
							, Location: Chennai<br> Code:MUM, Name:Chhatrapati Shivaji
							International Airport, Location: Mumbai<br> Code:SXR,
							Name:Srinagar International Airport, Location: Srinagar<br>
						</span>
					</div>
				</div>
			</form:form>
		</div>
		<div class="col s4 center">
			<span class="or">OR</span><br>
			<form:form action="logIn" method="GET">
				<input type="submit" value="log In"
					class="waves-effect waves-light btn-large"></input>
				<br>
			</form:form>
			<form:form action="signUp" method="GET">
				<input type="submit" value="sign up"
					class="waves-effect waves-light btn-large"></input>
				<br>
			</form:form>
		</div>
	</div>
	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		function myFunction() {
			var popup = document.getElementById("myPopup");
			popup.classList.toggle("show");
		}
	</script>
</body>

</html>
