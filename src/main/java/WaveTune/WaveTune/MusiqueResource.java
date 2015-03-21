package WaveTune.WaveTune;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/music")
@Produces(MediaType.APPLICATION_JSON)
public class MusiqueResource {

	private final UserDao userDao=App.dbi.open(UserDao.class);
	private final MusiqueDao musiqueDao=App.dbi.open(MusiqueDao.class);


	@GET
	@Path("{pseudo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getAllId(@PathParam("pseudo") String pseudo){
		return musiqueDao.getAllIdMusic(pseudo);
	}

	@GET
	@Path("id/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Musique getMusicById(@PathParam("id") int id){
		return musiqueDao. getMusique(id);
	}


}
