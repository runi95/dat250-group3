package beans;

import entities.Auction;

import javax.ejb.Stateless;

@Stateless
public class AuctionDao extends AbstractDao<Auction> {

    public AuctionDao() {
        super(Auction.class);
    }
}
