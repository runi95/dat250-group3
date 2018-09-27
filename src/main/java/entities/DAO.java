package entities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class DAO {
	
	@PersistenceContext()
	private EntityManager em;
	
	public TypedQuery<Auction> createNamedAuctionQuery() {
		return em.createNamedQuery(Auction.FIND_ALL, Auction.class);
	}
	
	public Auction findAuction(int id) {
		return em.find(Auction.class, id);
	}
	
	public TypedQuery<Bid> createNamedBidQuery() {
		return em.createNamedQuery(Bid.FIND_ALL, Bid.class);
	}
	
	public Bid findBid(int id) {
		return em.find(Bid.class, id);
	}

	public void persistBid(Bid bid) {
		em.persist(bid);
	}
	
	public void persistAuction(Auction auction) {
		em.persist(auction);
	}
	
	/**
	 * Persists a user into the DB
	 * 
	 * @param user
	 * @throws NamingException
	 * @throws JMSException
	 */
    public void persist(User user) throws NamingException, JMSException {
        em.persist(user);
    }
    
    /**
     * Gets all users from the DB
     * 
     * @return users
     */
    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> users = new ArrayList<>();
        users = query.getResultList();
        
        return users;
    }
}
