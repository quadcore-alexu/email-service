package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaSubject extends Criteria {


    public CriteriaSubject(Integer userFolderID) {
        super(userFolderID);
    }


    @Override
    public List<EmailHeader> meetCriteria(String subject) {
        System.out.println(userFolderID);
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria subjectCriteria = session.createCriteria(EmailHeader.class);
        subjectCriteria.add(Restrictions.eq("title", subject));
        subjectCriteria.createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", userFolderID));
        List<EmailHeader> filteredMailHeaders = subjectCriteria.list();
        trans.commit();
        session.close();
        return filteredMailHeaders;

    }
}
