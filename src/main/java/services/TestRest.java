package services;

import beans.AuctionDao;
import entities.Auction;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auctions")
@Stateless
public class TestRest extends Application {

    @Inject
    private AuctionDao auctionDao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuction(@PathParam("id") int id) {
        Auction auction = auctionDao.find(id);
        return Response.ok(auction).build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Auction getAuction() {
        return new Auction();
    }

    @GET
    @Path("/hei")
    public Response hei(){
        return Response.ok("halla").build();
    }

}
