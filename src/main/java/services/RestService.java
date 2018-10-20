package services;

import beans.AuctionDao;
import beans.BidDao;
import beans.ProductDao;
import beans.UserDao;
import com.google.common.collect.Iterables;
import entities.Auction;
import entities.Bid;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("")
@Stateless
public class RestService extends Application {

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private UserDao userDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private BidDao bidDao;

    @GET
    @Path("/auctions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuctions(){
        return Response.ok(auctionDao.findAll()).build();
    }

    @GET
    @Path("/auctions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuction(@PathParam("id") int id) {
        return Response.ok(auctionDao.find(id)).build();
    }

    @GET
    @Path("/auctions/{id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBids(@PathParam("id") int id){
        Auction auction = auctionDao.find(id);

        return Response.ok(bidDao.getBidsByAuctionID(auction.getBidIDs())).build();
    }

    @GET
    @Path("/auctions/{id}/bids/{bid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBid(@PathParam("id") int id, @PathParam("bid") int bid) {
        Response res = null;
        List<Bid> bids = bidDao.getBidsByAuctionID(auctionDao.find(id).getBidIDs());
        for (Bid b : bids) {
            if (b.getId() == bid)  {
                res = Response.ok(b).build();
            }
        }
        return res;
        //return Response.ok(bidDao.getbid(bid)).build();
    }

    @POST
    @Path("/auctions/{id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeBid(@PathParam("id") int id,
                             @HeaderParam("amount") double amount,
                             @HeaderParam("user-id") int userID) {

        Auction auction = auctionDao.find(id);
        int bidID = bidDao.getBidID(amount, userID);
        auction.addBid(bidID);
        auctionDao.edit(auction);

        return getBids(id);
    }

    @POST
    @Path("/user/createuser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@HeaderParam("u_name") String uName,
                               @HeaderParam("f_name") String fName,
                               @HeaderParam("l_name") String lName,
                               @HeaderParam("pwd") String pwd,
                               @HeaderParam("email") String email) {
        userDao.createUser(uName, fName, lName, pwd, email);
        return Response.ok(Iterables.getLast(userDao.findAll())).build();
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        return Response.ok(userDao.find(id)).build();
    }

    @POST
    @Path("/auctions/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuction(@HeaderParam("user_id") int userID) {
        auctionDao.createAuction(userID);
        return getMyAuctions(userID);
    }

    @POST
    @Path("/product/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam("name") String name) {
        productDao.createProduct(name);
        return Response.ok(Iterables.getLast(productDao.findAll())).build();
    }

    @POST
    @Path("/auction/{id}/addproduct/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProductToAuction(@PathParam("id") int id, @PathParam("pid") int pid) {
        auctionDao.addProduct(id, pid);
        return Response.ok(auctionDao.find(id)).build();
    }


    @GET
    @Path("/auctions/self/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyAuctions(@PathParam("id") int id) {
        List<Auction> auctions = new ArrayList<>();
        for (Auction auction : auctionDao.findAll()) {
            if (auction.getSellerID() == id) {
                auctions.add(auction);
            }
        }

        return Response.ok(auctions).build();
    }
}
