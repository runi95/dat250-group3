package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

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

	@Inject
	UserDao userDao;

	@Inject
    AuctionDao auctionDao;

	@Inject
    BidDao bidDao;

	@Inject
    CategoryDao categoryDao;

	@Inject
    CommentDao commentDao;

	@Inject
    ProductDao productDao;



	@PostConstruct
	public void createData() {
		Bid bid = createBid(9.0);
		bidDao.persist(bid);
		
		Bid bid2 = createBid(2.0);
		bidDao.persist(bid2);
		
		Product product = createProduct();
		productDao.persist(product);
		
		User seller = createUser();
		userDao.persist(seller);
		
		User buyer = createUser();
		userDao.persist(buyer);
		
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
		
		auctionDao.persist(auction);
	}

	private Bid createBid(double amount) {
		Bid bid = new Bid();
		bid.setAmount(amount);
		bid.setTime(LocalDateTime.now());
		bid.setUser(createUser());
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
		ArrayList<String> features = new ArrayList<>();
		features.add("feature1");
		features.add("feature2");
		
		return features;
	}
	
	private User createUser() {
		User user = new User();
		user.setName("First");
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
