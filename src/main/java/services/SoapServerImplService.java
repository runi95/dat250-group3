package services;

import beans.AuctionDao;
import beans.BidDao;
import entities.Auction;
import entities.Bid;

import javax.inject.Inject;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(endpointInterface = "services.SoapServer")
public class SoapServerImplService implements SoapServer {

    @Inject
    AuctionDao auctionDao;

    @Inject
    BidDao bidDao;

    public Auction getAuctionById(int id) {
        return auctionDao.find(id);
    }

    public Auction[] getOpenAuctions() {
        return (Auction[]) auctionDao.findAll().toArray();
    }

    public double bidOnAuction(int id, int amount) {
        Auction auction = auctionDao.find(id);

        Bid bid = new Bid();
        // bid.setUser(userDao.find(userID));
        bid.setAmount(amount);
        bid.setTime(LocalDateTime.now());

        auction.addBid(bid);
        auctionDao.edit(auction);

        return bid.getAmount();
    }
}