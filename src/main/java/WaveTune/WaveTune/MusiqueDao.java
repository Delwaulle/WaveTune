package WaveTune.WaveTune;

import java.util.Iterator;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface MusiqueDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS musiques (id INTEGER PRIMARY KEY,user_id INTEGER, titre STRING, album TEXT, dateUpload STRING, url TEXT, artiste TEXT,genre TEXT, img TEXT,FOREIGN KEY (user_id) REFERENCES Persons(id))")
	public void createTable();

	@SqlUpdate("INSERT INTO musiques (user_id,titre,album,dateUpload,url,artiste,genre,img) VALUES (:user_id,:titre,:album,:dateUpload,:url,:artiste,:genre,:img)")
	public void insertMusique(@Bind("user_id") int user_id,@Bind("titre") String titre, @Bind("album") String album, @Bind("dateUpload") String dateUpload, @Bind("url") String url, @Bind("artiste") String artiste, @Bind("genre") String genre,@Bind("img") String img);


	@SqlQuery("SELECT * FROM musiques WHERE user_id=:user_id;")
	public List<Musique> getAllMusic(@Bind("user_id") int user_id);

	@SqlQuery("SELECT * FROM musiques WHERE pseudo=:pseudo  ORDER BY titre ASC;")
	public List<Musique> getAllMusicByPseudo(@Bind("pseudo") String pseudo);


	@SqlQuery("SELECT * FROM musiques WHERE pseudo=:pseudo and album=:album ORDER BY titre ASC;")
	public List<Musique> getAlbumByPseudo(@Bind("pseudo") String pseudo, @Bind("album") String album);

	@SqlQuery("SELECT * FROM musiques WHERE pseudo=:pseudo and artiste=:artiste ORDER BY titre ASC;")
	public List<Musique> getArtisteByPseudo(@Bind("pseudo") String pseudo, @Bind("artiste") String artiste);

	@SqlQuery("SELECT album FROM musiques WHERE pseudo=:pseudo;")
	public List<String> getAllAlbum(@Bind("pseudo")String pseudo);

	@SqlQuery("SELECT artiste FROM musiques WHERE pseudo=:pseudo;")
	public List<String> getAllArtiste(@Bind("pseudo")String pseudo);

	@SqlQuery("SELECT url FROM musiques WHERE id=:id;")
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
