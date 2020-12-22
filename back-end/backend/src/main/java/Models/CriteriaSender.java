package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaSender extends Criteria {

    public CriteriaSender(Integer userFolderID) {
        super(userFolderID);
    }

    @Override
    public List<EmailHeader> meetCriteria(String sender) {

        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.Criteria senderCriteria = session.createCriteria(EmailHeader.class);
        senderCriteria.createAlias("sender", "currentSender")
                .add(Restrictions.eq("currentSender.userID", Integer.parseInt(sender)));
        senderCriteria.createAlias("folder", "currentFolder")
                .add(Restrictions.eq("currentFolder.folderID", userFolderID));
        List<EmailHeader> filteredMailHeaders = senderCriteria.list();
        trans.commit();
        session.close();
        return filteredMailHeaders;
    }


}
