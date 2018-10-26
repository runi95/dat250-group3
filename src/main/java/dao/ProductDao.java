package dao;

import entities.Product;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
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
        product.setComments(new ArrayList<>());
        super.persist(product);
    }

    public Product findByName(String title) {
        /*
        TypedQuery<User> query = em.createNamedQuery("findUserById", User.class);
        query.setParameter("email", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }
        return user;
        */

        return null;
    }
}
