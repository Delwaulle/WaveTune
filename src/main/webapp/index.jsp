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

	<%@include file='navbar.jsp' %>

    <div class="container">

      <div class="starter-template">
        <h1>WaveTune</h1>
        <%@include file='caroussel.jsp' %>
      </div>

	

    </div><!-- /.container -->
	
	<div class="container" id="portfolio">
		<div class="row padding" id="about">
			<div class="col-md-6">
				<img src="images/about.png" class="img-about img-responsive" alt="About image"/>
			</div>
			<div class="col-md-6">
				<h2 class="text-center">&Agrave; propos</h2>
				<p class="text-justify">WaveTune est le gestionnaire musical de votre biblioth&egrave;que en ligne. Il vous permet d'&eacute;couter votre musique que ce soit sur votre PC ou votre appareil Android. Cr&eacute;&eacute; par un groupe d'&eacute;tudiant de l'IUT A Lille 1, WaveTune est la meilleure solution pour correspondre &agrave; vos besoins.</p>
				
			</div>
		</div>	
		<hr />
		
		<div class="row padding" id="contact">
			<div class="col-md-6">
				<h2 class="text-center">Contact</h2>
				<p class="text-justify"> Nous contacter : <br> wavetune@gmail.com <br> Adresse : <br> IUT A Lille 1 - Boulevard Langevin, 59653 Villeneuve-d'Ascq</p>
				
			</div>
			<div class="col-md-6">
				<img src="images/contact.png" class="img-contact img-responsive" alt="Contact image"/>
			</div>
		</div>
		<hr />
	</div>
	
	<%@include file='footer.jsp' %>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body> 
</html>
