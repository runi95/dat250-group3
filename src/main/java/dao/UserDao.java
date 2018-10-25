package dao;

import entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserDao extends AbstractDao<User>{

    //TODO Find user in database by username

    public UserDao() {
        super(User.class);
    }

    public void registerUser(String fName, String lName, String uName, String email, String pwd) {
        User u = new User();
        u.setName(fName);
        u.setLastName(lName);
        u.setUserName(uName);
        u.setEmail(email);
        u.setPassword(pwd);
        u.setNumberOfRatings(0);
        u.setSumOfAllRatings(0);
        super.persist(u);
    }

    public boolean loginUser(String uName, String pwd) {
        User u = super.find(uName);
        if (u.getPassword().equals(pwd)) {
            //TODO do somehting else here. Session bean?
            return true;
        } else {
            return false;
        }
    }

    public void updateEmail(String uName, String email) {
        User u = super.find(uName);
        u.setEmail(email);
        super.edit(u);
    }
}
