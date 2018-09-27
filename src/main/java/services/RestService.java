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
import entities.Bid;
import entities.Bids;

@Path("/rest")
@Stateless
public class RestService {
	
	@PersistenceContext(unitName = "group3")
	private EntityManager em;
	
	@GET
	@Path("auctions")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAuctions() {
		TypedQuery<Auction> query = em.createNamedQuery(Auction.FIND_ALL, Auction.class);
		Auctions auctions = new Auctions(query.getResultList());
		
		return Response.ok(auctions).build();
	}

	@GET
	@Path("auctions/{id}")
	public Response getAuction(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		
		Auction auction = em.find(Auction.class, idInt);
		if (auction == null)
			throw new NotFoundException();
		
		return Response.ok(auction).build();
	}
	
	@GET
	@Path("auctions/{id}/bids")
	public Response getAuctionBids(@PathParam("id") String id) {
		TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
		Bids bids  = new Bids(query.getResultList());
		
		return Response.ok(bids).build();
	}
	
	@GET
	@Path("auctions/{aid}/bids/{bid}")
	public Response getAuctionBid(@PathParam("aid") String aid, @PathParam("bid") String bid) {
		int bidInt = Integer.parseInt(bid);
		
		Bid bidClass = em.find(Bid.class, bidInt);
		if (bidClass == null)
			throw new NotFoundException();
		
		return Response.ok(bidClass).build();
	}
	
	// TODO: Figure this shit out.
	@POST
	@Path("auctions/{id}/bids")
	public Response setAuctionBid(@PathParam("id") String id, Bid bid) {
		int idInt = Integer.parseInt(id);
		
		Auction auction = em.find(Auction.class, idInt);
		if (auction == null)
			throw new NotFoundException();
		
		em.persist(bid);
		
		return Response.ok().build();
	}
}
