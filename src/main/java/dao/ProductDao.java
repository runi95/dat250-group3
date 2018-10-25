package dao;

import entities.Product;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;

@Stateless
public class ProductDao extends AbstractDao<Product> {

    public ProductDao() {
        super(Product.class);
    }

    public void createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setPicture(new byte[] {});
        product.setPictureAsBase64("");
        product.setRating(0);
        product.setPublushedState(false);
        product.setFeatures(new ArrayList<>());
        product.setCategories(new HashSet<>());
        product.setComments(new HashSet<>());
        super.persist(product);
    }
}
