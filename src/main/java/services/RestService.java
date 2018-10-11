package services;

import beans.AuctionDao;
import beans.UserDao;
import com.google.common.collect.Iterables;
import entities.Auction;
import entities.Bid;
import entities.User;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Path("")
@Stateless
public class RestService extends Application {

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private UserDao userDao;

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
        return Response.ok(auctionDao.find(id).getBids()).build();
    }

    @GET
    @Path("/auctions/{id}/bids/{bid}")
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
    @Path("/auctions/{id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeBid(@PathParam("id") int id,
                             @HeaderParam("amount") double amount,
                             @HeaderParam("user-id") int userID) {
        Auction auction = auctionDao.find(id);

        Bid bid = new Bid();
        bid.setUser(userDao.find(userID));
        bid.setAmount(amount);
        bid.setTime(LocalDateTime.now());

        auction.addBid(bid);
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
        User u = new User();
        u.setUserName(uName);
        u.setName(fName);
        u.setLastName(lName);
        u.setPassword(pwd);
        u.setEmail(email);
        u.setComments(new HashSet<>());
        u.setNumberOfRatings(0);
        u.setSumOfAllRatings(0);

        userDao.persist(u);
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
        Auction auction = new Auction();
        auction.setSeller(userDao.find(userID));
        auction.setLastBid(0);
        ArrayList<Bid> bids = new ArrayList<>();
        Bid bid = new Bid();
        bid.setAmount(0);
        bid.setUser(userDao.find(userID));
        bids.add(bid);
        auction.setBids(bids);
        auctionDao.persist(auction);
        return getMyAuctions(userID);
    }

    @GET
    @Path("/auctions/self/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyAuctions(@PathParam("id") int id) {
        List<Auction> auctions = new ArrayList<>();
        for (Auction auction : auctionDao.findAll()) {
            if (auction.getSeller().getId() == id) {
                auctions.add(auction);
            }
        }

        return Response.ok(auctions).build();
    }
}
