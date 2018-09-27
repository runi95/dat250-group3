package entities; 
 
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table; 


@Entity 
@Table(name = "user") 
public class User { 

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id; 
     
    private String name; 
    private String lastName; 
    private String userName; 
    private String password; 
    private String email; 
    private double sumOfAllRatings; 
    private int numberOfRatings; 
    private Set<Comment> comments;
    private Set<Auction> auctions;
     
    public User(String name, String lastName, String userName, String password, String email) {
    	this.name = name;
    	this.lastName = lastName;
    	this.userName = userName;
    	this.password = password;
    	this.email = email;
    	this.sumOfAllRatings = 0.0;
    	this.numberOfRatings = 0;
    	this.comments = new HashSet<Comment>();
    	this.auctions = new HashSet<Auction>();
    }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="product")
    public Set<Comment> getComments() { return this.comments; }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="product")
    public Set<Auction> getAuctions() { return this.auctions; }
    
    public int getId() {
    	return this.id;
    }
    
    public int getNumberOfRatings() { 
        return numberOfRatings; 
    } 

    public void addRating(double rating) { 
        this.sumOfAllRatings += rating; 
        this.numberOfRatings += 1; 
    } 

    public double getRank() { 
        return this.sumOfAllRatings / this.numberOfRatings; 
    } 

    public String getName() { 
        return name; 
    } 

    public String getLastName() { 
        return lastName; 
    } 

    public String getUserName() { 
        return userName; 
    } 

    public String getPassword() { 
        return password; 
    } 

    public String getEmail() { 
        return email; 
    } 

} 
