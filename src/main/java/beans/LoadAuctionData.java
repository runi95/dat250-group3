package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Auction;
import entities.Bid;
import entities.Comment;
import entities.Product;
import entities.User;

@Singleton
@Startup
public class LoadAuctionData {
	
	@PersistenceContext(unitName = "group3")
	private EntityManager em;
	
	@PostConstruct
	public void createData() {
		Bid bid = createBid(1.0);
		Bid bid2 = createBid(2.0);
		Product product = createProduct();
		User buyer = createUser(2);
		bid.setUser(buyer);
		bid2.setUser(buyer);
		Auction auction = new Auction();
		auction.addBid(bid);
		auction.addBid(bid2);
		auction.setPublishedTime(LocalDateTime.now());
		auction.setEndTime(LocalDateTime.now().plusHours(1));
		auction.setLastBid(bid2.getAmount());
		auction.setProduct(product);
		
		em.persist(auction);
	}

	private Bid createBid(double amount) {
		Bid bid = new Bid();
		bid.setAmount(amount);
		bid.setTime(LocalDateTime.now());
		bid.setUser(createUser(2));
		return bid;
	}

	private Product createProduct() {
		Product product = new Product();
		product.setName("productName");
		product.setPublushedState(false);
		product.setRating(1.337);
		product.setFeatures(createFeatures());
		product.setPicture(null);
		product.setPictureAsBase64("base64");
		return product;
	}

	private ArrayList<String> createFeatures() {
		ArrayList<String> features = new ArrayList<String>();
		features.add("feature1");
		features.add("feature2");
		return features;
	}
	
	private User createUser(int id) {
		User user = new User();
		user.setId(id);
		user.setName("FirstName");
		user.setLastName("LastName");
		user.setUserName("userName");
		user.setPassword("password");
		user.setSumOfAllRatings(5.0);
		user.setEmail("emailAdress");
		user.setNumberOfRatings(1);
		user.setComments(createComments());
		user.setAuctions(createAuchtions());
		return null;
	}

	private Set<Auction> createAuchtions() {
		Set<Auction> set = new HashSet<Auction>();
		set.add(createAuction(2));
		set.add(createAuction(3));
		return set;
	}

	private Auction createAuction(int i) {
		Auction auction = new Auction();
		auction.setProduct(createProduct());
		auction.setPublishedTime(LocalDateTime.now());
		auction.setEndTime(LocalDateTime.now().plusHours(1));
		auction.setBids(createBids());
		return null;
	}

	private Set<Bid> createBids() {
		Set<Bid> set = new HashSet<>();
		set.add(createBid(3.0));
		set.add(createBid(4.5));
		return set;
	}

	private Set<Comment> createComments() {
		Set<Comment> set = new HashSet<Comment>();
		set.add(createComment("comment1"));
		set.add(createComment("comment2"));
		return set;
	}

	private Comment createComment(String string) {
		Comment comment = new Comment();
		comment.setCreated(LocalDateTime.now());
		comment.setProduct(createProduct());
		comment.setRating(3.5);
		comment.setText("CommentText");
		return comment;
	}
	
	
}
