package WaveTune.Wavetune;

import java.util.ArrayList;
import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface MusiqueDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS musiques (id INTEGER PRIMARY KEY,pseudo STRING, titre STRING, album TEXT, dateUpload STRING, url TEXT, artiste TEXT,genre TEXT, img TEXT)")
	public void createTable();

	@SqlUpdate("INSERT INTO musiques (titre,album,dateUpload,url,artiste,genre,img) VALUES (:titre,:album,:dateUpload,:url,:artiste,:genre,:img)")
	public void insertMusique(@Bind("pseudo") String pseudo,@Bind("titre") String titre, @Bind("album") String album, @Bind("dateUpload") String dateUpload, @Bind("url") String url, @Bind("artiste") String artiste, @Bind("genre") String genre,@Bind("img") String img);


	@SqlQuery("SELECT id FROM musiques WHERE pseudo=:pseudo;")
	public ArrayList<Integer> getAllIdMusic(@Bind("pseudo") String pseudo);


	@SqlQuery("SELECT * FROM musiques where url=:url")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Musique getVideoByLink(@Bind("url") String url);

	@SqlQuery("SELECT * FROM musiques where id=:id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Musique getMusique(@Bind("id") int id);

	@SqlQuery("SELECT * FROM musiques where titre LIKE ('%:titre%')")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> recherche(@Bind("titre") String titre);

	@SqlQuery("SELECT * FROM musiques")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> getAllMusique();

	@SqlQuery("SELECT * FROM musiques where titre=:titre")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> getAllMusiqueTitre(@Bind("titre") String titre);

	@SqlQuery("SELECT * FROM musiques ORDER BY id DESC")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> getLastMusique();

	@SqlQuery("SELECT * FROM musiques where pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> getPseudoMusique(@Bind("pseudo") String pseudo);

	@SqlUpdate("DROP TABLE musiques")
	public void dropTable();

	@SqlUpdate("DELETE FROM musiques where id=:id")
	public void deleteMusique(@Bind("id") int id);

}
