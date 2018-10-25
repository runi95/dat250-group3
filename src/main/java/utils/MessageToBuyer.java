package utils;

import entities.Bid;
import entities.User;

public class MessageToBuyer {

    private User buyer;
    private User seller;
    private Bid winningBid;
    private String message;

    public MessageToBuyer(User buyer, User seller, Bid winningBid, String message) {
        this.buyer = buyer;
        this.seller = seller;
        this.winningBid = winningBid;
        this.message = message;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
