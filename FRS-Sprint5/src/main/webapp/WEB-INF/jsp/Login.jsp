<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>FRS: Log In</title>
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
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="container">
		<div class="row">
			<div class="card col s4 offset-s4 center">
				<form action="login" method="POST">
					<%-- <div class="logoutMsg">
						<% if(param.logout){ %>You have been logged out <% } %>
					</div> --%>
					<div class="row">
						<h5>Log In</h5>
						<div class="input-field col s12">
							<input id="username" type="text" class="validate"
								name="username"> <label for="first_name">User
								Name</label>
						</div>
						<div class="input-field col s12">
							<input id="password" type="password" class="validate"
								name="password"> <label for="password">Password</label>
						</div>
					</div>
					<%-- <div class="loginError">
						<% if(param.error){ %>Invalid Username or Password <% } %>
					</div> --%>
					<input type="submit" value="Submit"
						class="waves-effect waves-light btn-large"></input>
				</form>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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