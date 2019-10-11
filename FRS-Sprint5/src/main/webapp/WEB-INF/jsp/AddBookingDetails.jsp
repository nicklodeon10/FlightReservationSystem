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
body {
	background-color: #eeeeee;
}

footer {
	width: 100%;
}

a {
	color: #212121;
}

.err, .suc {
    display: none;
}

.err { color: red;}

.suc { color: green;}

.detailrow{
	display: none;
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
		<form:form method="POST" action="/booking/save" modelAttribute="booking">
			<table>
				<tr>
					<th></th>
					<th>Passenger Name</th>
					<th>Passenger Age</th>
					<th>Passenger UIN</th>
				</tr>
				<jstl:forEach items="${booking.passengerList}" var="passenger"
					varStatus="status">
					<tr id="row${status.index}" class="detailrow">
						<td align="center">${status.count}</td>
						<td><input id="pname${status.index}" name="passengerList[${status.index}].passengerName" onblur="validateName('pname${status.index}')" />
						<span id="pname${status.index}Error" class="err">Invalid Name</span>
						<span id="pname${status.index}Success" class="suc">Valid</span></td>
						<td><input id="page${status.index}" name="passengerList[${status.index}].passengerAge" onblur="validateAge('page${status.index}')" />
						<span id="page${status.index}Error" class="err">Invalid Age</span>
						<span id="page${status.index}Success" class="suc">Valid</span></td>
						<td><input id="puin${status.index}" name="passengerList[${status.index}].passengerUIN" onblur="validateUIN('puin${status.index}')" />
						<span id="puin${status.index}Error" class="err">Invalid UIN</span>
						<span id="puin${status.index}Success" class="suc">Valid</span></td>
					</tr>
				</jstl:forEach>
			</table>
			<br>
			<br>
			<input type="submit" value="Confirm"
				class="waves-effect waves-light btn-large" disabled></input>
		</form:form>
		<button onclick="increasePass()">Add Passenger</button>
	</div>

	<script>
	var count=0;
	function increasePass(){
		var id='row'+count;
		document.getElementById(id).style.display='block';
		count++;
	}
	
	function validateName(field) {
		// Get the  value of the input field being submitted
		value = document.getElementById(field).value;
		// Set the error field tag in the html
		errorField = field + 'Error';
		// Set the success field
		successField = field + 'Success';
		var flag=/^[a-zA-Z ]+$/.test(value);
		if (flag) {
			document.getElementById(successField).style.display = 'block';
			document.getElementById(errorField).style.display = 'none';
			return true;
		} else {
			document.getElementById(successField).style.display = 'none';
			document.getElementById(errorField).style.display = 'block';
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
		var flag=false;
		if(value>0 && value<90)
			flag=true;
		if (flag) {
			document.getElementById(successField).style.display = 'block';
			document.getElementById(errorField).style.display = 'none';
			return true;
		} else {
			document.getElementById(successField).style.display = 'none';
			document.getElementById(errorField).style.display = 'block';
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
		var flag=false;
		var temp=""+value;
		if(temp.length==12)
			flag=true;
		if (flag) {
			document.getElementById(successField).style.display = 'block';
			document.getElementById(errorField).style.display = 'none';
			return true;
		} else {
			document.getElementById(successField).style.display = 'none';
			document.getElementById(errorField).style.display = 'block';
			return false;
		}
	}
	</script>

	<!-- Body -->

	<!-- Footer -->
	<jsp:include page="Footer.jsp"></jsp:include>
	<!-- Footer -->
</body>

</html>