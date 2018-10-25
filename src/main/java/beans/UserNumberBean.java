package beans;

import dao.AuctionDao;
import entities.Auction;
import entities.Bid;
import entities.Product;
import entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;

@ManagedBean
@SessionScoped
public class UserNumberBean implements Serializable {
    private static final long serialVersionUID = 5443351151396868724L;
    private Auction auction;

    @Inject
    AuctionDao auctionDao;

    public UserNumberBean() {
        User seller = new User()
                ;
        seller.setName("Kari");
        seller.setLastName("Nordmann");

        User buyer = new User();
        buyer.setName("Ola");
        buyer.setLastName("Svenson");

        Bid bid = new Bid();
        bid.setAmount(100.0f);
        bid.setTime(LocalDateTime.now());
        bid.setUser(buyer);

        Product product = new Product();
        product.setName("Fifty Shades of Software Development");

        auction = new Auction();
        auction.setPublishedTime(LocalDateTime.now());
        auction.setEndTime(LocalDateTime.now().plusHours(12));
        auction.setSeller(seller);
        auction.addBid(bid);
        auction.setProduct(product);
    }

    public Auction getAuction() { return this.auction; }

    public void setauction(Auction auction) { this.auction = auction; }
}