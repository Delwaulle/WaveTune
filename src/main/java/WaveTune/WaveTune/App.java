package WaveTune.WaveTune;


import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

@ApplicationPath("/v1/")
public class App extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserResource.class);
		s.add(MultiPartFeature.class);
		s.add(LoggingFilter.class);
		s.add(MusiqueResource.class);
		s.add(Synchronisation.class);
		return s;
	}

	public static DBI dbi;
	static {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:"+System.getProperty("java.io.tmpdir")+"wavetune.db");
		System.out.println("jdbc:sqlite:"+System.getProperty("java.io.tmpdir")+"wavetune.db");
		dbi = new DBI(ds);
		dbi.open(UserDao.class).dropTable();
		dbi.open(MusiqueDao.class).dropTable();
		dbi.open(UserDao.class).createTable();
		dbi.open(MusiqueDao.class).createTable();
		dbi.open(UserDao.class).createUserAdmin();
		User usr=dbi.open(UserDao.class).selectUserbyPseudo("admin");
		System.out.println(usr.getEmail()+" -- "+usr.getPseudo()+" -- "+usr.getId());
		File dir =new File("src"+File.separator+"main"+File.separator+"webapp"+File.separator+"directory"+File.separator+usr.getPseudo());
		if(!dir.exists())
			dir.mkdirs();
		String path="src"+File.separator+"main"+File.separator+"webapp"+File.separator+"directory"+File.separator+"admin"+File.separator+"03  Muse - Undisclosed Desires.mp3";
		AudioParser ap=new AudioParser(path);
		dbi.open(MusiqueDao.class).insertMusique(1, ap.getTitle(), ap.getAlbum(), new Date().toString(), "03  Muse - Undisclosed Desires.mp3", ap.getArtiste(), ap.getGenre(), "rsc"+File.separator+"logo.png",path);

		String path2="src"+File.separator+"main"+File.separator+"webapp"+File.separator+"directory"+File.separator+"admin"+File.separator+"Britney Spears - if You Seek Amy.mp3";
		AudioParser ap2=new AudioParser(path2);
		dbi.open(MusiqueDao.class).insertMusique(1, ap2.getTitle(), ap2.getAlbum(), new Date().toString(), "Britney Spears - if You Seek Amy.mp3", ap2.getArtiste(), ap2.getGenre(), "rsc"+File.separator+"logo.png",path);
	}
}
