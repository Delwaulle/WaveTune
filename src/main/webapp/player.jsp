<html>


	<head>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
		<style>
			/*#previous{width: 24px ; height: 24px}*/
			#myList{width:277px;}
			#mp3Player{width: 400px; border: 1px solid black;padding-top: 30px;padding-bottom: 30px}
			#playPause{width: 24px; height:24px ;background: url("images/pause.png") no-repeat; cursor: pointer;border: none}
			#previous{width: 24px; height:24px ;background: url("images/previous.png") no-repeat; cursor: pointer;border: none}
			#next{width: 24px; height:24px ;background: url("images/next.png") no-repeat; cursor: pointer ;border: none}
			#stop{width: 24px; height:24px ;background: url("images/stop.png") no-repeat;cursor: pointer ;border: none}
			#timeBox{display: inline-block;text-align: center;width: 100px}
			#time{width:300px;background: linear-gradient(#FF8000,#FFB266);height: 25px;padding-top: 7px}
			input[type='range']{height: 8px;-webkit-appearance:none !important;cursor: pointer;border-bottom:#333 1px solid;padding: 0px;margin:0px;background: black}
			#volumeSlider{width:40px;height: 8px}
			#buttons{margin-top: 7px;}
			#liste{height: 100px;width: 300px;background: linear-gradient(#FF8000,#FFB266);margin-top: 5px;}
			#liste.scroll{overflow: scroll; height: 40px}
			#liste span:hover{border: 1px solid black;border-radius: 3px}




			
		</style>
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
	</body>



</html>