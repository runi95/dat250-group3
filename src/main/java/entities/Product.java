package entities;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String pictureAsBase64;
	
	private ArrayList<String> features;
	
	private Double rating;
	
	private Boolean isPublished;
	
	private Auction auction;
	
	private Set<Comment> comments;
	
	private Set<Category> categories;
	
	@OneToOne(optional=false, mappedBy="product")
    public Auction getAuction() { return this.auction; }
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
    public Set<Comment> getComments() { return this.comments; }
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
    public Set<Category> getOrders() { return this.categories; }
	
	public int getId() { return this.id; }
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getPictureAsBase64() { return this.pictureAsBase64; }
	
	public void setPictureAsBase64(String pictureAsBase64) { this.pictureAsBase64 = pictureAsBase64; }
	
	public void setPicture(byte[] picture) {
		this.pictureAsBase64 = Base64.getEncoder().encodeToString(picture);
	}
	
	public ArrayList<String> getFeatures() { return this.features; }
	
	public void setFeatures(ArrayList<String> features) { this.features = features; }
	
	public void addFeature(String feature) { this.features.add(feature); }
	
	public Double getRating() { return this.rating; }
	
	public void setRating(double rating) { this.rating = rating; }
	
	public Boolean isPublished() { return this.isPublished; }
	
	public void setPublushedState(boolean isPublished) { this.isPublished = isPublished; }
}
