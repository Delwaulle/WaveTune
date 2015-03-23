
/*
function initAudio(){
	var ext, dir , audio, idx;

	dir = "playlist/";
	ext = ".mp3";


	var myList = document.getElementById("myList");
	idx = myList.selectedIndex;
	var options = myList.options;
	var value = options[idx];

	audio = new Audio();
	audio.src = dir + value + ext;
	alert(audio.src);
	audio.play();

	myList.addEventListener("change",changeTrack);


	function changeTrack(event){
		idx = myList.selectedIndex;
		options = myList.options;
		value = options[idx];
		audio.src = dir + value + ext;
		audio.play();
	}
}

window.addEventListener("load",initAudio);

*/



function initAudio(){
	var playlist,ext, dir,value, audio, idxInt, idx, seeking,seekTo,selectedItem,PreviousSelectedItem,nextSelectedItem;
	var liste = document.getElementById("liste");
	var text ="";
	//Extension de fichier 
	dir = "playlist/";
	ext = ".mp3";

	playlist = ["Ahzee","Ariana","Diab"];

	idx=0;
	// La liste d'items
	
	//idx = myList.selectedIndex;
	
	
	
	value = playlist[0];
	audio = new Audio();
	audio.src = dir + value + ext;
	audio.play();

	//Parcours de la playliste
	for(var i=0;i<playlist.length;i++){
		//text += "Track "+i+ " - " + playlist[i]+ "<br>";
		liste.innerHTML = liste.innerHTML + "<span id='piste"+i+"'>Track "+i+ " - " + playlist[i]+"</span><br>";
	}

	//liste.innerHTML = text; 



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



	
	var selectedIndex = playlist[idxInt];
	selectedItem = document.getElementById("piste"+idxInt);
	selectedItem.style.color="blue";


	function nextTrack(event){
		var selectedIndex,PreviousSelectedIndex;
		if(idx==playlist.length-1){
			idx=0;
			selectedIndex = playlist[idx];
			selectedItem = document.getElementById("piste"+idx);
			PreviousSelectedIndex = playlist[playlist.length-1];
			PreviousSelectedItem = document.getElementById("piste"+idx-1);
			value = playlist[idx];
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
			PreviousSelectedItem.style.color="black";
			selectedItem.style.color="blue";

		}

		else{
			idx++;
			selectedIndex = playlist[idx];
			selectedItem = document.getElementById("piste"+idx);
			PreviousSelectedIndex = playlist[idx-1];
			PreviousSelectedItem = document.getElementById("piste"+idx-1);
			//alert(idx);
			value = playlist[idx];
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
			PreviousSelectedItem.style.color="black";
			selectedItem.style.color="blue";
		}
		
	}

	function previousTrack(event){

		var selectedIndex,NextSelectedIndex;
		if(idx==0){
			idx=0;
			value = playlist[idx].text;
			//alert(value);
			audio.src = dir + value + ext;
			audio.play();
		}

		else{
			idx--;
			selectedIndex = playlist[idx];
			NextSelectedIndex = playlist[idx+1];
			//alert(idx);
			value = playlist[idx];
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
			audio.currentTime = seekTo;
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
