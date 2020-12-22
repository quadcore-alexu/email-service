package Database;

import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FilterTest {
    //test it using a user in your db

    @Test
    public void testSubjectFilter()
    {
        //addToDB();
        Criteria subjectFilter=new CriteriaSubject(35);
        List<EmailHeader> result=subjectFilter.meetCriteria("meow0Mail");
        Assert.assertEquals(1,result.size());
        Assert.assertEquals("meow0Mail",result.get(0).getTitle());

    }
     @Test
    public void testSenderFilter()
    {
        Criteria senderFilter=new CriteriaSender(35);
        List<EmailHeader> result=senderFilter.meetCriteria("35");
        Assert.assertEquals(1,result.size());
        int id=result.get(0).getSender().getUserID();
        Assert.assertEquals(35,id);

    }
    public void addToDB()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        User user = new User();
        user.setAddress("meow123@gmail.com");
        try {
            user.setDOB(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/1999"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPassword("hiiii");
        session.save(user);
        Folder f = new Folder();
        f.setOwner(user);
        user.getFolders().add(f);
        session.save(f);
        EmailHeader emh = new EmailHeader();
        emh.setSender(user);
        emh.setPriority(5);
        emh.setFolder(f);
        f.getHeaders().add(emh);
        emh.setTitle("meow0Mail");
        session.save(emh);
        trans.commit();
        session.close();
        factory.close();
    }

}
