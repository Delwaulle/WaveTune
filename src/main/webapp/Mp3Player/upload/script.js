/*
readonly attribute DOMString name;
readonly attribute Date lastModifiedDate;

if (window.File && window.FileReader && window.FileList && window.Blob) {
	alert("File API supported.!");
} else {
	alert(‘The File APIs are not fully supported in this browser.’);
}

function startRead(evt) {
    var file = document.getElementById("file").files[0];
    if (file) {
        //  getAsText(file);
        alert("Name: " + file.name + "\n" + "Last Modified Date :" + file.lastModifiedDate);
    }
}


function startReadFromDrag(evt) {
    var file = evt.dataTransfer.files[0];
    if (file) {
        //  getAsText(file);
        var fileAttr = "Name: " + file.name + "\n" + "Last Modified Date :" + file.lastModifiedDate;
        $(‘#draghere’).text(fileAttr);
        alert(fileAttr);

    }
    evt.stopPropagation();
    evt.preventDefault();
}
	var dropingDiv = document.getElementById(‘draghere‘);
	dropingDiv.addEventListener(‘dragover‘, domagic, false);
	dropingDiv.addEventListener(‘drop‘, startReadFromDrag, false);*/


var context = new (window.AudioContext || window.webkitAudioContext)();
var source;
var processor;
var analyser;
var xhr;

function initAudio(data) {
    source = context.createBufferSource();

    if(context.decodeAudioData) {
        context.decodeAudioData(data, function(buffer) {
            source.buffer = buffer;
            createAudio();
        }, function(e) {
            console.log(e);
        });
    } else {
        source.buffer = context.createBuffer(data, false /*mixToMono*/);
        createAudio();
    }
}

function createAudio() {
    processor = context.createJavaScriptNode(2048 /*bufferSize*/, 1 /*num inputs*/, 1 /*numoutputs*/);
    processor.onaudioprocess = processAudio;
    analyser = context.createAnalyser();

    source.connect(context.destination);
    source.connect(analyser);

    analyser.connect(processor);
    processor.connect(context.destination);

    source.noteOn(0);
    setTimeout(disconnect, source.buffer.duration * 1000);
}

function disconnect() {
    source.noteOff(0);
    source.disconnect(0);
    processor.disconnect(0);
    analyser.disconnect(0);
}

function processAudio(e) {
    var freqByteData = new Uint8Array(analyser.frequencyBinCount);
    analyser.getByteFrequencyData(freqByteData);
    console.log(freqByteData);
}

function handleResult() {
    if (xhr.readyState == 4 /* complete */) {
        switch(xhr.status) {
            case 200: /* Success */
                initAudio(request.response);
                break;
            default:
                break;
        }
        xhr = null;
    }      
}

function dropEvent(evt) {
    evt.stopPropagation();
    evt.preventDefault();

    var droppedFiles = evt.dataTransfer.files;

    //Ajax the file to the server and respond with the data

    var formData = new FormData();
    for(var i = 0; i < droppedFiles.length; ++i) {
            var file = droppedFiles[i];

            files.append(file.name, file);
    }

    xhr = new XMLHttpRequest();
    xhr.open("POST", 'URL');  
    xhr.onreadystatechange = handleResult;
    xhr.send(formData);
}

function dragOver(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    return false;
}

var dropArea = document.getElementById('div1');
dropArea.addEventListener('drop', dropEvent, false);
dropArea.addEventListener('dragover', dragOver, false);