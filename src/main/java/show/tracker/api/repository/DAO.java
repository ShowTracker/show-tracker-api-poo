
package show.tracker.api.repository;

import java.util.List;


public abstract class DAO<T> {
    
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:" + "database/showtracker.db";
    public static final String USER = "";
    public static final String PASSWORD = "";
    
    public abstract boolean insert(T obj);
    
    public abstract T getOne(int id);
    
    public abstract List<T> getAll();
    
    public abstract boolean update(T obj);
    
    public abstract boolean delete(int id);
}
