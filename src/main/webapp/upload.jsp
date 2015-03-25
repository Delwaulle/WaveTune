
	<form id="uploadForm" enctype="multipart/form-data" action="http://localhost:8080/v1/music" target="uploadFrame" method="post">
    <label for="uploadFile">Mp3</label>
    <input id="uploadFile" name="uploadFile" type="file" />
	<input id="user" name="user" type="hidden" value="admin"/>
    <br /><br />
    <input id="uploadSubmit" type="submit" value="Upload !" />
	</form>
	<iframe style="display:none;" id="uploadFrame" name="uploadFrame"></iframe>

