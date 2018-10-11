package services;

import beans.AuctionDao;
import entities.Auction;
import entities.Bid;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/auctions")
@Stateless
public class TestRest extends Application {

    @Inject
    private AuctionDao auctionDao;

    @Context
    UriInfo uri;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuctions(){
        return Response.ok(auctionDao.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuction(@PathParam("id") int id) {
        return Response.ok(auctionDao.find(id)).build();
    }

    @GET
    @Path("{id}/bids/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBids(@PathParam("id") int id){
        return Response.ok(auctionDao.find(id).getBids()).build();
    }

    @GET
    @Path("{id}/bids/{bid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBid(@PathParam("id") int id, @PathParam("bid") int bid) {
        Response res = null;
        List<Bid> bids = auctionDao.find(id).getBids();
        for (Bid b : bids) {
            if (b.getId() == bid)  {
                res = Response.ok(b).build();
            }
        }
        return res;
    }

    @POST
    @Path("{id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeBid(@PathParam("id") int id, int value) {
        Auction auction = auctionDao.find(id);
        Bid bid = new Bid(value);
        auction.addBid(bid);
        URI bidUri = UriBuilder.fromUri(uri.getBaseUri()).path("auctions").path(Integer.toString(id)).path("bids").path(Integer.toString(bid.getId())).build();
        return Response.created(bidUri).entity(bid).build();
    }
}
