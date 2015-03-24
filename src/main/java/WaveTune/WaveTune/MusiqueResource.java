package WaveTune.WaveTune;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

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
		musiqueDao.insertMusique(1, title, album, new Date().toString(), path, artiste, genre, img);

		return Response.status(Response.Status.ACCEPTED).build();
		//return Response.status(Response.Status.CONFLICT).build();

	}*/


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {

		FormDataBodyPart filePart = form.getField("uploadFile");
		FormDataBodyPart userPart=form.getField("user");

		String pseudo=userPart.getValueAs(String.class);
		ContentDisposition headerOfFilePart =  filePart.getContentDisposition();



		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		System.out.println("upload");
		System.out.println("pseudo : " +pseudo);

		File dir =new File("directory"+File.separator+pseudo);
		if(!dir.exists())
			dir.mkdirs();

		String path = "directory"+File.separator+pseudo+File.separator+headerOfFilePart.getFileName();
		System.out.println(path);


		// save the file to the server
		saveFile(fileInputStream, path);
		String output = "File saved to server location : " + path;

		System.out.println(output);
		AudioParser ap=new AudioParser(path);
		String title=ap.getTitle();
		String artiste=ap.getArtiste();
		String genre=ap.getGenre();
		String album=ap.getAlbum();
		String img="rsc"+File.separator+"logo.png";
		System.out.println(title + " -- "+ artiste + " -- "+ genre + " -- "+ album);
		System.out.println(userDao.selectPseudoById(pseudo));
		musiqueDao.insertMusique(userDao.selectPseudoById(pseudo), title, album, new Date().toString(), path, artiste, genre, img);

		return Response.status(Response.Status.ACCEPTED).build();

	}
	// save uploaded file to a defined location on the server

	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


}
