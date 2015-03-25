package WaveTune.WaveTune;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {
	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/inscription")
	public User createUser(User user) {
		System.out.println("CREATE USER");
		Date date = new Date();
		String dateInscription =date.toString();
		if(userDao.selectPseudo(user.getPseudo())==null){
			System.out.println("pas present bdd");
			System.out.println("user : "+user.getPseudo()+" --- "+user.getPassword()+" --- "+user.getEmail());
			userDao.insertUser(user.getPseudo(), user.getPassword(), user.getEmail(), dateInscription);
			new File("directory"+File.separator+user.getPseudo()).mkdirs();
			return user;
		}
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/alex")
	public User createUser2(User user) {
		System.out.println("CREATE USER");
		Date date = new Date();
		String dateInscription =date.toString();
		if(userDao.selectPseudo(user.getPseudo())==null){
			userDao.insertUser(user.getPseudo(), encodeMD5(user.getPassword()), user.getEmail(), dateInscription);
			new File("directory"+File.separator+user.getPseudo()).mkdirs();
			return user;
		}
		return null;
	}

	private String encodeMD5(String password){
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] thedigest = md.digest(bytesOfMessage);
		String passMD5 = null;
		try {
			passMD5 = new String(thedigest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passMD5;
	}

	/*@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		if (userDao.selectPseudo(id)!=null) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
		return Response.accepted().status(Status.NOT_FOUND).build();
	}
	 */
	protected User find(String email,String password) {
		User rep=userDao.containsUser(email, password);
		return rep;
	}
	protected User find(int id) {
		return userDao.selectUserByID(id);
	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("{id}")
	public Response updateUser(@PathParam("id") int id,
			User user) {
		User oldUser = find(id);
		System.out.println("Should update user with id: "+id
				+" ("+oldUser+") to " +user);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		oldUser.setPseudo(user.getPseudo());
		return Response.status(200).entity(oldUser).build();
	}

	@POST
	@Path("connection")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser_post(User user ) {
		System.out.println(user.getEmail());
		User out = find(user.getEmail(),user.getPassword());
		return out;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{email}&{password}")
	public User getUser(@PathParam("email") String email, @PathParam("password") String password){
		System.out.println(email+" -- "+password);
		return find(email,password);
	}

	@GET
	@Path("test")
	public int simpleGet(@PathParam("email") String email){
		System.out.println(email);
		return 0;
	}

}

