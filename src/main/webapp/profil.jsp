<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="bootstrap/docs/favicon.ico">

    <title>WaveTune</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/dist/css/starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="bootstrap/docs/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--<script src="bootstrap/docs/assets/js/ie-emulation-modes-warning.js"></script>-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
  </head>

  <body>
  <script>
  $(document).ready(function() {
	$('#modifier').click(function (event) {
		  // Using the core $.ajax() method
		$.ajax({
			// The URL for the request
			url: "http://localhost:8080/v1/user/update",
			// The data to send (will be converted to a query string)
			data: JSON.stringify({"email" : $("#modifiermail2").val(),"pseudo" : readCookie("pseudo"),"password" : $("#modifiermdp3").val()}),
			// Whether this is a POST or GET request
			type: "PUT",
			// The type of data we expect back
			dataType : "json",
			contentType: "application/json",
			// Code to run if the request succeeds;
			// the response is passed to the function
			success: function( json ) {
				alert("modif");
				document.location.href="http://localhost:8080/";
			},
			// Code to run if the request fails; the raw request and
			// status codes are passed to the function
			error: function( xhr, status, errorThrown ) {
			alert( "Sorry, there was a problem!" );
				console.log( "Error: " + errorThrown );
				console.log( "Status: " + status );
				console.dir( xhr );
			},
			// Code to run regardless of success or failure
			complete: function( xhr, status ) {
			alert( "The request is complete!" );
			}
			});
		});
	});
  
  </script>

	<%@include file='navbar.jsp' %>

    <div class="container">

      <div class="starter-template">
        <h1>Profil</h1>
	</div><!-- /.container -->
       
	 
	<form id="defaultForm" method="post" class="form-horizontal">
	<div class="form-group">
    <label for="exampleInputEmail1">Modifier votre adresse email</label>
    <input type="email" class="form-control" id="modifiermail1" placeholder="Email">
	</div>
	<div class="form-group">
    <label for="exampleInputEmail1"></label>
    <input type="email" class="form-control" id="modifiermail2" placeholder="Confirmation">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Modifier votre mot de passe</label>
		<input type="password" class="form-control" id="modifiermdp1" placeholder="Mot de passe actuel">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1"></label>
		<input type="password" class="form-control" id="modifiermdp2" placeholder="Nouveau mot de passe">
	</div>

	<div class="form-group">
		<label for="exampleInputPassword1"></label>
		<input type="password" class="form-control" id="modifiermdp3" placeholder="Confirmation">
	</div>
		<div class="text-center">
	  <button id="modifier" class="btn btn-primary btn-lg">Modifier</button>
		</div>
	</form>
	
	
	<%@include file='footer.jsp' %>


	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body> 
</html>
