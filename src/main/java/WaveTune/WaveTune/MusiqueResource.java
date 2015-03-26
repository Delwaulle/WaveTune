package WaveTune.WaveTune;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

@Path("/music")
public class MusiqueResource {

	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all/{pseudo}")
	public Musique[] getAllMusique(@PathParam("pseudo") String pseudo){
		System.out.println(pseudo);
		List<Musique> liste = musiqueDao.getAllMusicByPseudo(userDao.selectPseudoById(pseudo));
		Musique[] vraiListe=new Musique[liste.size()];
		for(int i =0;i<liste.size();i++){
			vraiListe[i]=liste.get(i);
		}
		return vraiListe;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("album/{pseudo}&{album}")
	public Musique[] getAlbum(@PathParam("pseudo") String pseudo,@PathParam("album") String album){
		List<Musique> liste = musiqueDao.getAlbumByPseudo(userDao.selectPseudoById(pseudo), album);
		Musique[] vraiListe=new Musique[liste.size()];
		for(int i =0;i<liste.size();i++){
			vraiListe[i]=liste.get(i);
		}
		return vraiListe;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("artiste/{pseudo}&{artiste}")
	public Musique[] getArtiste(@PathParam("pseudo") String pseudo,@PathParam("artiste") String artiste){
		List<Musique> liste = musiqueDao.getArtisteByPseudo(userDao.selectPseudoById(pseudo),artiste);
		Musique[] vraiListe=new Musique[liste.size()];
		for(int i =0;i<liste.size();i++){
			vraiListe[i]=liste.get(i);
		}
		return vraiListe;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("album/all/{pseudo}")
	public List<String> getAllAlbum(@PathParam("pseudo") String pseudo){
		return musiqueDao.getAllAlbum(userDao.selectPseudoById(pseudo));
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("artiste/all/{pseudo}")
	public List<String> getAllArtiste(@PathParam("pseudo") String pseudo){
		return musiqueDao.getAllArtiste(userDao.selectPseudoById(pseudo));
	}

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

		File dir =new File("src"+File.separator+"main"+File.separator+"webapp"+File.separator+"directory"+File.separator+pseudo);
		if(!dir.exists())
			dir.mkdirs();

		String path = "src"+File.separator+"main"+File.separator+"webapp"+File.separator+"directory"+File.separator+pseudo+File.separator+headerOfFilePart.getFileName();
		System.out.println(path);


		// save the file to the server
		saveFile(fileInputStream, path);
		String output = "File saved to server location : " + path;

		String url=headerOfFilePart.getFileName();

		System.out.println(output);
		AudioParser ap=new AudioParser(path);
		String title=ap.getTitle();
		String artiste=ap.getArtiste();
		String genre=ap.getGenre();
		String album=ap.getAlbum();
		String img="rsc"+File.separator+"logo.png";
		System.out.println(title + " -- "+ artiste + " -- "+ genre + " -- "+ album + " -- ");
		System.out.println(userDao.selectPseudoById(pseudo));
		musiqueDao.insertMusique(userDao.selectPseudoById(pseudo), title, album, new Date().toString(), url, artiste, genre, img,path);

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
