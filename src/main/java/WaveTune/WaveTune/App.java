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
		File dir =new File("directory");
		if(!dir.exists())
			dir.mkdirs();
		String path="rsc"+File.separator+"Eminem - We Made You"+".mp3";
		AudioParser ap=new AudioParser(path);
		System.out.println(ap.getTitle());
		System.out.println(ap.getArtiste());
		System.out.println(ap.getGenre());
		System.out.println(ap.getAlbum());
		System.out.println(new Date().toString());
		dbi.open(MusiqueDao.class).insertMusique(1, ap.getTitle(), ap.getAlbum(), new Date().toString(), path, ap.getArtiste(), ap.getGenre(), "rsc"+File.separator+"logo.png");
	}
}
