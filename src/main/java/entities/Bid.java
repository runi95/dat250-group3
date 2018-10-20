package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
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

	private int userID;

	public Bid() {

	}

	@OneToMany
    @JoinColumn(name="id", nullable=false)
    public int getUserID() { return this.userID; }
	
	public void setUserID(int userID) { this.userID = userID; }
	
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	public double getAmount() { return this.amount; }
	
	public void setAmount(double amount) { this.amount = amount; }
	
	public LocalDateTime getTime() { return this.time; }
	
	public void setTime(LocalDateTime time) { this.time = time; }

}
