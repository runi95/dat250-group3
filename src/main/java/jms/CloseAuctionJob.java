package jms;

//import beans.AuctionDao;
//import beans.BidDao;
import entities.Auction;
import entities.Bid;
import entities.Product;
import entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.naming.*;
import javax.jms.*;

public class CloseAuctionJob {

//    @EJB
//    static AuctionDao auctionDao;
//
//    @EJB
//    static BidDao bidDao;
//
//    public static void main(String[] args) {
//        while (true) {
//            List<Auction> auctions = auctionDao.findAllPublishedAuctions();
//            LocalDateTime now = LocalDateTime.now();
//            List<Auction> auctionsToClose = new ArrayList<>();
//
//            auctions.forEach(x -> {if (x.getEndTime().isBefore(now)) auctionsToClose.add(x); });
//
//            closeAuctions(auctionsToClose);
//            notifyHighestBidsWhenClosed(auctionsToClose);
//
//            try { Thread.sleep(1000); } catch (Exception e) { }
//
//         }
//    }
//
//    private static void closeAuctions(List<Auction> auctionsToClose) {
//        for (Auction auction : auctionsToClose) {
//            auction.getProduct().setPublushedState(false);
//            auctionDao.edit(auction);
//        }
//    }
//
//    private static void notifyHighestBidsWhenClosed(List<Auction> auctions) {
//        for (Auction auction : auctions) {
//            buildAndSendDweet(auction);
//        }
//    }
//
//    private static void buildAndSendDweet(Auction auction) {
//        try {
//            Properties props = new Properties();
//            Context ctx = new InitialContext(props);
//            TopicConnectionFactory f = (TopicConnectionFactory) ctx.lookup("myTopicConnectionFactory");
//            TopicConnection con = f.createTopicConnection();
//            con.start();
//
//            TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//            Topic t = (Topic) ctx.lookup(auction.getHighestBid().getUser().getUserName());
//            TopicPublisher publisher = ses.createPublisher(t);
//
//            Bid bid = auction.getHighestBid();
//            User seller = auction.getSeller();
//            User buyer = bid.getUser();
//            Product product = auction.getProduct();
//
//            if (auction.getHighestBid().getAmount() != 0) {
//                MapMessage msg = ses.createMapMessage();
//                msg.setString("product_name", product.getName());
//                msg.setString("url", "https://localhost:8080/g2018_03/rest/auctions/" + auction.getId());
//                msg.setString("seller_user_name", seller.getUserName());
//                msg.setString("buyer_user_name", buyer.getUserName());
//                msg.setDouble("amount", bid.getAmount());
//
//                publisher.publish(msg);
//            }
//
//        } catch(JMSException | NamingException e){
//            System.out.println("FATAL ERROR CAUSED RECURRENT JOB TO STOP: " + e.getMessage());
//        }
//
//    }

}
