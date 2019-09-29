<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>FRS: Log In</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <style type="text/css">
    body{
  background-color: #eeeeee;
}
footer{
  position: absolute;
  bottom: 0;
  width: 100%;
}
a{
  color: #212121;
}
.brand-logo{
  margin-left: 20px;
}
.card{
  margin-top: 15vh;
}
  </style>
</head>

<body>
	<!-- Header -->
	<nav>
		<div class="nav-wrapper grey darken-4">
			<a href="#" class="brand-logo">
				<i class="large material-icons">airplanemode_active</i>Flight Reservation System
			</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#">Log In</a></li>
				<li><a href="#">Sign Up</a></li>
			</ul>
		</div>
	</nav>
	<!-- Header -->
	
	<!-- Body -->
	 <div class="container">
	 	<div class="row">
	 		<div class="card col s4 offset-s4 center">
	 			<div class="row">
	 				<h5>Log In</h5>
	 				<div class="input-field col s12">
          				<input id="user_name" type="text" class="validate">
          				<label for="first_name">User Name</label>
       				 </div>
       				 <div class="input-field col s12">
         				 <input id="password" type="password" class="validate">
         				 <label for="password">Password</label>
        			</div>
        			<a class="waves-effect waves-light btn large">Submit</a>
	 			</div>
	 			<div class="row">
	 				<a href="#">New User? Sign Up.</a>
	 			</div>
	 		</div>
	 	</div>
	 </div>
	<!-- Body -->
	
	<!-- Footer -->
		<footer class="page-footer grey lighten-3">
          <div class="container"></div>
          <div class="footer-copyright grey darken-4">
            <div class="container">
            © 2019 Flight Reservation System
            <a class="grey-text text-lighten-4 right" href="#!">About Us</a>
            </div>
          </div>
        </footer>
	<!-- Footer -->
    <script>
    </script>
</body>

</html>
