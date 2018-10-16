package beans;

import entities.User;

import javax.ejb.Stateless;
import java.util.HashSet;

@Stateless
public class UserDao extends AbstractDao<User>{

    //TODO Find user in database by username

    public UserDao() {
        super(User.class);
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
        edit(u);
    }

    public void createUser(String uName, String fName, String lName, String pwd, String email) {
        User u = new User();
        u.setUserName(uName);
        u.setName(fName);
        u.setLastName(lName);
        u.setPassword(pwd);
        u.setEmail(email);
        u.setComments(new HashSet<>());
        u.setNumberOfRatings(0);
        u.setSumOfAllRatings(0);

        persist(u);
    }
}
