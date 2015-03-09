package WaveTune.Wavetune;

import java.util.Iterator;

public interface MusiqueDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS musiques (id INTEGER PRIMARY KEY,pseudo STRING, titre STRING, album TEXT, dateUpload STRING, url TEXT, artiste TEXT)")
	public void createTable();

	@SqlUpdate("INSERT INTO musiques (titre,album,dateUpload,url,artiste) VALUES (:titre,:album,:dateUpload,:url,:artiste)")
	public void insertMusique(@Bind("pseudo") String pseudo,@Bind("titre") String titre, @Bind("album") String album, @Bind("dateUpload") String dateUpload, @Bind("url") String url, @Bind("artiste") String artiste);

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
