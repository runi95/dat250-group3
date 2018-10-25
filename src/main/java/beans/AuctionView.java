package beans;

import dao.AuctionDao;
import dao.BidDao;
import dao.UserDao;
import dao.UserEJB;
import entities.Auction;
import entities.Bid;
import entities.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
public class AuctionView implements Serializable {
    private static final long serialVersionUID = 5443351151396868724L;

    private static Logger log = Logger.getLogger(AuctionView.class.getName());

    private Auction auction;

    private double bid;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private BidDao bidDao;

    @Inject
    private UserEJB userDao;

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
        auction.addBid(bid);
        auction.setLastBid(bid.getAmount());
        auctionDao.edit(auction);
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }
}
