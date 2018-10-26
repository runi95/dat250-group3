package beans;

import dao.AuctionDao;
import dao.BidDao;
import dao.ProductDao;
import dao.UserEJB;
import entities.Auction;
import entities.Bid;
import entities.Product;
import entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SessionScoped
@ManagedBean
public class CreateAuctionView implements Serializable {
    private static final long serialVersionUID = 6554462262408979835L;

    private String title;
    private Date endTime;
    private Part file;
    private byte[] pictureByteArray;

    @Inject
    UserEJB userDao;

    @Inject
    ProductDao productDao;

    @Inject
    AuctionDao auctionDao;

    @Inject
    BidDao bidDao;

    public CreateAuctionView() {
    }

    public String createNewAuction() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();

        User user = userDao.findUserById(principal.getName());

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            pictureByteArray = new byte[inputStream.available()];
            inputStream.read(pictureByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = new Product();
        product.setName(title);
        product.setPicture(pictureByteArray);

        productDao.persist(product);

        Auction auction = new Auction();
        auction.setProduct(product);
        auction.setSeller(user);
        auction.setPublishedTime(LocalDateTime.now());
        auction.setEndTime(endTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        Bid bid = new Bid();
        bid.setAmount(0.0f);
        bid.setTime(LocalDateTime.now());
        bid.setUser(user);

        bidDao.persist(bid);
        auction.setHighestBid(bid);

        auctionDao.persist(auction);

        return "auctions";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
