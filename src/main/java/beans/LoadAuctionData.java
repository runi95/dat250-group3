package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.google.common.collect.Iterables;
import entities.Auction;
import entities.Bid;
import entities.Comment;
import entities.Product;
import entities.User;

@Singleton
@Startup
public class LoadAuctionData {

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
		List<Bid> bids = new ArrayList<>();

		User seller = createUser("Pål", "Pål", "Pål", "Pål", "Pål");
		userDao.persist(seller);
		seller = Iterables.getLast(userDao.findAll());

		User buyer = createUser("Per", "Per", "Per", "Per", "Per");
		userDao.persist(buyer);
		buyer = Iterables.getLast(userDao.findAll());

		Product product = createProduct("Useful item", 10);
		productDao.persist(product);
		product = Iterables.getLast(productDao.findAll());

		Bid bid = createBid(9.0);
		bid.setUserID(buyer.getId());
		bidDao.persist(bid);
		bids.add(bid);

		Bid bid2 = createBid(2.0);
		bid2.setUserID(buyer.getId());
		bidDao.persist(bid2);
		bids.add(bid2);

		Auction auction = createAuction(bids, product.getId(), seller);
		auctionDao.persist(auction);
	}

	private Auction createAuction(List<Bid> bids, int productID, User seller){
		Auction auction = new Auction();
		for (Bid b : bids){
			auction.addBid(b.getId());
		}

		auction.setPublishedTime(LocalDateTime.now());
		auction.setEndTime(LocalDateTime.now().plusHours(1));
		auction.setProductID(productID);
		auction.setSellerID(seller.getId());

		return auction;
	}

	private Bid createBid(double amount) {
		Bid bid = new Bid();
		bid.setAmount(amount);
		bid.setTime(LocalDateTime.now());
		return bid;
	}

	private Product createProduct(String name, double rating) {
		Product product = new Product();
		product.setName(name);
		product.setPublushedState(false);
		product.setRating(rating);
		product.setFeatures(createFeatures("f1", "f2"));
		product.setPicture(new byte[] {});
		product.setPictureAsBase64("base64");
		
		return product;
	}

	private ArrayList<String> createFeatures(String f1, String f2) {
		ArrayList<String> features = new ArrayList<>();
		features.add(f1);
		features.add(f2);
		
		return features;
	}
	
	private User createUser(String name, String lastName, String userName, String pwd, String email) {
		User user = new User();
		user.setName(name);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(pwd);
		user.setSumOfAllRatings(5.0);
		user.setEmail(email);
		user.setNumberOfRatings(1);

		return user;
	}

	private List<Comment> createComments(String c1, String c2) {
		List<Comment> lst = new ArrayList<>();
		lst.add(createComment(c1));
		lst.add(createComment(c2));

		return lst;
	}

	private Comment createComment(String c) {
		Comment comment = new Comment();
		comment.setCreated(LocalDateTime.now());
		comment.setRating(3.5);
		comment.setText(c);
		commentDao.persist(comment);
		
		return comment;
	}
}
