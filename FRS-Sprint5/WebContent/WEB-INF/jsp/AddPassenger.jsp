<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->

	<div class="container card centre">
		<h4>Add Passenger Details:</h4>
		<form:form method="POST" action="saveBooking" modelAttribute="booking">
			<table>
				<tr>
				<th></th>
					<th>Passenger Name</th>
					<th>Passenger Age</th>
					<th>Passenger UIN</th>
				</tr>
				<jstl:forEach items="${booking.passengerList}" var="passenger"
					varStatus="status">
					<tr>
						<td align="center">${status.count}</td>
						<td><input
							name="passengerList[${status.index}].passengerName" /></td>
						<td><input name="passengerList[${status.index}].passengerAge" /></td>
						<td><input name="passengerList[${status.index}].passengerUIN" /></td>
					</tr>
				</jstl:forEach>
			</table>
			<br>
			<br>
			<input type="submit" value="Confirm"
				class="waves-effect waves-light btn-large"></input>
		</form:form>
	</div>

	<script>
		function resetFields() {
			document.getElementById('passenger_name').value = "";
			document.getElementById('passenger_uin').value = "";
			document.getElementById('passenger_age').value = "";
		}
	</script>

	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer -->
</body>

</html>