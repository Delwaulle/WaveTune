<script src="bootstrap/js/jquery-2.1.3.js"></script>
<script src="cookie.js"></script>
<script src="bootstrap/js/md5.js"></script>
<script>
window.onload = function(){
	var login = readCookie("pseudo");
	if (login != null){
		$("#buttonsignin").empty();
		$("<div class='btn-group'><button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>Bienvenue "+login+"      <span class='glyphicon glyphicon-cog' aria-hidden='true'></span> </button><ul class='dropdown-menu'><li><a href='/profil.jsp'><i class='icon-user'></i>Profil</a></li><li class='divider'></li><li><a onclick='deconnect()'><i class='icon-list-alt'></i>D&eacute;connection</a></li></ul></div>").appendTo("#buttonsignin");	
	}
	else if(document.location.href=="http://localhost:8080/" || document.location.href=="http://localhost:8080/sorry.jsp" || document.location.href=="http://localhost:8080/#about"  ||  document.location.href=="http://localhost:8080/#contact" ||  document.location.href=="http://localhost:8080/donwload.jsp"){
	
	}
	else {
		document.location.href="http://localhost:8080/sorry.jsp";
	}
}
</script>
<script>
	function deconnect(){
		eraseCookie("pseudo");
		window.location.reload();
		}
</script>

<script>
$(document).ready(function() {
		$('#bsignin').click(function (event) {	
			$('.errorconnect').empty();
			if($("#email").val() == "" || $("#passwordinput").val() == ""){
				$("<br><div class='alert alert-danger'><p>Adresse mail ou mot de passe vide!</p></div>").appendTo('.errorconnect');
			}
			else{
			$.ajax({
			url: "http://localhost:8080/v1/user/connection",
			//MD5 Success
			//data: JSON.stringify({"email" : $("#email").val(),"password" : CryptoJS.MD5($("#passwordinput").val()).toString()}),
			data: JSON.stringify({"email" : $("#email").val(),"password" : $("#passwordinput").val()}),
			type: "POST",
			dataType : "json",
			contentType: "application/json",
			success: function( json ) {
				if( json == null){
					$("<br><div class='alert alert-danger'><p>Email ou mot de passe incorrect !</p></div>").appendTo('.errorconnect');
				}
				else{
				createCookie("pseudo", json.pseudo, 30);
				var deconnect = document.getElementById('deconnect');
				var myModal = document.getElementById('myModal');
				$("#buttonsignin").empty();
				$("<div class='btn-group'><button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>Bienvenue "+json.pseudo+"      <span class='glyphicon glyphicon-cog' aria-hidden='true'></span> </button><ul class='dropdown-menu'><li><a href='/profil.jsp'><i class='icon-user'></i>Profil</a></li><li class='divider'></li><li><a onclick='deconnect()'><i class='icon-list-alt'></i>D&eacute;connection</a></li></ul></div>").appendTo("#buttonsignin");
				$('#myModal').removeClass('fade');
				$('#myModal').modal('hide');
				
				if(document.location.href=="http://localhost:8080/"){
					
				}
				if(document.location.href=="http://localhost:8080/sorry.jsp"){
					document.location.href="http://localhost:8080/"
				}
				}
			},
			error: function( xhr, status, errorThrown ) {
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
			},
			complete: function( xhr, status ) {
			}
			});
			}
		});
	});
	
	
	$(document).ready(function() {
		$('#confirmsignup').click(function (event) {
			$('.errorinscription').empty();
			var regexmail = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
			var regexpassword = /^.{6,15}$/i;
			var Email = $("#Email").val();
			var password = $("#Inputpassword").val();
			var passwordconfirm = $("#inputPasswordConfirm").val();
			if($("#Email").val() == "" || $("#pseudo").val() == "" || $("#Inputpassword").val() == "" || $("#inputPasswordConfirm").val() == ""){
				$("<br><div class='alert alert-danger'><p>Un des champs est vide !</p></div>").appendTo('.errorinscription');
			}
			else if(regexmail.test(Email) == false){
				$("<br><div class='alert alert-danger'><p>Email non valide !</p></div>").appendTo('.errorinscription');
			}
			else if(password != passwordconfirm){
				$("<br><div class='alert alert-danger'><p>Les deux mots de passe sont diff&eacute;rents</p></div>").appendTo('.errorinscription');
			}
			else if(regexpassword.test(password) == false){
				$("<br><div class='alert alert-danger'><p>Le mot de passe doit comporter entre 6 et 15 caract&egrave;res</p></div>").appendTo('.errorinscription');
			}
			else if(regexpassword.test(passwordconfirm) == false){
				$("<br><div class='alert alert-danger'><p>Le mot de passe doit comporter entre 6 et 15 caract&egrave;res</p></div>").appendTo('.errorinscription');
			}
			else{
			$.ajax({
			// The URL for the request
			url: "http://localhost:8080/v1/user/inscription",
			// The data to send (will be converted to a query string)
			
			//MD5 Success
			//data: JSON.stringify({"email" : $("#Email").val(),"pseudo" : $("#pseudo").val(),"password" : CryptoJS.MD5($("#inputPasswordConfirm").val()).toString()}),
			
			data: JSON.stringify({"email" : $("#Email").val(),"pseudo" : $("#pseudo").val(),"password" : $("#inputPasswordConfirm").val()}),
			
			// Whether this is a POST or GET request
			type: "POST",
			// The type of data we expect back
			dataType : "json",
			contentType: "application/json",
			// Code to run if the request succeeds;
			// the response is passed to the function
			success: function( json ) {
				$('#myModal').removeClass('fade');
				$('#myModal').modal('hide');
			},
			// Code to run if the request fails; the raw request and
			// status codes are passed to the function
			error: function( xhr, status, errorThrown ) {
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
			},
			// Code to run regardless of success or failure
			complete: function( xhr, status ) {
			}
			}).done(function(){				
				$.ajax({
			url: "http://localhost:8080/v1/user/connection",
			//MD5 Success
			
			//data: JSON.stringify({"email" : $("#Email").val(),"password" : CryptoJS.MD5($("#inputPasswordConfirm").val()).toString()}),
			data: JSON.stringify({"email" : $("#Email").val(),"password" : $("#inputPasswordConfirm").val()}),
			type: "POST",
			dataType : "json",
			contentType: "application/json",
			success: function( json ) {
				createCookie("pseudo", json.pseudo, 30);
				var deconnect = document.getElementById('deconnect');
				var myModal = document.getElementById('myModal');
				$("#buttonsignin").empty();
				$("<div class='btn-group'><button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>Bienvenue "+json.pseudo+"      <span class='glyphicon glyphicon-cog' aria-hidden='true'></span> </button><ul class='dropdown-menu'><li><a href='/profil.jsp'><i class='icon-user'></i>Profil</a></li><li class='divider'></li><li><a onclick='deconnect()'><i class='icon-list-alt'></i>D&eacute;connection</a></li></ul></div>").appendTo("#buttonsignin");
				$('#myModal').removeClass('fade');
				$('#myModal').modal('hide');
				
				if(document.location.href=="http://localhost:8080/"){
					
				}
				if(document.location.href=="http://localhost:8080/sorry.jsp"){
					document.location.href="http://localhost:8080/"
				}
			},
			error: function( xhr, status, errorThrown ) {
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
			},
			complete: function( xhr, status ) {
			}
			});
			});
			}
			
		});
	}); 
	// end document.ready
