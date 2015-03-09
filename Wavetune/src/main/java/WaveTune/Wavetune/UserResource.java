package WaveTune.Wavetune;
import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private static Map<Integer, User> users = new HashMap<Integer, User>();

	@POST
	public User createUser(User user) {
		int id = users.size();
		user.setId(id+1);
		users.put(user.getId(), user);
		return user;
	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		if (users.containsKey(id)) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
		return Response.accepted().status(Status.NOT_FOUND).build();
	}

	protected User find(String name) {
		User out = null;
		for (User user : users.values()) {
			if (user.getPseudo().equals(name)) {
				return user;
			}
		}
		return out;
	}
	protected User find(int id) {
		return users.get(id);
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
	@Path("/{name}")
	public User getUser(@PathParam("name") String name ) {
		User out = find(name);
		if (out == null) {
			throw new WebApplicationException(404);
		}
		return out;
	}

	@GET
	public List<User> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
		return new ArrayList<User>(users.values());
	}

}
