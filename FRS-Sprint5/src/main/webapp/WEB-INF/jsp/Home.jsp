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
<style>
html, body {
	background-color: #eeeeee;
	height: 100%;
	margin: 0;
}

.wrapper {
	min-height: 100%;
	margin-bottom: -50px;
	background-image: url('homeimage.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

footer {
	height: 50px;
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
	<jsp:include page="OutHeader.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="wrapper">
		<div class="row">
			<div class="col s12 center">
				<h4>
					<span class="white-text"> Welcome to <br> Flight
						Reservation<br> System
					</span>
				</h4>
				<a href="login">
					<button type="button"
						class="waves-effect waves-light btn-large black">
						<span class="white-text">Log In</span>
					</button>
				</a> <br> <a href="signup">
					<button type="button"
						class="waves-effect waves-light btn-large black">
						<span class="white-text">Sign Up</span>
					</button>
				</a>
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