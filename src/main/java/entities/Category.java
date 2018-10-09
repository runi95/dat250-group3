package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "category")
@XmlRootElement
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private Product product;
	
	private Set<Category> subCategories;
	
	private Set<Category> superCategories;
	
	@ManyToOne
    @JoinColumn(name="id", nullable=false)
    public Product getProduct() { return this.product; }
	
	public void setProduct(Product product) { this.product = product; }
	
	@ManyToMany
    @JoinTable(name="id")
    public Set<Category> getSubcategories() { return this.subCategories; }
	
	public void setSubcategories(Set<Category> subCategories) { this.subCategories = subCategories; }
	
	public void addSubCategory(Category subCategory) { this.subCategories.add(subCategory); }
	
	@ManyToMany(mappedBy="superCategories")
    public Set<Category> getSuperCategories() { return this.superCategories; }
	
	public void setSuperCategories(Set<Category> superCategories) { this.superCategories = superCategories; }
	
	public void addSuperCategory(Category superCategory) { this.superCategories.add(superCategory); }
	
	public int getId() { return this.id; }
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
}
