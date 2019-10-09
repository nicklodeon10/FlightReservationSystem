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

select{
	display: block;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="row">
		<div class="col s6 offset-s3">
			<form:form action="find" method="POST">
				<div class="card">
					<div class="card-content">
						<div class="row">
							<div class="input-field col s12">
								Source Airport:</div>
								<select name="source_airport">
									<jstl:forEach items="${airportList}" var="airport">
										<option value="${airport.airportCode}">${airport.airportName} ,${airport.airportLocation}</option>
									</jstl:forEach>
								</select>
							
							<div class="input-field col s12">
								Destination Airport: 
								<select name="destination_airport">
									<jstl:forEach items="${airportList}" var="airport">
										<option value="${airport.airportCode}">${airport.airportName} ,${airport.airportLocation}</option>
									</jstl:forEach>
								</select>
							</div>
							<div class="input-field col s12">
								<input placeholder="Enter Date of Journey here"
									id="date_of_journey" type="date" class="validate"
									name="journeydate"> <label for="date_of_journey">Date
									of Journey</label>
							</div>
						</div>
					</div>
					<button type="submit" value="findFlights"
						class="waves-effect waves-light btn-large left">findFlights</button>
				</div>
			</form:form>
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