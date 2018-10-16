package beans;

import entities.Auction;
import entities.Bid;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuctionDao extends AbstractDao<Auction> {

    public AuctionDao() {
        super(Auction.class);
    }

    public void addProduct(int id, int product) {
        Auction a = find(id);
        a.setProductID(product);
        edit(a);
    }

    public List<Auction> findAllOpenAuctions() {
        List<Auction> auctions = findAll();
        List<Auction> filteredAuctionsBecauseWeDontWantToWriteQueries = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        auctions.forEach(x -> {if(x.getEndTime().isAfter(now)) filteredAuctionsBecauseWeDontWantToWriteQueries.add(x); });

        return filteredAuctionsBecauseWeDontWantToWriteQueries;
    }

    public void createAuction(int user) {
        Auction auction = new Auction();
        auction.setSellerID(user);
        auction.setLastBid(0);
        ArrayList<Bid> bids = new ArrayList<>();
        Bid bid = new Bid();
        bid.setAmount(0);
        bid.setUserID(user);
        bids.add(bid);
        auction.setBids(bids);
        persist(auction);
    }

    public void addBid(int userID) {

    }
}
