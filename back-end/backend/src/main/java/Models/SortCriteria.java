package Models;

import org.hibernate.SessionFactory;

import java.util.List;

public abstract class SortCriteria {
    protected SessionFactory factory=SecurityFilter.getInstance().getSessionFactory();
    protected Integer userFolderID;
    protected Integer page;

    public SortCriteria(Integer userFolderID, Integer page) {
        this.userFolderID=userFolderID;
        this.page=page;
    }

    public abstract List<EmailHeader> sort();
}
