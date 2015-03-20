package WaveTune.WaveTune;


import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

@ApplicationPath("/v1/")
public class App extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserResource.class);
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
		System.out.println(usr.getEmail()+" -- "+usr.getPseudo());
		File dir =new File("directory");
		if(!dir.exists())
			dir.mkdirs();

	}
}
