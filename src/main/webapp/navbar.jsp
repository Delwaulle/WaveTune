<script src="bootstrap/js/jquery-2.1.3.js"></script>
<script src="bootstrap/js/validator.js"></script>
<script src="cookie.js"></script>
<script>
$(document).ready(function() {
		$('#bsignin').click(function (event) {

			$.ajax({

			url: "http://localhost:8080/v1/user/connection",

			data: JSON.stringify({"email" : $("#email").val(),"password" : $("#passwordinput").val()}),

			type: "POST",

			dataType : "json",
			contentType: "application/json",

			success: function( json ) {
				alert( json.password );
				createCookie("pseudo", json.pseudo, 7);
				$("#buttonsignin").empty();
				$("<p>" +json.password+"</p>").appendTo("#buttonsignin");
			},
			error: function( xhr, status, errorThrown ) {
			alert( "Sorry, there was a problem!" );
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
			},

			complete: function( xhr, status ) {
			alert( "The request is complete!" );
			}
			});
		});
	});
	
	
	$(document).ready(function() {
		$('#confirmsignup').click(function (event) {
		  	// Using the core $.ajax() method
			$.ajax({
			// The URL for the request
			url: "http://localhost:8080/v1/user/",
			// The data to send (will be converted to a query string)
			data: JSON.stringify({'email' : $("#Email").val(),'pseudo' : $("#userid").val,'password' : $("#passwordinput").val()}),
			// Whether this is a POST or GET request
			type: "POST",
			// The type of data we expect back
			dataType : "json",
			// Code to run if the request succeeds;
			// the response is passed to the function
			success: function( json ) {
				//cookie
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
	}); // end document.ready

</script>
<script>
	var login = readCookie("pseudo");
	if(login != null)
		$("#buttonsignin").empty();
		$("<p>" +login+"</p>).appendTo("#buttonsignin");
		$("#deconnect").style.display = "";
	else
		&(<button id="buttonsigninregister" class="btn btn-primary btn-lg" href="#signup" data-toggle="modal" data-target=".bs-modal-sm">Se connecter</button>).appendTo("#buttonsignin");
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
          <a class="navbar-brand" href="/#">WaveTune</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/music.jsp">Ma musique</a></li>
			<li><a href="/#about">&Agrave; propos</a></li>
            <li><a href="/#contact">Contact</a></li>
          </ul>
	<form class="navbar-form navbar-right" role="sign">
                    
<!-- Button trigger modal -->

<div id="buttonsignin">
  <button id="buttonsigninregister" class="btn btn-primary btn-lg" href="#signup" data-toggle="modal" data-target=".bs-modal-sm">Se connecter</button>
  <div id"deconnect" style="display:none;">
  <button id="buttondeconnect" class="btn btn-primary btn-lg" href="#deconnect" data-toggle="modal">D&eacute;connection</button>
  </div>
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
            <form data-toggle="validator"class="form-horizontal">
            <fieldset>
            <!-- Sign In Form -->
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="userid"></label>
              <div class="controls">
                <input id="email" name="email" type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="Email" data-error="Adresse mail non valide" class="input-medium" required="">
              </div>
			  <div class="help-block with-errors"></div>
            </div>

            <!-- Password input-->
            <div class="control-group">
              <label class="control-label" for="passwordinput"></label>
              <div class="controls">
                <input id="passwordinput" name="passwordinput" class="form-control" type="password" data-minlength="6" placeholder="Mot de passe" class="input-medium">
              </div>
            </div>

            <!-- Multiple Checkboxes (inline) -->
            <div class="control-group">
              <label class="control-label" for="rememberme"></label>
              <div class="controls">
                <label class="checkbox inline" for="rememberme-0">
                  <input type="checkbox" name="rememberme" id="rememberme-0" value="Remember me">
                  Se souvenir de moi
                </label>
              </div>
            </div>

            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="signin"></label>
              <div class="controls">
                <button id="bsignin" name="signin" class="btn btn-success">Se connecter</button>
              </div>
            </div>
            </fieldset>
            </form>
        </div>
        <div class="tab-pane fade" id="signup">
            <form id="myForm" data-toggle="validator" class="form-horizontal" name="signin" novalidate >
            <fieldset>
            <!-- Sign Up Form -->
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="Email"></label>
              <div class="controls">
                <input id="Email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" "name="Email" class="form-control" data-error="Adresse mail non valide" type="text" placeholder="Email" class="input-large" required="">
              </div>
			  <div class="help-block with-errors"></div>
            </div>
            
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="userid"></label>
              <div class="controls">
                <input id="userid" name="userid" class="form-control" type="text" placeholder="Pseudo" class="input-large" required="">
              </div>
            </div>
            
            <!-- Password input-->
            <div class="control-group">
              <label for="Inputpassword"class="control-label" for="password"></label>
              <div class="controls">
                <input id="Inputpassword" name="password" class="form-control" data-minlength="6" type="password" placeholder="Mot de passe" class="input-large" required>
                <span class="help-block">6 charact&egrave;res minimum</span>
              </div>
            </div>
            
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="reenterpassword"></label>
              <div class="controls">
                <input type="password" class="form-control" id="inputPasswordConfirm" data-match="#Inputpassword" data-match-error="Mots de passe non confirm&eacute;" placeholder="Confirmation" required>
				<div class="help-block with-errors"></div>
              </div>
            </div>
            
            <!-- Multiple Radios (inline) -->
            <br>
			<div class="control-group">
				<label>
					<input type="checkbox" required/> Je suis un humain.
				</label>
				<span class="help-block">Confirmez que vous &ecirc;tes un humain</span>
			</div>
            
            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="confirmsignup" name="confirmsignup" class="btn btn-success">S'inscrire</button>
              </div>
            </div>
            </fieldset>
            </form>
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
        </div><!--/.nav-collapse -->
      </div>
    </div>
