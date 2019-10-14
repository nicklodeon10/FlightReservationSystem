<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Add Flight</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
	<jsp:include page="AdminHeader.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container center">
		<div class="jumbotron text-center">
			<h1>Search Your Flight</h1>
			<p>Enter your Flight Id</p>
		</div>

		<div class="row text center">
			<form:form action="/flight/modify/search" method="GET">
				<div class="input-field col s6">
					<input id="id" type="number" class="validate" name="flight_id" required>
					<label class="active">Flight Id:</label>
					<button class="btn waves-effect waves-teal open-button col s6"
						type="submit">
						Search <i class="fa fa-search"></i>
					</button>
				</div>
			</form:form>
		</div>
		<div class="row">
			<div class="card col s4 offset-s4 center">
				<form:form action="/flight/modified" method="POST"
					modelAttribute="flight">
					<div class="row">
						<h5>Update Flight</h5>
						<br>
						<div class="input-field col s12">
							<form:input id="flight_id" type="number" class="validate"
								path="flightNumber" value="${flight.flightNumber}"
								readonly="true"></form:input>
							<label for="flight_id">Flight Id</label>
						</div>
						<div class="input-field col s12">
							<form:input id="flight_model" type="text" class="validate"
								path="flightModel" value="${flight.flightModel}"></form:input>
							<label for="flight_model">Flight Model</label>
							<span id="model_error" style="color:red"></span>
                            <span style="color:red">${error}</span>
						</div>
						<div class="input-field col s12">
							<form:input id="carrier_name" type="text" class="validate"
								path="carrierName" value="${flight.carrierName}"></form:input>
							<label for="carrier_name">Carrier Name</label>
							<span id="carrier_error" style="color:red"></span>
							<span style="color:red">${error}</span>
						</div>
						<div class="input-field col s12">
							<form:input id="seat_capacity" type="number" class="validate"
								path="seatCapacity" value="${flight.seatCapacity}"></form:input>
							<label for="seat_capacity">Seat Count</label>
							<span id="capacity_error" style="color:red"></span>
							<span style="color:red">${error}</span>
						</div>
						<br> <input type="submit" value="Submit"
							class="waves-effect waves-light btn-large"></input>
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
	</script>
	
	<script src='<c:url value = "/webjars/lib/jquery/jquery.min.js"/>'></script>
	<script
		src='<c:url value = "/webjars/lib/jquery/jquery-migrate.min.js"/>'></script>
	<script
		src='<c:url value = "/webjars/lib/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
	<script src='<c:url value = "/webjars/lib/easing/easing.min.js"/>'></script>
	<script src='<c:url value = "/webjars/lib/wow/wow.min.js"/>'></script>
	<script
		src='<c:url value = "/webjars/lib/waypoints/waypoints.min.js"/>'></script>
	<script
		src='<c:url value = "/webjars/lib/counterup/counterup.min.js"/>'></script>
	<script src='<c:url value = "/webjars/lib/superfish/hoverIntent.js"/>'></script>
	<script
		src='<c:url value = "/webjars/lib/superfish/superfish.min.js"/>'></script>

	<!-- Contact Form JavaScript File -->
	<script src='<c:url value = "/webjars/contactform/contactform.js" />'></script>

	<!-- Template Main Javascript File -->
	<script src='<c:url value = "/webjars/js/main.js" />'></script>
	
	<script type="text/javascript">
	
	 $(function () {
		 
		 $("#model_error").hide();
		 $("#carrier_error").hide();
		 $("#capacity_error").hide();
		 
		 
		 var error_model = false;
		 var carrier_model = false;
		 var capacity_model = false;
		 
		 $("#flight_model").focusout(function () {
				check_model();
			}
		 	 
		 );
		 
		 $("#carrier_name").focusout(function () {
				check_carrier();
			}
		 	 
		 );
	 
		 $("#seat_capacity").focusout(function () {
				check_capacity();
			}
		 	 
		 );
	
	
		 
		 function check_model() {
				var length = $("#flight_model").val().length;
				var pattern = new RegExp("^[A-Z0-9]+$");
				if(length<1 || !pattern.test($("#flight_model").val())){
					$("#model_error").html("Please enter Alphanumeric with Capital Letters!");
					$("#model_error").show();
					error_model = true;
				}
				else{
					$("#model_error").hide();
				}
			
		 
	 }
		 
		 

		 function check_carrier() {
				var length = $("#carrier_name").val().length;
				var pattern = new RegExp("^[A-Z]+$");
				if(length<1 || !pattern.test($("#carrier_name").val())){
					$("#carrier_error").html("Please enter  Capital Letters!");
					$("#carrier_error").show();
					error_carrier = true;
				}
				else{
					$("#carrier_error").hide();
				}
			
		 
	 }
		 
		 function check_capacity() {
				var length = $("#seat_capacity").val().length;
				
				if(length<1 || length>1000){
					$("#capacity_error").html("Please enter appropriate seat capacity!");
					$("#capacity_error").show();
					error_carrier = true;
				}
				else{
					$("#carrier_error").hide();
				}
			
		 
	 }
		 
		 
		 
		 
		 $("#form").submit(function(){
			 error_model = false;
			 error_carrier = false;
				
				check_model();
				check_carrier();
				if(error_model == false && error_carrier == false && error_capacity == false){
					return true;
				}
				else{
					return false;
				}
			});
		});
	 
	
	</script> 
	
</body>

</html>
