package dao;

import entities.Bid;

import javax.ejb.Stateless;

@Stateless
public class BidDao extends AbstractDao<Bid> {

    public BidDao(){
        super(Bid.class);
    }

}
