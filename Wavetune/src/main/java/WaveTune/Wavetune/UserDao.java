package WaveTune.Wavetune;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface UserDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, pseudo STRING, password STRING, email STRING, type STRING, dateInscription STRING, des TEXT, fb TEXT, twitter TEXT, gplus TEXT)")
	public void createTable();

	@SqlUpdate("INSERT INTO users (pseudo,password,type,dateInscription) VALUES (:pseudo,:password,'standard user',:date)")
	public void insertUser(@Bind("pseudo") String pseudo, @Bind("password") String password, @Bind("date") String date);

	@SqlUpdate("INSERT INTO users (pseudo,password,email,type,dateInscription) VALUES(:pseudo,:password,:email,'standard user',:dateInscription)")
	public void insertUser(@Bind("pseudo") String pseudo, @Bind("password") String password, @Bind("email") String email, @Bind("dateInscription") String dateInscription);

	@SqlUpdate("UPDATE users SET pseudo=:pseudo,password=:password,email=:email,type=:type,dateInscription:dateInscription) where id=:id")
	public void updateUser(@Bind("id") int id, @Bind("pseudo") String pseudo, @Bind("password") String password, @Bind("email") String email, @Bind("type") String type, @Bind("dateInscription") String dateInscription);

	@SqlUpdate("UPDATE users SET des=:des where id=:id")
	public void updateProfilDes(@Bind("id") int id, @Bind("des") String des);

	@SqlUpdate("UPDATE users SET fb=:fb where id=:id")
	public void updateProfilFb(@Bind("id") int id, @Bind("fb") String fb);

	@SqlUpdate("UPDATE users SET twitter=:twitter where id=:id")
	public void updateProfilTwitter(@Bind("id") int id, @Bind("twitter") String twitter);

	@SqlUpdate("UPDATE users SET gplus=:gplus where id=:id")
	public void updateProfilGplus(@Bind("id") int id, @Bind("gplus") String gplus);

	@SqlUpdate("UPDATE users SET pseudo=:pseudo where id=:id")
	public void updateUserPseudo(@Bind("id") int id, @Bind("pseudo") String pseudo);

	@SqlUpdate("UPDATE users SET password=:password where id=:id")
	public void updateUserPassword(@Bind("id") int id, @Bind("password") String password);

	@SqlUpdate("UPDATE users SET email=:email where id=:id")
	public void updateUserEmail(@Bind("id") int id, @Bind("email") String email);

	@SqlUpdate("UPDATE users SET type=:type where id=:id")
	public void updateUserType(@Bind("id") int id, @Bind("type") String type);

	@SqlQuery("SELECT * FROM users WHERE pseudo=:pseudo and password=:password ")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public User selectUser(@Bind("pseudo") String pseudo , @Bind("password") String password);

	@SqlQuery("SELECT * FROM users WHERE pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public User selectUserbyPseudo(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT * FROM users ORDER BY id DESC")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<User> selectLastUsers();

	@SqlQuery("SELECT type FROM users WHERE pseudo=:pseudo ")
	public String selectType(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT des FROM users WHERE pseudo=:pseudo ")
	public String selectDes(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT fb FROM users WHERE pseudo=:pseudo ")
	public String selectFb(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT twitter FROM users WHERE pseudo=:pseudo ")
	public String selectTwitter(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT gplus FROM users WHERE pseudo=:pseudo ")
	public String selectGplus(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT pseudo FROM users WHERE pseudo=:pseudo")
	public String selectPseudo(@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT pseudo FROM users WHERE id=:id")
	public String selectPseudo(@Bind("id") int id);

	@SqlQuery("SELECT * FROM users WHERE email=:email and password=:password")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public User containsUser(@Bind("email") String email, @Bind("password") String password);

	@SqlUpdate("DROP TABLE IF EXISTS users")
	public void dropTable();

	@SqlUpdate("DELETE FROM users WHERE id=:id")
	public void deleteUser(@Bind("id") int id);

	@SqlUpdate("SELECT * FROM users WHERE id=:id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public User selectUserByID(@Bind("id") int id);

}