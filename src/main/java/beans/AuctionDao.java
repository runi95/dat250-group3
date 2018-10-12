package beans;

import entities.Auction;
import entities.Product;

import javax.ejb.Stateless;

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
}
