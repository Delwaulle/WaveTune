package WaveTune.WaveTune;

import java.io.File;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/synchronisation")
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
	@Path("music/{id}")
	public List<Musique> getAllMusique(@PathParam("id") int id){
		return musiqueDao.getAllMusic(id);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadMusic(@PathParam("id") int id){
		String pathMusic=musiqueDao.getPathMusic(id);
		File file=new File(pathMusic);
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
				.build();
	}



}
