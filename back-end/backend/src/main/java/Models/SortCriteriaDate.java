package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SortCriteriaDate extends SortCriteria{
    public SortCriteriaDate(Integer userFolderID, Integer page) {
        super(userFolderID, page);
    }

    @Override
    public List<EmailHeader> sort() {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria sortCriteriaDate= session.createCriteria(EmailHeader.class)
                .createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", userFolderID))
                .addOrder(Order.asc("date"));
        int startIndex=(page*6)-6;
        List<EmailHeader> sortedList=sortCriteriaDate.setFirstResult(startIndex).setMaxResults(6).list();
        trans.commit();
        session.close();
        return sortedList;
    }
}
