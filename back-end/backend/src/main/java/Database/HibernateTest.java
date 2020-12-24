package Database;

import Models.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
        /*StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        // Create user
        User user = new User();
        user.setAddress("asmaa123@gmail.com");
        try {
            user.setDOB(new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setPassword("12345");
        session.save(user);
        // user is in database now
        // create folder
        Folder f = new Folder();
        // set user owner
        f.setOwner(user);
        // connect folder to the user
        user.getFolders().add(f);
        session.save(f);
        // create EmailHeader
        EmailHeader emh = new EmailHeader();
        emh.setSender(user);
        // set header folder
        emh.setFolder(f);
        // add the header to folder
        f.getHeaders().add(emh);
        emh.setTitle("Hello World");
        session.save(emh);

        // retrieve user from the data base
        //User retrievedUser = session.find(User.class, 1);*/
        UserSession s=new UserSession(1);
        s.paging(1);
        //s.getUserFromDataBase();
        //s.showFolder();
        // it comes with his folders and mails
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //System.out.println(retrievedUser.getFolders().get(0).getHeaders().get(0).getTitle());
        /*String sql = "SELECT * FROM USERS WHERE user_address = :user_address AND user_password= :user_password";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        query.setParameter("user_address", "asmaa@quadcore.com");
        query.setParameter("user_password","123");
        List<User> results = query.list();
        User x=results.get(0);
        System.out.println(x.getAddress());
        System.out.println(results.size());*/
        /*trans.commit();
        session.close();
        factory.close();*/
    }
}
