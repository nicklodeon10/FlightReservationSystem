<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cancel Booking</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
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
  bottom: 0;
  right: 15px;
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

<div class="jumbotron text-center">
  <h1 align="center">Cancel Booking</h1>

  <p align="center">Enter Booking Id to cancel booking</p> 
</div>
  
<div class="container">
  
 <div class="row">
    <div class="input-field col s6 offset-s3">
      <input  id="id" type="number" class="validate">
      <label class="active" for="booking_id">Booking Id:</label>
    </div>
  </div>
        

</div>
<center>
<button class="btn waves-effect waves-teal open-button col s6" onclick="cancelForm()">Cancel</button>
</center>


<div class="form-popup" id="myForm" >
  <form action="/action_page.php" class="form-container" >

 <h3 >Are You Sure?</h3>
 
  <button type="submit" class="btn">Yes</button>
  
    <button type="button" class="btn cancel" onclick="closeForm()">No</button>

  </form>
  
  
</div>

  <script>
function cancelForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}
</script>
  
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