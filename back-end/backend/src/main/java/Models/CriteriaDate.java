package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CriteriaDate extends Criteria{
    public CriteriaDate(Integer userFolderID,Integer page) {
        super(userFolderID,page);
    }

    @Override
    public List<EmailHeader> meetCriteria(String target) {
        Date date = null;
        try {
             date =new SimpleDateFormat("dd/MM/yyyy").parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria dateCriteria = session.createCriteria(EmailHeader.class);
        dateCriteria.createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", userFolderID))
                .add(Restrictions.eq("date",date));
        int startIndex=(page*6)-6;
        List<EmailHeader> filteredMailHeaders = dateCriteria.setFirstResult(startIndex).setMaxResults(6).list();
        trans.commit();
        session.close();
        return filteredMailHeaders;

    }
}
