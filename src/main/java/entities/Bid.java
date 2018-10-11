package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Bid.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double amount;
	
	private LocalDateTime time;
	
	private Auction auction;
	
	private User user;

	public Bid() {

	}

	public Bid(double amount){
		this.amount = amount;
	}

	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Auction getAuction() { return this.auction; }
	
	public void setAuction(Auction auction) { this.auction = auction; }
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public User getUser() { return this.user; }
	
	public void setUser(User user) { this.user = user; }
	
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	public double getAmount() { return this.amount; }
	
	public void setAmount(double amount) { this.amount = amount; }
	
	public LocalDateTime getTime() { return this.time; }
	
	public void setTime(LocalDateTime time) { this.time = time; }
}
