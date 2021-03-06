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
  <script src="bootstrap/js/jquery-2.1.3.js"></script>
	<script>
	function download() {
		$("#dl").delay(1000).append('<iframe width="1" height="1" frameborder="0" src="download/WaveTune.apk"></iframe>'); 
	};
	</script>

	<%@include file='navbar.jsp' %>

    <div class="container">

      <div class="starter-template">
        <h1>T&eacute;l&eacute;chargement</h1>
		<p>
		Vous pouvez t&eacute;l&eacute;charger l'apk de notre application en cliquant le bouton ci-dessous.<br>
		Pensez &agrave; d&eacute;sactiver le contr&ocirc;le des sources inconnues sur votre smartphone.
		</p>
	</div><!-- /.container -->
       
	 
	
	<div   class="text-center">
	  <button id="modifier" onclick="download()" class="btn btn-primary btn-lg">T&eacute;l&eacute;charger</button>
	</div>
	
	<div id="dl">
	
	</div>
	
	<div id="footer">
    <div class="navbar-inner  navbar-fixed-bottom">
	<%@include file='footer.jsp' %>
	</div>
	</div>

	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body> 
</html>
