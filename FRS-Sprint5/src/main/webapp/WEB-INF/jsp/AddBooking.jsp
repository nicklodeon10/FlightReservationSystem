<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Find Flights</title>

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

select {
	display: inline-block;
}

.label {
	display: inline-block;
}

button {
	margin: 20px;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="UserHeader.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="wrapper">
		<div class="row">
			<div class="col s6 offset-s3">
				<form:form action="/booking/find" method="POST">
					<div class="card">
						<div class="card-content">
							<div class="row">
								<div class="input-field col s12">
									<div class="label">Source Airport</div>
									<select name="source_airport" onblur="disableChoice()" id="src">
										<option selected disabled>Select Source Airport</option>
										<jstl:forEach items="${airportList}" var="airport">
											<option value="${airport.airportCode}">${airport.airportName}
												,${airport.airportLocation}</option>
										</jstl:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<div class="label">Destination Airport</div>
									<select name="destination_airport">
										<option selected disabled>Select Destination Airport</option>
										<jstl:forEach items="${airportList}" var="airport">
											<option value="${airport.airportCode}"
												id="dest${airport.airportCode}">${airport.airportName}
												,${airport.airportLocation}</option>
										</jstl:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<div class="label">Date of Journey</div>
									<input placeholder="Enter Date of Journey here"
										id="date_of_journey" type="date" class="validate"
										name="journeydate">
								</div>
							</div>
							<div class="row">
								<button type="submit" value="Find Flights"
									class="waves-effect waves-light btn-large left">Find
									Flights</button>
							</div>
						</div>
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
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if (dd < 10)
			dd = '0' + dd
		if (mm < 10)
			mm = '0' + mm
		min = yyyy + '-' + mm + '-' + dd;
		document.getElementById("date_of_journey").setAttribute("min", min);
		today.setMonth(today.getMonth() + 2);
		dd = today.getDate();
		mm = today.getMonth();
		yyyy = today.getFullYear();
		if (dd < 10)
			dd = '0' + dd
		if (mm < 10)
			mm = '0' + mm
		max = yyyy + '-' + mm + '-' + dd;
		document.getElementById("date_of_journey").setAttribute("max", max);

		var prev = [];
		function disableChoice() {
			for (var i = 0; i < prev.length; i++) {
				prev[i].disabled = false;
			}
			var element = document.getElementById('dest'
					+ document.getElementById('src').value);
			element.disabled = true;
			prev.push(element);
		}
	</script>
</body>

</html>