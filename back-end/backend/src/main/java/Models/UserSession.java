package Models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserSession {
    //user
    //const take id
    //get user from db
    //sent,draft,move,delete

    private int userID;

    private User user;
    private SessionFactory factory = SecurityFilter.getInstance().getSessionFactory();

    public UserSession(int id){
        this.userID=id;
        user=new User();
    }

    public User getUserFromDataBase() {
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();

        user = session.find(User.class, userID);
        //System.out.println(retrievedUser.getFolders().get(0).getHeaders().get(0).getTitle());

        trans.commit();
        session.close();
        return user;
    }

    public void showFolder(){
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        String x=user.getFolders().get(0).getHeaders().get(0).getTitle();
        System.out.println(x);
        session.close();
    }
}
