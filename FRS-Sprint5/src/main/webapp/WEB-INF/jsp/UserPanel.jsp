<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>User Panel</title>
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
	width: 100%;
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
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="row">
		<div class="col s8 offset-s2">
			<div class="row">
				<div class="col s4">
					<div class="card center">
						<div class="card-image waves-effect waves-block waves-light">
							<img class="activator"
								src="<c:url value="/resources/user.png" />">
						</div>
						<div class="card-content">
							<a href="addBooking"><span
								class="card-title activator grey-text text-darken-4">
									Create<br>Booking
							</span></a>
						</div>
					</div>
				</div>
				<div class="col s4">
					<div class="card center">
						<div class="card-image waves-effect waves-block waves-light">
							<img class="activator"
								src="<c:url value="/resources/user.png" />">
						</div>
						<div class="card-content">
							<a href="showBooking"><span
								class="card-title activator grey-text text-darken-4">
									View<br>Booking
							</span></a>
						</div>
					</div>
				</div>
				<div class="col s4">
					<div class="card center">
						<div class="card-image waves-effect waves-block waves-light">
							<img class="activator"
								src="<c:url value="/resources/user.png" />">
						</div>
						<div class="card-content">
							<a href="deleteBooking"><span
								class="card-title activator grey-text text-darken-4">
									Cancel<br>Booking
							</span></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		
	</script>
</body>

</html>