package dao;

import entities.Auction;
import entities.Product;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuctionDao extends AbstractDao<Auction> {

    public AuctionDao() {
        super(Auction.class);
    }

    public void addProduct(int id, Product product) {
        Auction a = find(id);
        a.setProduct(product);
        edit(a);
    }

    public List<Auction> findAllPublishedAuctions() {
        List<Auction> auctions = findAll();
        List<Auction> publishedAuctions = new ArrayList<>();

        auctions.forEach(x -> {if (x.getProduct().isPublished()) publishedAuctions.add(x);});
        return publishedAuctions;
    }

    public List<Auction> findAllOpenAuctions() {
        List<Auction> auctions = findAll();
        List<Auction> filteredAuctionsBecauseWeDontWantToWriteQueries = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        auctions.forEach(x -> {if(x.getEndTime().isAfter(now)) filteredAuctionsBecauseWeDontWantToWriteQueries.add(x); });

        return filteredAuctionsBecauseWeDontWantToWriteQueries;
    }
}
