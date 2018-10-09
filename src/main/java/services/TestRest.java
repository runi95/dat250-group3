package services;

import beans.AuctionDao;
import entities.Auction;

import javax.ejb.Stateless;
import javax.inject.Inject;import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auctions")
@Stateless
public class TestRest extends Application {

    @Inject
    private AuctionDao auctionDao;

    @POST
    @Path("/{id}")
    public Response save(@Valid Auction auction) {
        this.auctionDao.persist(auction);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Auction auction = this.auctionDao.find(id);
        return Response.ok(auction).build();
    }
}
