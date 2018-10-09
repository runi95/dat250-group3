package beans;

import entities.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductDao extends AbstractDao<Product> {

    public ProductDao() {
        super(Product.class);
    }
}
