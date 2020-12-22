package Models;

import org.hibernate.SessionFactory;

import java.util.List;

public abstract class Criteria {
    protected SessionFactory factory=SecurityFilter.getInstance().getSessionFactory();
    protected Integer userFolderID;
    public abstract List<EmailHeader> meetCriteria( String target);
    public Criteria(Integer userFolderID)
    {
        this.userFolderID=userFolderID;
    }
}