</script>

<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">WaveTune</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/music.jsp">Ma musique</a></li>
			<li><a href="/#about">&Agrave; propos</a></li>
            <li><a href="/#contact">Contact</a></li>
			<li><a href="/download.jsp">T&eacute;l&eacute;chargement</a></li>
          </ul>
	<div class="navbar-form navbar-right text-center" role="sign">
                    
<!-- Button trigger modal -->
<div id="buttonsignin">
  <button id="buttonsigninregister" class="btn btn-primary" href="#signup" data-toggle="modal" data-target=".bs-modal-sm">Se connecter</button>
</div>
  
<!-- Modal -->
<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <br>
        <div class="bs-example bs-example-tabs">
            <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#signin" data-toggle="tab">Se connecter</a></li>
              <li class=""><a href="#signup" data-toggle="tab">S'inscrire</a></li>
            </ul>
        </div>
      <div class="modal-body">
        <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade active in" id="signin">
            <div  class="form-horizontal">
            <fieldset>
            <!-- Sign In Form -->
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="userid"></label>
              <div class="controls">
                <input id="email" name="email" type="text" class="form-control"  placeholder="Email"  class="input-medium" >
              </div>

            <!-- Password input-->
            <div class="control-group">
              <label class="control-label" for="passwordinput"></label>
              <div class="controls">
                <input id="passwordinput" name="passwordinput" class="form-control" type="password" placeholder="Mot de passe" class="input-medium">
              </div>
			  <div class="errorconnect"></div>
            </div>
            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="signin"></label>
              <div class="controls">
                <button id="bsignin" name="signin" class="btn btn-success">Se connecter</button>
              </div>
            </div>
            </fieldset>
            </div>
        </div>
        <div class="tab-pane fade" id="signup">
            <div id="myForm" class="form-horizontal" name="signin" novalidate >
            <fieldset>
            <!-- Sign Up Form -->
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="Email"></label>
              <div class="controls">
                <input id="Email"  "name="Email" class="form-control"  type="text" placeholder="Email" class="input-large" required>
              </div>
			  <div class="help-block with-errors"></div>
            </div>
            
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="userid"></label>
              <div class="controls">
                <input id="pseudo" name="userid" class="form-control" type="text" placeholder="Pseudo" class="input-large" >
              </div>
            </div>
            
            <!-- Password input-->
            <div class="control-group">
              <label class="control-label"></label>
              <div class="controls">
                <input id="Inputpassword" class="form-control"  type="password" placeholder="Mot de passe" class="input-large" >
              </div>
            </div>
            
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label"></label>
              <div class="controls">
                <input id="inputPasswordConfirm" type="password"  class="form-control"   placeholder="Confirmation">
				<div class="errorinscription"></div>
            </div>
        
            
            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="confirmsignup" name="confirmsignup" class="btn btn-success">S'inscrire</button>
              </div>
            </div>
            </fieldset>
            </div>
      </div>
    </div>
      </div>
      <div class="modal-footer">
      <center>
        <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
        </center>
      </div>
    </div>
  </div>
</div>
</div>
        </div><!--/.nav-collapse -->
      </div>
    </div>