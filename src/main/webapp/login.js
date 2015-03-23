	function include(fileName){
		document.write("<script type='text/javascript' src='"+fileName+"'></script>" );
	}
	include("bootstrap/js/jquery-2.1.3.js");
	// IMPORTANT ! Otherwise the DOM is not yet created !
	$(document).ready(function() {
		$('#bsignin').click(function (event) {

			$.ajax({

			url: "http://localhost:8080/v1/user/connection",

			data: {"email" : $("#userid").val(),"password" : $("#password").val()},

			type: "POST",

			dataType : "json",
			contentType: "application/json",

			success: function( json ) {
				$("#buttonsigninregister").empty();
				$("<p>" +json.pseudo+"</p>").appendTo("#buttonsigninregister");
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