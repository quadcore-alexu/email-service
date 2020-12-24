package Models;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class SortCriteriaPriority extends SortCriteria{


    public SortCriteriaPriority(Integer userFolderID, Integer page) {
        super(userFolderID, page);
    }

    @Override
    public List<EmailHeader> sort() {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria sortCriteriaPriority= session.createCriteria(EmailHeader.class)
                .createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", userFolderID))
                .addOrder(Order.asc("priority"));
        int startIndex=(page*6)-6;
        List<EmailHeader> sortedList=sortCriteriaPriority.setFirstResult(startIndex).setMaxResults(6).list();
        trans.commit();
        session.close();
        return sortedList;
    }


}
