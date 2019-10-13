<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
<title>Add Details</title>
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

.err, .suc {
	display: none;
}

.err {
	color: red;
}

.suc {
	color: green;
}

.detailrow {
	display: none;
}

button {
	margin: 20px;
}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Body -->
	<div class="wrapper">
		<div class="container card" style="padding: 20px;">
			<h4>Add Passenger Details:</h4>
			<form:form method="POST" action="/booking/save"
				modelAttribute="booking">
				<table style="width: 750px">
					<tr>
						<th></th>
						<th>Name</th>
						<th>Age</th>
						<th>UIN</th>
					</tr>
				</table>
				<jstl:forEach items="${booking.passengerList}" var="passenger"
					varStatus="status">
					<table>
						<tr id="row${status.index}" class="detailrow">
							<td align="center">${status.count}</td>
							<td style="width: 250px"><input id="pname${status.index}"
								name="passengerList[${status.index}].passengerName"
								onblur="validateName('pname${status.index}')" /> <span
								id="pname${status.index}Error" class="err">Invalid Name</span> <span
								id="pname${status.index}Success" class="suc">Valid</span></td>
							<td style="width: 250px"><input id="page${status.index}"
								name="passengerList[${status.index}].passengerAge"
								onblur="validateAge('page${status.index}')" /> <span
								id="page${status.index}Error" class="err">Invalid Age</span> <span
								id="page${status.index}Success" class="suc">Valid</span></td>
							<td style="width: 250px"><input id="puin${status.index}"
								name="passengerList[${status.index}].passengerUIN"
								onblur="validateUIN('puin${status.index}')" /> <span
								id="puin${status.index}Error" class="err">Invalid UIN</span> <span
								id="puin${status.index}Success" class="suc">Valid</span></td>
						</tr>
					</table>
				</jstl:forEach>
				<table>
					<tr>
						<td><button id="increaseButton" onclick="increasePass()"
								class="waves-effect waves-light btn-large">Add
								Passenger</button></td>
						<td><input type="submit" value="Confirm" id="submitButton"
							class="waves-effect waves-light btn-large" disabled></input></td>
					</tr>
				</table>
				<br>
				<br>
			</form:form>
		</div>
	</div>
	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
	<script>
		var count = 0;
		var nameFlag = false;
		var ageFlag = false;
		var uinFlag = false;

		function enableSubmit() {
			if (nameFlag && ageFlag && uinFlag) {
				document.getElementById('submitButton').disabled = false;
				document.getElementById('increaseButton').disabled = false;
			}
		}

		function increasePass() {
			if (count > -1) {
				document.getElementById('increaseButton').disabled = true;
			}
			var id = 'row' + count;
			document.getElementById(id).style.display = 'block';
			count++;
		}

		function validateName(field) {
			// Get the  value of the input field being submitted
			value = document.getElementById(field).value;
			// Set the error field tag in the html
			errorField = field + 'Error';
			// Set the success field
			successField = field + 'Success';
			var flag = /^[a-zA-Z ]+$/.test(value);
			if (flag) {
				document.getElementById(successField).style.display = 'block';
				document.getElementById(errorField).style.display = 'none';
				nameFlag = true;
				return true;
			} else {
				document.getElementById(successField).style.display = 'none';
				document.getElementById(errorField).style.display = 'block';
				nameFlag = false;
				return false;
			}
		}

		function validateAge(field) {
			// Get the  value of the input field being submitted
			value = document.getElementById(field).value;
			// Set the error field tag in the html
			errorField = field + 'Error';
			// Set the success field
			successField = field + 'Success';
			var flag = false;
			if (value > 0 && value < 90)
				flag = true;
			if (flag) {
				document.getElementById(successField).style.display = 'block';
				document.getElementById(errorField).style.display = 'none';
				ageFlag = true;
				return true;
			} else {
				document.getElementById(successField).style.display = 'none';
				document.getElementById(errorField).style.display = 'block';
				ageFlag = false;
				return false;
			}
		}

		function validateUIN(field) {
			// Get the  value of the input field being submitted
			value = document.getElementById(field).value;
			// Set the error field tag in the html
			errorField = field + 'Error';
			// Set the success field
			successField = field + 'Success';
			var flag = false;
			var temp = "" + value;
			if (temp.length == 12)
				flag = true;
			if (flag) {
				document.getElementById(successField).style.display = 'block';
				document.getElementById(errorField).style.display = 'none';
				uinFlag = true;
				enableSubmit();
				return true;
			} else {
				document.getElementById(successField).style.display = 'none';
				document.getElementById(errorField).style.display = 'block';
				uinFlag = false;
				return false;
			}
		}
	</script>
</body>

</html>