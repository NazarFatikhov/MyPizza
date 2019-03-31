package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.exceptions.DBException;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.ProductDublicateException;
import buu.mypizza.exceptions.UserDublicateException;
import java.util.List;

public interface Repository<T> {
    
    
    T getByStringKey(String key);
    
    void add(T t);
    
    boolean isExist(T t);
    
    List<T> getAll();
    
    void update(T t, String[] fields);
    
    void delete(T t);
    
    int getIdByStringKey(String key);
    
}
