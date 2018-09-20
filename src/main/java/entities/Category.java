package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	private int id;
	
	private String name;
	
	private Product product;
	
	private Set<Category> subCategories;
	
	private Set<Category> superCategories;
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Product getProduct() { return this.product; }
	
	@ManyToMany
    @JoinTable(name="id")
    public Set<Category> getSubcategories() { return this.subCategories; }
	
	@ManyToMany(mappedBy="superCategories")
    public Set<Category> getSuperCategories() { return this.superCategories; }
	
	public int getId() { return this.id; }
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
}
