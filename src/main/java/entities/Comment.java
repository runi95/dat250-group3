package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private Double rating;
	
	private String text;
	
	private LocalDateTime created;
	
	private Product product;
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Product getProduct() { return this.product; }
	
	public int getId() { return this.id; }
	
	public double getRating() { return this.rating; }
	
	public void setRating(double rating) { this.rating = rating; }
	
	public String getText() { return this.text; }
	
	public void setText(String text) { this.text = text; }
	
	public LocalDateTime getCreated() { return this.created; }
	
	public void setCreated(LocalDateTime created) { this.created = created; }
}
