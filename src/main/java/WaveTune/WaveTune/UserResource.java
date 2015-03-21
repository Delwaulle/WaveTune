package WaveTune.WaveTune;
import java.util.List;

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
	//private static Map<Integer, User> users = new HashMap<Integer, User>();
	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {

		Date date = new Date();
		String dateInscription =date.toString();
		if(userDao.selectPseudo(user.getPseudo()).equals(null)){
			userDao.insertUser(user.getPseudo(), encodeMD5(user.getPassword()), user.getEmail(), dateInscription);
			new File("directory"+File.separator+user.getPseudo()).mkdirs();
			return Response.accepted().status(Status.CREATED).build();
		}
		return Response.accepted().status(Status.CONFLICT).build();
	}*/

	@GET
	@Path("{pseudo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getAllId(@PathParam("pseudo") String pseudo){
		return musiqueDao.getAllIdMusic(pseudo);
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
	public User getUser(User user ) {
		System.out.println(user.getEmail());
		User out = find(user.getEmail(),user.getPassword());
		if (out == null) {
			System.out.println("erreur");
			throw new WebApplicationException(404);
		}
		return out;
	}

	@GET
	@Path("test")
	public int simpleGet(@PathParam("email") String email){
		System.out.println(email);
		return 0;
	}

}

