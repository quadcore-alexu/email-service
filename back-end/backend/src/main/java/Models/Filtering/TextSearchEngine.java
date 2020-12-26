package Models.Filtering;


import Models.EmailHeader;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TextSearchEngine extends Criteria {

    public TextSearchEngine(Integer userFolderID, Integer page) {
        super(userFolderID, page);
    }

    @Override
    public List<EmailHeader> meetCriteria(String target) {
        Session session= factory.openSession();
        org.hibernate.Criteria cr = session.createCriteria(EmailHeader.class)
                .createAlias("folder","currentFolder")
                .createAlias("sender","currentSender")
                .add(Restrictions.eq("currentFolder.folderID",userFolderID));

        Criterion title =Restrictions.ilike("title", target);
        Criterion sender = Restrictions.ilike("currentSender.userName",target);
        LogicalExpression orExp = Restrictions.or(title,sender);
        cr.add( orExp );
        int startIndex=(page*6)-6;
        List<EmailHeader> filteredMailHeaders = cr.setFirstResult(startIndex).setMaxResults(6).list();
        System.err.println(userFolderID);
        System.err.println(filteredMailHeaders.size());
        return filteredMailHeaders;
    }
}
