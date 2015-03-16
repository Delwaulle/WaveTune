package WaveTune.Wavetune;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	//private static Map<Integer, User> users = new HashMap<Integer, User>();
	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);

	@POST
	public Response createUser(String pseudo, String password,String email) {

		Date date = new Date();
		String dateInscription =date.toString();
		if(userDao.selectPseudo(pseudo).equals(null)){
			userDao.insertUser(pseudo, password, email, dateInscription);
			return Response.accepted().status(Status.CREATED).build();
		}
		return Response.accepted().status(Status.CONFLICT).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getAllId(User user){
		return musiqueDao.getAllIdMusic(user.getPseudo());
	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		if (userDao.selectPseudo(id)!=null) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
		return Response.accepted().status(Status.NOT_FOUND).build();
	}

	protected User find(String email,String password) {
		User rep=userDao.containsUser(email, password);
		return rep;
	}
	protected User find(int id) {
		return userDao.selectUserByID(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
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

	@GET
	@Path("/{email}/{password}")
	public User getUser(@PathParam("email") String email,@PathParam("password") String password ) {
		User out = find(email,password);
		if (out == null) {
			throw new WebApplicationException(404);
		}
		return out;
	}

}

