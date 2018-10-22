package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQuery(name="Auction.findAll", query="SELECT a FROM Auction a")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Auction.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Double lastBid;
	
	private LocalDateTime endTime;
	
	private LocalDateTime published;
	
	private Product product;
	
	private List<Bid> bids;
	
	private User seller;

	@Transient
	private Semaphore semaphore = new Semaphore(1);

	public Auction() {

	}

	@OneToOne(optional=false)
    @JoinColumn(
        name="id", unique=true, nullable=false)
    public Product getProduct() { return this.product; }
	
	public void setProduct(Product product) { this.product = product; }
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="auction")
    public List<Bid> getBids() { return this.bids; }
	
	public void setBids(List<Bid> bids) { this.bids = bids; }
	
	public void addBid(Bid bid) {
		if (bids == null) {
			lastBid = 0.0;
			bids = new ArrayList<>();
		}

//	    try {
//            semaphore.acquire();
//            if (bid.getAmount() > lastBid) {
//                setLastBid(bid.getAmount());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        semaphore.release();

	    this.bids.add(bid);
	}
	
	public int getId() { return this.id; }
	
	public double getLastBid() { return this.lastBid; }
	
	public void setLastBid(double lastBid) { this.lastBid = lastBid; }
	
	public LocalDateTime getEndTime() { return this.endTime; }
	
	public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
	
	public LocalDateTime getPublishedTime() { return this.published; }
	
	public void setPublishedTime(LocalDateTime published) { this.published = published; }

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
}
