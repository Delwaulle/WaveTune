package WaveTune.WaveTune;

import javax.ws.rs.Path;

@Path("/music")
public class MusiqueResource {

	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);

	/*@POST
	public Response uploadMusique(){
		String path="rsc/Eminem - We Made You"+".mp3";
		AudioParser ap=new AudioParser(path);
		String title=ap.getTitle();
		String artiste=ap.getArtiste();
		String genre=ap.getGenre();
		String album=ap.getAlbum();
		String img="rsc"+File.separator+"logo.png";
		musiqueDao.insertMusique("admin", title, album, new Date().toString(), path, artiste, genre, img);

		return Response.status(Response.Status.ACCEPTED).build();
		//return Response.status(Response.Status.CONFLICT).build();

	}


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {


		System.out.println("upload");

		String path = "C://" + contentDispositionHeader.getFileName();
		System.out.println(path);


		// save the file to the server
		saveFile(fileInputStream, path);

		String output = "File saved to server location : " + path;

		AudioParser ap=new AudioParser(path);
		String title=ap.getTitle();
		String artiste=ap.getArtiste();
		String genre=ap.getGenre();
		String album=ap.getAlbum();
		String img="rsc"+File.separator+"logo.png";
		musiqueDao.insertMusique("admin", title, album, new Date().toString(), path, artiste, genre, img);

		return Response.status(200).entity(output).build();

	}*/

	// save uploaded file to a defined location on the server




}
