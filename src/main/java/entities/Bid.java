package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bid")
public class Bid {

	@Id
	@GeneratedValue
	private int id;
	
	private Double amount;
	
	private LocalDateTime time;
	
	private Auction auction;
	
	private User user;
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Auction getAuction() { return this.auction; }
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Auction getUser() { return this.user; }
	
	public int getId() { return this.id; }
	
	public double getAmount() { return this.amount; }
	
	public void setAmount(double amount) { this.amount = amount; }
	
	public LocalDateTime getTime() { return this.time; }
	
	public void setTime(LocalDateTime time) { this.time = time; }
}
