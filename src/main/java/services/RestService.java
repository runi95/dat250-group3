package services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import entities.DAO;


/*@Path("/rest")
@Stateless
public class RestService {
	
	@EJB
	DAO dao;
	
	@GET
	@Path("auctions")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAuctions() {
		TypedQuery<Auction> query = dao.createNamedAuctionQuery();
		Auctions auctions = new Auctions(query.getResultList());
		
		return Response.ok(auctions).build();
	}

	@GET
	@Path("auctions/{id}")
	public Response getAuction(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		
		Auction auction = dao.findAuction(idInt);
		if (auction == null)
			throw new NotFoundException();
		
		return Response.ok(auction).build();
	}
	
	@GET
	@Path("auctions/{id}/bids")
	public Response getAuctionBids(@PathParam("id") String id) {
		TypedQuery<Bid> query = dao.createNamedBidQuery();
		Bids bids  = new Bids(query.getResultList());
		
		return Response.ok(bids).build();
	}
	
	@GET
	@Path("auctions/{aid}/bids/{bid}")
	public Response getAuctionBid(@PathParam("aid") String aid, @PathParam("bid") String bid) {
		int bidInt = Integer.parseInt(bid);
		
		Bid bidClass = dao.findBid(bidInt);
		if (bidClass == null)
			throw new NotFoundException();
		
		return Response.ok(bidClass).build();
	}
	
	// TODO: Figure this shit out.
	@POST
	@Path("auctions/{id}/bids")
	public Response setAuctionBid(@PathParam("id") String id, Bid bid) {
		int idInt = Integer.parseInt(id);
		
		Auction auction = dao.findAuction(idInt);
		if (auction == null)
			throw new NotFoundException();
		
		dao.persistBid(bid);
		
		return Response.ok().build();
	}
}*/
