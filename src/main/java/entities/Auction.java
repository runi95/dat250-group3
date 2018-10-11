package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
	
	private Set<Bid> bids = new HashSet<>();
	
	private User seller;

	public Auction() {

	}

	@OneToOne(optional=false)
    @JoinColumn(
        name="id", unique=true, nullable=false)
    public Product getProduct() { return this.product; }
	
	public void setProduct(Product product) { this.product = product; }
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="auction")
    public Set<Bid> getBids() { return this.bids; }
	
	public void setBids(Set<Bid> bids) { this.bids = bids; }
	
	public void addBid(Bid bid) { this.bids.add(bid); }
	
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
