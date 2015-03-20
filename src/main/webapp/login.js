	<script src="bootstrap/js/jquery-2.1.3.js"></script>
	<script>
	// IMPORTANT ! Otherwise the DOM is not yet created !
	$(document).ready(function() {
		$('#bsignin').click(function (event) {
		  	// Using the core $.ajax() method
			$.ajax({
			// The URL for the request
			url: "http://localhost:8080/user/connection",
			// The data to send (will be converted to a query string)
			data: {'email' : $("#userid").val(),'password' : $("#passwordinput").val()},
			// Whether this is a POST or GET request
			type: "POST",
			// The type of data we expect back
			dataType : "json",
			// Code to run if the request succeeds;
			// the response is passed to the function
			success: function( json ) {
				$("#buttonsigninregister").empty();
				$("<p>" +json.pseudo+"</p>").appendTo("#buttonsigninregister");
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