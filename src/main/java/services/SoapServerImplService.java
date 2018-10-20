package services;

import beans.AuctionDao;
import beans.UserDao;
import entities.Auction;
import entities.Bid;

import javax.inject.Inject;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.List;

@WebService(endpointInterface = "services.SoapServer")
public class SoapServerImplService implements SoapServer {

    @Inject
    UserDao userDao;

    @Inject
    AuctionDao auctionDao;

//    public Auction getAuctionById(int id) {
//        return auctionDao.find(id);
//    }

    public Auction[] getOpenAuctions() {
        List<Auction> openAuctions = auctionDao.findAllOpenAuctions();
        return openAuctions.toArray(new Auction[openAuctions.size()]);
    }

    public double bidOnAuction(int id, int userID, int amount) {
        Auction auction = auctionDao.find(id);

        Bid bid = new Bid();
        bid.setUserID(userID);
        bid.setAmount(amount);
        bid.setTime(LocalDateTime.now());

        auction.addBid(bid.getId());
        auctionDao.edit(auction);

        return auction.getLastBid();
    }
}