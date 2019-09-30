<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />
</head>
<title>Booked Flight Details</title>
<style>

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

.form-popup {
  display: none;
  position: fixed;
  text-align: center;
  bottom: 10;
  left: 5;
  
  border: 3px solid #f1f1f1;
  z-index: 9;
}

.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  text-align: center;
  align-content: center;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
   text-align: center;
}
.form-container .cancel {
  background-color: red;
 text-align: center;
   
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
				<li><a href="#">Bookings</a></li>
				<li><a href="#">View Flights</a></li>
			</ul>
		</div>
	</nav>
	<!-- Header -->
	<center>
<h2>Passenger Details</h2>
	</center>
<div class="row">
    <div class="col s6 ">
      <div class="card blue-grey darken-1">
        <div class="card-content white-text">
          
               <a>PNR Number: </a>
                <a>Passenger Name: </a><br>
                 <a>Passenger Age: </a><br>
                  <a>Flight Name: </a><br>          
          
          
          
        </div>
        </div>
    </div>
  </div>
            


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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</script>
  


</body>
</html>