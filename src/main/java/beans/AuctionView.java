package beans;

import dao.AuctionDao;
import dao.UserEJB;
import entities.Auction;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
public class AuctionView implements Serializable {
    private static final long serialVersionUID = 5443351151396868724L;

    private static Logger log = Logger.getLogger(AuctionView.class.getName());

    @Inject
    private AuctionDao auctionDao;

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

    public String chooseAuction() {
        return "auction";
    }
}
