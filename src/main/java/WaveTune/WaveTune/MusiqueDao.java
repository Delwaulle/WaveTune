package WaveTune.WaveTune;

import java.util.Iterator;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface MusiqueDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS musiques (id INTEGER PRIMARY KEY,user_id INTEGER, titre STRING, album TEXT, dateUpload STRING, url TEXT, artiste TEXT,genre TEXT, img TEXT,path TEXT,FOREIGN KEY (user_id) REFERENCES Persons(id))")
	public void createTable();

	@SqlUpdate("INSERT INTO musiques (user_id,titre,album,dateUpload,url,artiste,genre,img,path) VALUES (:user_id,:titre,:album,:dateUpload,:url,:artiste,:genre,:img,:path)")
	public void insertMusique(@Bind("user_id") int user_id,@Bind("titre") String titre, @Bind("album") String album, @Bind("dateUpload") String dateUpload, @Bind("url") String url, @Bind("artiste") String artiste, @Bind("genre") String genre,@Bind("img") String img,@Bind("path") String path);


	@SqlQuery("SELECT * FROM musiques WHERE user_id=:user_id;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<Musique> getAllMusic(@Bind("user_id") int user_id);

	@SqlQuery("SELECT * FROM musiques WHERE user_id=:user_id  ORDER BY titre ASC;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<Musique> getAllMusicByPseudo(@Bind("user_id") int user_id);


	@SqlQuery("SELECT * FROM musiques WHERE user_id=:user_id and album=:album ORDER BY titre ASC;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<Musique> getAlbumByPseudo(@Bind("user_id") int user_id, @Bind("album") String album);

	@SqlQuery("SELECT * FROM musiques WHERE user_id=:user_id and artiste=:artiste ORDER BY titre ASC;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<Musique> getArtisteByPseudo(@Bind("user_id") int user_id, @Bind("artiste") String artiste);

	@SqlQuery("SELECT album FROM musiques WHERE user_id=:user_id;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<String> getAllAlbum(@Bind("user_id") int user_id);

	@SqlQuery("SELECT artiste FROM musiques WHERE user_id=:user_id;")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public List<String> getAllArtiste(@Bind("user_id") int user_id);

	@SqlQuery("SELECT path FROM musiques WHERE id=:id;")
	public String getPathMusic(@Bind("id")int id);

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

	@SqlQuery("SELECT * FROM musiques where user_id=:user_id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Musique> getIdMusique(@Bind("user_id") int user_id);

	@SqlUpdate("DROP TABLE IF EXISTS musiques")
	public void dropTable();

	@SqlUpdate("DELETE FROM musiques where id=:id")
	public void deleteMusique(@Bind("id") int id);

}
