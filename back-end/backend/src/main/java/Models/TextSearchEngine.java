package Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TextSearchEngine {

    Integer userFolderID;
    private SessionFactory factory=SecurityFilter.getInstance().getSessionFactory();

    public List<EmailHeader> getSearchResults(String target)
    {
        Session session= factory.openSession();
        org.hibernate.Criteria cr = session.createCriteria(EmailHeader.class)
                .createAlias("folder","currentFolder")
                .createAlias("sender","currentSender")
                .add(Restrictions.eq("currentFolder.folderID",userFolderID));

        Criterion title =Restrictions.ilike("title", target);
        Criterion sender = Restrictions.ilike("currentSender.userName",target);

        LogicalExpression orExp = Restrictions.or(title,sender);
        cr.add( orExp );
        return cr.list();
    }

}
