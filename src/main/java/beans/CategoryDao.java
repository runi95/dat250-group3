package beans;

import entities.Category;

import javax.ejb.Stateless;

@Stateless
public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao() {
        super(Category.class);
    }
}
