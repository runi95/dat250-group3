package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import entities.Auction;
import entities.Bid;
import entities.Comment;
import entities.DAO;
import entities.Product;
import entities.User;

@Singleton
@Startup
public class LoadAuctionData {
	
	@EJB
	DAO dao;
	
	@PostConstruct
	public void createData() {
		Bid bid = createBid(1.0);
		dao.persistBid(bid);
		
		Bid bid2 = createBid(2.0);
		dao.persistBid(bid2);
		
		Product product = createProduct();
		dao.persistProduct(product);
		
		User seller = createUser(1);
		dao.persist(seller);
		
		User buyer = createUser(2);
		dao.persist(buyer);
		
		bid.setUser(buyer);
		bid2.setUser(buyer);
		Auction auction = new Auction();
		auction.addBid(bid);
		auction.addBid(bid2);
		auction.setPublishedTime(LocalDateTime.now());
		auction.setEndTime(LocalDateTime.now().plusHours(1));
		auction.setLastBid(bid2.getAmount());
		auction.setProduct(product);
		auction.setSeller(seller);
		
		dao.persistAuction(auction);
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
		product.setPicture(new byte[] {});
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
		user.setName("FirstName");
		user.setLastName("LastName");
		user.setUserName("userName");
		user.setPassword("password");
		user.setSumOfAllRatings(5.0);
		user.setEmail("emailAdress");
		user.setNumberOfRatings(1);
		user.setComments(createComments());
		
		return user;
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
		// comment.setProduct(createProduct());
		comment.setRating(3.5);
		comment.setText("CommentText");
		dao.persistComment(comment);
		
		return comment;
	}
}
