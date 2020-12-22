package Models;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class UserSession {

    private int userID;

    private User currentUser;
    private SessionFactory factory = SecurityFilter.getInstance().getSessionFactory();

    public UserSession(int id){
        this.userID=id;
        currentUser=new User();
    }

    public User getUserFromDataBase() {
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();

        currentUser = session.find(User.class, userID);

        trans.commit();
        session.close();
        return currentUser;
    }

    /*public void showFolder(){
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        String x=user.getFolders().get(0).getHeaders().get(0).getTitle();
        System.out.println(x);
        session.close();
    }*/

    public void sendEmail(Email email,List<String> receiver_address,String [] attachmentPaths){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        email.setSender(currentUser);
        Date date=new Date();
        email.setDate(date);

        for(int i=0;i<attachmentPaths.length;i++){
            Attachment attachment=new Attachment();
            attachment.setEmail(email);
            attachment.setPath(attachmentPaths[i]);
            email.getAttachments().add(attachment);
            session.save(attachment);
        }

        String idList="(";
        for (int i=0;i<receiver_address.size()-1;i++){
            idList+="'"+receiver_address.get(i)+"'";
            idList+=",";
        }
        idList+="'"+receiver_address.get(receiver_address.size()-1)+"'";
        idList+=")";

        String sql = "SELECT * FROM USERS WHERE user_id IN ";
        sql+=idList;
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        List<User> receivers=query.list();

        for (int i=0;i<receivers.size();i++){
            EmailHeader emailHeader = new EmailHeader();
            emailHeader.setTitle(email.getTitle());
            emailHeader.setSender(currentUser);
            emailHeader.setDate(email.getDate());
            emailHeader.setPriority(email.getPriority());
            emailHeader.setFolder(receivers.get(i).getFolders().get(0));
            receivers.get(i).getFolders().get(0).getHeaders().add(emailHeader);
            session.save(receivers.get(i));
            session.save(emailHeader);
        }

        trans.commit();
        session.close();
    }
}
