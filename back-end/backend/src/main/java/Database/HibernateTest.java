package Database;

import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HibernateTest {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        // Create user
        User user = new User();
        user.setAddress("test@quadcore.com");
        try {
            user.setDOB(new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPassword("Ay 7aga");
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
        User retrievedUser = session.find(User.class, 1);
        // it comes with his folders and mails
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(retrievedUser.getFolders().get(0).getHeaders().get(0).getTitle());
        trans.commit();
        session.close();
        factory.close();
    }
}
