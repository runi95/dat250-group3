package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	public Response getAuctions() {
		return Response.ok().build();
	}

	@GET
	@Path("rest/auctions/{id}")
	public Response getAuction(@PathParam("id") String id) {
		return Response.ok().build();
	}
	
	@GET
	@Path("rest/auctions/{id}/bids")
	public Response getAuctionBids(@PathParam("id") String id) {
		return Response.ok().build();
	}
	
	@GET
	@Path("rest/auctions/{aid}/bids/{bid}")
	public Response getAuctionBid(@PathParam("aid") String aid, @PathParam("bid") String bid) {
		return Response.ok().build();
	}
	
	@POST
	@Path("rest/auctions/{id}/bids")
	public Response setAuctionBid(@PathParam("id") String id) {
		return Response.ok().build();
	}
}
