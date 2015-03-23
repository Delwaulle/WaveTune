



function initAudio(){
	var ext, dir , audio, idxInt, idx, seeking,seekTo;
	//Extension de fichier 
	dir = "playlist/";
	ext = ".mp3";

	// La liste d'items
	var myList = document.getElementById("myList");
	//idx = myList.selectedIndex;
	
	idxInt = 0;
	idx=0;
	var options = myList.options;
	var value = options[idxInt].text;
	//alert(value);

	audio = new Audio();
	audio.src = dir + value + ext;
	audio.play();



	var bntNext = document.getElementById("next");
	var btnPrev = document.getElementById("previous");
	var btnPlayPause = document.getElementById("playPause");
	var btnStop = document.getElementById("stop");
	var seekSlider = document.getElementById("seekSlider");
	var volumeSlider = document.getElementById("volumeSlider");
	var curtimetext = document.getElementById("TimeText");
	var durtimetext = document.getElementById("duration");


	
	bntNext.addEventListener("click",nextTrack);
	btnPrev.addEventListener("click",previousTrack);
	btnPlayPause.addEventListener("click",playPauseTrack);
	btnStop.addEventListener("click",stopTrack);
	seekSlider.addEventListener("mousedown",function(event){seeking = true; seek(event);});
	seekSlider.addEventListener("mousemove",function(event){seek(event);});
	seekSlider.addEventListener("mouseup",function(event){seeking = false;});
	volumeSlider.addEventListener("mousemove", setVolume);
	audio.addEventListener("timeupdate",function(){seektimeupdate(); });



	
	var selectedIndex = options[idxInt];
	selectedIndex.style.color="blue";


	function nextTrack(event){
		var selectedIndex,PreviousSelectedIndex;
		if(idx==options.length-1){
			idx=0;
			selectedIndex = options[idx];
			PreviousSelectedIndex = options[options.length-1];
			options = myList.options;
			value = options[idx].text;
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
			PreviousSelectedIndex.style.color="black";
			selectedIndex.style.color="blue";

		}

		else{
			idx++;
			selectedIndex = options[idx];
			PreviousSelectedIndex = options[idx-1];
			//alert(idx);
			options = myList.options;
			value = options[idx].text;
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
			PreviousSelectedIndex.style.color="black";
			selectedIndex.style.color="blue";
		}
		
	}

	function previousTrack(event){

		var selectedIndex,NextSelectedIndex;
		if(idx==0){
			idx=0;
			options = myList.options;
			value = options[idx].text;
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
		}

		else{
			idx--;
			selectedIndex = options[idx];
			NextSelectedIndex = options[idx+1];
			//alert(idx);
			options = myList.options;
			value = options[idx].text;
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
			NextSelectedIndex.style.color="black";
			selectedIndex.style.color="blue";
		}
	}

	function playPauseTrack(event){
		if(audio.paused){
			audio.play();
			document.getElementById("playPause").style.background="url(pause.png) no-repeat";	
		}
		else{	
			audio.pause();
			document.getElementById("playPause").style.background="url(play.png) no-repeat";
		}
	}


	function stopTrack(event){
		audio.pause();
		audio.currentTime=0;
		seekSlider.value=0;
		document.getElementById("playPause").style.background="url(play.png) no-repeat";
	}

	function setVolume(event){
		audio.volume = volumeSlider.value / 100;
	}

	function seek(event){
		if(seeking){
			seekSlider.value = event.clientX - seekSlider.offsetLeft;
			seekTo = audio.duration * (seekSlider.value / 100);
			audio.currentTime=  seekTo;
		}
	}

	function seektimeupdate(){
		var nt = audio.currentTime * (100 / audio.duration);
		seekSlider.value = nt;
		var curmins = Math.floor(audio.currentTime / 60);
	    var cursecs = Math.floor(audio.currentTime - curmins * 60);
	    var durmins = Math.floor(audio.duration / 60);
	    var dursecs = Math.floor(audio.duration - durmins * 60);
		if(cursecs < 10){ cursecs = "0"+cursecs; }
	    if(dursecs < 10){ dursecs = "0"+dursecs; }
	    if(curmins < 10){ curmins = "0"+curmins; }
	    if(durmins < 10){ durmins = "0"+durmins; }
		curtimetext.innerHTML = curmins+":"+cursecs;
	    durtimetext.innerHTML = durmins+":"+dursecs;
	}
}

window.addEventListener("load",initAudio);
