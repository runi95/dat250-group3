package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "comment")
@XmlRootElement
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Double rating;
	
	private String text;
	
	private LocalDateTime created;
	
	private Product product;

	public Comment(){

	}

	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Product getProduct() { return this.product; }
	
	public void setProduct(Product product) { this.product = product; }
	
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	public Double getRating() { return this.rating; }
	
	public void setRating(double rating) { this.rating = rating; }
	
	public String getText() { return this.text; }
	
	public void setText(String text) { this.text = text; }
	
	public LocalDateTime getCreated() { return this.created; }
	
	public void setCreated(LocalDateTime created) { this.created = created; }
}
