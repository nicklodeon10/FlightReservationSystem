<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style>
.brand-logo {
	margin-left: 20px;
}

.linkbutton {
	background: none !important;
	border: none;
	padding: 0 !important;
	/*optional*/
	color: white;
	cursor: pointer;
}
</style>
</head>
<body>
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="" class="brand-logo"> <i class="large material-icons">airplanemode_active</i>Flight
				Reservation System
			</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="/user">Home</a></li>
				<li><form:form action="/logout" method="POST">
						<button class="linkbutton" type="submit">Logout</button>
					</form:form></li>
			</ul>
		</div>
	</nav>
</body>
</html>