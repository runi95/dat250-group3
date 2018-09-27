package entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSSessionMode;
import javax.jms.Topic;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DAO {
	
	// Injected database connection:
	@PersistenceContext(unitName="group3")
	private EntityManager em;
	
	@Inject
	@JMSConnectionFactory("jms/dat250/ConnectionFactory")
	@JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
	private JMSContext context;
	
	@Resource(lookup = "jms/dat250/Topic")
	private Topic topic;
	
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
        Query query = em.createQuery("SELECT t FROM User u");
        List<User> users = new ArrayList<>();
        users = query.getResultList();
        return users;
    }
}
