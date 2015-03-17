package WaveTune.Wavetune;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/application")
@Produces(MediaType.APPLICATION_JSON)
public class Synchronisation {

	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);

	@POST
	public Response verifUserConnection(User user) {
		User rep=userDao.containsUser(user.getEmail(), user.getPassword());
		if(rep!=null)
			return Response.status(Response.Status.ACCEPTED).build();
		return Response.status(Response.Status.CONFLICT).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getAllId(User user){
		return musiqueDao.getAllIdMusic(user.getPseudo());
	}



}
