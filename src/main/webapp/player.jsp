<html>


	<head>
		
		<link rel="stylesheet" type="text/css" href="style/style.css">
		
	</head>

	<body>
		<center>
		<br><br>

			<div id="mp3Player">
				<div id="time">
					<input type="range" id="seekSlider" min="0" max="100" value="0" step="1">
					<div id="timeBox">
						<span id="TimeText">
							00:00
						</span>
						/
						<span id="duration">
							00:00
						</span>
					</div>
					<input type="range" id="volumeSlider" min="0" max="100" value="100" step="1">
				</div>

				<div id="buttons">
					<button type="button" id="playPause"></button>
					<button type="button" id="stop"></button>
					<button type="button" id="previous" ></button>
					<button type="button" id="next" ></button><br>
				</div>

				
				<div id="liste" style="overflow-y:scroll;">

					
				</div>
			</div>
		</center>
		
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
	</body>



</html>