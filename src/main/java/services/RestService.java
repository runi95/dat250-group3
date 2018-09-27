package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Auction;
import entities.Auctions;

@Path("/group3")
@Stateless
public class RestService {
	
	@PersistenceContext(unitName = "group3")
	private EntityManager em;
	
	@GET
	@Path("rest/auctions")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAuctions() {
		TypedQuery<Auction> query = em.createNamedQuery(Auction.FIND_ALL, Auction.class);
		Auctions auctions = new Auctions(query.getResultList());
		
		return Response.ok(auctions).build();
	}

	@GET
	@Path("rest/auctions/{id}")
	public Response getAuction(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Auction auction = em.find(Auction.class, idInt);
		if (auction == null)
			throw new NotFoundException();
		
		return Response.ok(auction).build();
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
