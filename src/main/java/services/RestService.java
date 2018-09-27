package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/group3")
@Stateless
public class RestService {
	
	@PersistenceContext(unitName = "group3")
	private EntityManager em;
	
	@GET
	@Path("rest/auctions")
	public Response getTweet(@PathParam("id") String id) {
		return Response.ok().build();
	}

}
