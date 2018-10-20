package beans;

import com.google.common.collect.Iterables;
import entities.Bid;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BidDao extends AbstractDao<Bid> {

    public BidDao(){
        super(Bid.class);
    }

    public int getBidID(double amount, int uID){
        addBid(amount, uID);
        return Iterables.getLast(findAll()).getId();
    }

    public Bid getbid(int bID) {
        List<Bid> bid = new ArrayList<>();
        bid.add(find(bID));
        return bid.get(0);
    }

    private void addBid(double amount, int uID){
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setUserID(uID);
        bid.setTime(LocalDateTime.now());
        persist(bid);
    }

    public List<Bid> getBidsByAuctionID(List<Integer> bidIDs){
        ArrayList<Bid> bids = new ArrayList<>();
        for (Integer bidID : bidIDs) {
            bids.add(find(bidID));
        }

        return bids;
    }
}
