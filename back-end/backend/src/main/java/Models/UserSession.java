package Models;

import Models.Immutables.EmailHeaderImmutable;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserSession {

    private int userID;
    private User currentUser;
    private SessionFactory factory = SecurityFilter.getInstance().getSessionFactory();

    public UserSession(int id){
        this.userID=id;
        currentUser=getUserFromDataBase();

    }


    public User getUserFromDataBase() {
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        currentUser = session.find(User.class, userID);
        trans.commit();
        session.close();
        return currentUser;
    }
    public Email getMail(int emailId){
        Session session = factory.openSession();
        Transaction trans  = session.beginTransaction();
        Email email = session.find(Email.class, emailId);
        trans.commit();
        session.close();
        return email;
    }

    public EmailHeader createEmailHeader(Email email){
        EmailHeader emailHeader = new EmailHeader();
        emailHeader.setTitle(email.getTitle());
        emailHeader.setEmail(email);
        emailHeader.setSender(currentUser);
        emailHeader.setDate(email.getDate());
        emailHeader.setPriority(email.getPriority());
        return emailHeader;
    }
    public void setEmailContent(Email email,Map<String, Object> emailMap,String [] attachmentPaths){
        Session session = factory.openSession();
        email.setTitle(emailMap.get("title").toString());
        email.setPriority((int)emailMap.get("priority"));
        email.setContent(emailMap.get("content").toString());
        email.setSender(currentUser);
        Date date=new Date();
        email.setDate(date);
        session.save(email);

        for(int i=0;i<attachmentPaths.length;i++){
            Attachment attachment=new Attachment();
            attachment.setEmail(email);
            attachment.setPath(attachmentPaths[i]);
            email.getAttachments().add(attachment);
            session.save(attachment);
        }

    }


    public void sendEmail(Map<String, Object> emailMap, String[] receiver_address, String [] attachmentPaths){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Email email = new Email();
        setEmailContent(email,emailMap,attachmentPaths);
        String idList="(";
        for (int i=0;i<receiver_address.length-1;i++){
            idList+="'"+receiver_address[i]+"'";
            idList+=",";
        }
        idList+="'"+receiver_address[receiver_address.length-1]+"'";
        idList+=")";

        String sql = "SELECT * FROM USERS WHERE user_address IN ";
        sql+=idList;
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        List<User> receivers=query.list();

        for (int i=0;i<receivers.size();i++){
            EmailHeader emailHeader = createEmailHeader(email);
            emailHeader.setFolder(receivers.get(i).getFolders().get(0));
            receivers.get(i).getFolders().get(0).getHeaders().add(emailHeader);
            session.save(receivers.get(i));
            session.save(emailHeader);
        }
        EmailHeader emailHeader = createEmailHeader(email);
        emailHeader.setFolder(currentUser.getFolders().get(2));
        currentUser.getFolders().get(2).getHeaders().add(emailHeader);
        session.save(emailHeader);
        trans.commit();
        session.close();
    }

    public void draft(Map<String, Object> emailMap,String [] attachmentPaths){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Email email = new Email();
        setEmailContent(email,emailMap,attachmentPaths);
        EmailHeader emailHeader = createEmailHeader(email);
        emailHeader.setEmail(email);
        emailHeader.setFolder(currentUser.getFolders().get(2));
        currentUser.getFolders().get(2).getHeaders().add(emailHeader);
        session.save(emailHeader);
        trans.commit();
        session.close();
    }

    public void moveEmail(int []headersId,int currentFolder,int destinationFolder){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        List<EmailHeader> emailHeaders=getEmailHeader(headersId);
        for (int i=0;i<emailHeaders.size();i++){
            currentUser.getFolders().get(destinationFolder).getHeaders().add(emailHeaders.get(i));
            currentUser.getFolders().get(currentFolder).getHeaders().remove(emailHeaders.get(i));
            emailHeaders.get(i).setFolder(currentUser.getFolders().get(destinationFolder));
            session.save(emailHeaders.get(i));

        }

        System.out.println(currentUser.getFolders().get(destinationFolder).getHeaders().size());
        System.out.println(currentUser.getFolders().get(currentFolder).getHeaders().size());

        trans.commit();
        session.close();
    }
    public void copyEmail(int []headersId,int currentFolder,int destinationFolder){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        List<EmailHeader> emailHeaders=getEmailHeader(headersId);
        for (int i=0;i<emailHeaders.size();i++){
            currentUser.getFolders().get(destinationFolder).getHeaders().add(emailHeaders.get(i));
            emailHeaders.get(i).setFolder(currentUser.getFolders().get(destinationFolder));
            session.save(emailHeaders.get(i));
        }

        System.out.println(currentUser.getFolders().get(destinationFolder).getHeaders().size());
        System.out.println(currentUser.getFolders().get(currentFolder).getHeaders().size());

        trans.commit();
        session.close();
    }

    public void deleteEmail(int []headersId,int currentFolder){
        moveEmail(headersId,currentFolder,3);
    }

    public List<EmailHeader> getEmailHeader(int[] headersId){
        Session session = factory.openSession();
        String idList="(";
        for (int i=0;i<headersId.length-1;i++){
            idList+="'"+headersId[i]+"'";
            idList+=",";
        }
        idList+="'"+headersId[headersId.length-1]+"'";
        idList+=")";
        String sql = "SELECT * FROM EMAIL_HEADERS WHERE email_header_id IN ";
        sql+=idList;
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(EmailHeader.class);
        List<EmailHeader> emailHeaders=query.list();
        session.close();
        return emailHeaders;
    }

    public void addFolder(Map<String,Object> folderMap){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Folder folder = new Folder();
        folder.setOwner(currentUser);
        folder.setFolderName(folderMap.get("name").toString());
        currentUser.getFolders().add(folder);
        session.save(folder);
        trans.commit();
        session.close();
    }

    public void editFolder(Map<String, Object> folderMap){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Folder folder = session.find(Folder.class, folderMap.get("id"));
        folder.setOwner(currentUser);
        folder.setFolderName(folderMap.get("name").toString());
        session.save(folder);
        trans.commit();
        session.close();

    }

    public void removeFolder(int folderID){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Folder folder = session.find(Folder.class, folderID);
        folder.setOwner(currentUser);
        currentUser.getFolders().remove(folder);
        session.save(currentUser);
        trans.commit();
        session.close();
        System.out.println(currentUser.getFolders().size());
    }

    public List<EmailHeader> paging(int pageNumber){
        System.out.println("####################################################################");
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria cr = session.createCriteria(EmailHeader.class);
        int n=6*(pageNumber-1);
        cr.setFirstResult(7);
        cr.setMaxResults(6);
        cr.createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", 2));

        List<EmailHeader> emailHeaders  = cr.list();
        for (int i=0;i<emailHeaders.size();i++) {
            System.out.println("####################################################################");
            System.out.println(emailHeaders.get(i).getEmailHeaderID());
            System.out.println("####################################################################");
        }

        trans.commit();
        session.close();
        return emailHeaders;
    }

    public List<String> getFolderNames() {
        List folders = this.currentUser.getFolders();
        List<String> folderNames = new ArrayList<>();
        for (int i = 0; i < folders.size(); i++) {
            Folder f= (Folder) folders.get(i);
            folderNames.add(f.getFolderName());

        }
        return folderNames;
    }

    public List<EmailHeaderImmutable> loadEmailHeaders(int folderIndex, int page, String sortingCriteria,Boolean order){
        SortCriteria criteria=null;
        int folderId= currentUser.getFolders().get(folderIndex).getFolderID();
        if (sortingCriteria=="date")
            criteria=new SortCriteriaDate(folderId,page,order);
        else if(sortingCriteria=="priority")
            criteria=new SortCriteriaPriority(folderId,page,order);
        else if (sortingCriteria=="subject")
            criteria=new SortCriteriaSubject(folderId,page,order);
        List<EmailHeader>headers=criteria.sort();
        List<EmailHeaderImmutable> immutableList=toImmutable(headers);
        return immutableList;

    }

    public List<EmailHeaderImmutable> toImmutable(List<EmailHeader> list){
        List<EmailHeaderImmutable> immutableList = new ArrayList<EmailHeaderImmutable>();
        for (EmailHeader eh: list) {
            immutableList.add(new EmailHeaderImmutable(eh));
        }
        return immutableList;
    }

    public User getCurrentUser() {
        return currentUser;
    }



    public void test(){
        Session session = factory.openSession();
        int x=currentUser.getFolders().get(1).getHeaders().size();
        int y=currentUser.getFolders().get(2).getHeaders().size();
        System.out.println(x);
        System.out.println(y);
        session.close();
    }


}