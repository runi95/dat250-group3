package entities; 
 
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity 
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.email = :email")
})
@Table(name = "user_")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="email", nullable=false, unique = true)
    private String email;

    @Column(name="name", nullable=false, length=30)
    private String name;

    @Column(name="lastName", nullable=false, length=30)
    private String lastName;

    @Column(name="userName", nullable=false, length=30)
    private String userName;

    @Column(name="password", nullable=false, length=64)
    private String password;

    @Column(name = "rating")
    private double rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private double sumOfAllRatings;

    private int numberOfRatings;

    private Set<Comment> comments;

    public User() {

    }

    public User(String name, String lastName, String userName, String password, String email) {
    	this.name = name;
    	this.lastName = lastName;
    	this.userName = userName;
    	this.password = password;
    	this.email = email;
    	this.comments = new HashSet<Comment>();
    }

    @OneToMany(mappedBy="product")
    public Set<Comment> getComments() { return this.comments; }
    
    public int getNumberOfRatings() { 
        return numberOfRatings; 
    } 

    public void addRating(double rating) { 
        this.sumOfAllRatings += rating; 
        this.numberOfRatings += 1; 
    }

    public void setRating(){
        this.rating = this.sumOfAllRatings / this.numberOfRatings;;
    }

    public double getRating() {
        return rating;
    } 

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSumOfAllRatings(double sumOfAllRatings) {
		this.sumOfAllRatings = sumOfAllRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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
