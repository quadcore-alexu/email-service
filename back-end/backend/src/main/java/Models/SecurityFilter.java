package Models;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Base64;
import java.util.List;
import java.util.UUID;


public class SecurityFilter {
    private static SecurityFilter instance;
    private static SessionFactory factory;

    private SecurityFilter() {
    }

    public static synchronized SecurityFilter getInstance() {
        if (instance == null && factory == null) {
            instance = new SecurityFilter();
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        }
        return instance;
    }

    public static synchronized SessionFactory getSessionFactory() {
        return factory;
    }

    public String authenticateLogin(String encodedUserInfo) {
        String[] decodedUserInfo = getDecodedUserInfo(encodedUserInfo);
        String email = decodedUserInfo[0];
        String password = decodedUserInfo[1];
        boolean isAuthenticatedUser = matchesDB(email, password);
        return isAuthenticatedUser ? generateUniqueUUID() : "null";

    }

    private boolean matchesDB(String email, String password) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        String sql = "SELECT * FROM USERS WHERE  user_address = :user_address AND user_password= :user_password";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        query.setParameter("user_address", email);
        query.setParameter("user_password", password);
        List<User> results = query.list();
        System.out.println(results.size());
        trans.commit();
        session.close();
        return results.size() == 1 ? true : false;

    }

    private String generateUniqueUUID() {
        return UUID.randomUUID().toString();
    }

    private String[] getDecodedUserInfo(String encodedUserInfo) {
        String encodedUsernamePassword = encodedUserInfo.substring("Basic ".length()).trim();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodedUsernamePassword);
        String decodedLine = new String(bytes);
        int separator = decodedLine.indexOf(':');
        String[] decodedUserInfo = new String[2];
        decodedUserInfo[0] = decodedLine.substring(0, separator);
        decodedUserInfo[1] = decodedLine.substring(separator + 1);
        return decodedUserInfo;
    }


}
