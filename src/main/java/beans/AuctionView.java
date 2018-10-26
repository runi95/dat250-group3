package beans;

import dao.*;
import entities.Auction;
import entities.Bid;
import entities.Comment;
import entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
public class AuctionView implements Serializable {
    private static final long serialVersionUID = 5443351151396868724L;

    private static Logger log = Logger.getLogger(AuctionView.class.getName());

    private Auction auction;

    private double bid;

    private String comment;

    private double rating;

    private String srch;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private BidDao bidDao;

    @Inject
    private UserEJB userDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private CommentDao commentDao;

    public AuctionView() {}

    public List<Auction> getOpenAuctions() {
        return auctionDao.findAllOpenAuctions();
    }

    public int getAuctionSize() {
        return auctionDao.findAll().size();
    }

    public String viewAuction() {
        return "auction";
    }

    public String chooseAuction(int id) {
        auction = auctionDao.find(id);

        return "auction";
    }

    public Auction getAuction() {
        return auction;
    }

    public void placeBid() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();

        User user = userDao.findUserById(principal.getName());

        Bid bid = new Bid();
        bid.setAmount(this.bid);
        bid.setTime(LocalDateTime.now());
        bid.setUser(user);

        bidDao.persist(bid);
        auction.setHighestBid(bid);
        auctionDao.edit(auction);
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public String post() {
        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setText(this.comment);

        if (this.rating > 0) {
            comment.setRating(this.rating);
        }

        auction.getProduct().addComment(comment);

        commentDao.persist(comment);
        productDao.edit(auction.getProduct());

        return "auction";
    }

    public String getSrch() { return srch; }

    public void setSrch(String srch) { this.srch = srch; }

    public String getSearch() {
        return "auctionsearch";
    }

    public void getSearchResults() {
        productDao.findByName(srch);
    }
}
