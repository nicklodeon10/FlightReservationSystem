<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style>
.brand-logo {margin-left =20px;
	
}
</style>
</head>
<body>
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="backHome" class="brand-logo"> <i
				class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="home">Home</a></li>
				<form:form action="logout" method="POST">
					<button type="submit">Logout</button>
				</form:form>
			</ul>
		</div>
	</nav>
</body>
</html>