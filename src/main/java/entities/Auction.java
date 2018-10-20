package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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
	
	private int productID;
	
	private List<Integer> bidIDs;
	
	private int sellerID;

	@Transient
	private Semaphore semaphore = new Semaphore(1);

	public Auction() {

	}

	@OneToOne(optional=false)
    @JoinColumn(name="id", unique=true, nullable=false)
    public int getProductID() { return this.productID; }
	
	public void setProductID(int productID) { this.productID = productID; }
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="auction")
    public List<Integer> getBidIDs() { return this.bidIDs; }
	
	public void setBidIDs(List<Integer> bids) { this.bidIDs = bids; }
	
	public void addBid(int bid) {
	    if (bidIDs == null){
	        bidIDs = new ArrayList<>();
	        setLastBid(0);
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

	    this.bidIDs.add(bid);

	    //if (bid.getAmount() > getLastBid()) setLastBid(bid.getAmount());
	}
	
	public int getId() { return this.id; }
	
	public double getLastBid() { return this.lastBid; }
	
	public void setLastBid(double lastBid) { this.lastBid = lastBid; }
	
	public LocalDateTime getEndTime() { return this.endTime; }
	
	public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
	
	public LocalDateTime getPublishedTime() { return this.published; }
	
	public void setPublishedTime(LocalDateTime published) { this.published = published; }

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int seller) {
		this.sellerID = seller;
	}
}
