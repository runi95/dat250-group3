package beans;

import entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserDao extends AbstractDao<User>{

    public UserDao() {
        super(User.class);
    }
}
