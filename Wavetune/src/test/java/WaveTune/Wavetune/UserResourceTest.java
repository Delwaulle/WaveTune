package WaveTune.Wavetune;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class UserResourceTest  extends JerseyTest{

	@Test
	public void testCreateUser(){
		User user = new User(1, "MoussahRasay", "mdp123", "delwaulle.loic@hotmail.fr");
		// Conversion de l'instance de User au format JSON pour l'envoi
		Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);

		// Envoi de la requête HTTP POST pour la création de l'utilisateur
		Response response = target("/users").request().post(userEntity);

		// Vérification du code de retour HTTP
		assertEquals(201, response.getStatus());

		// Vérification que la création renvoie bien l'URI de la nouvelle instance dans le header HTTP 'Location'
		// ici : http://localhost:8080/users/MoussahRasay
		URI uriAttendue = target("/users").path(user.getPseudo()).getUri();
		assertTrue(uriAttendue.equals(response.getLocation()));
	}

}
