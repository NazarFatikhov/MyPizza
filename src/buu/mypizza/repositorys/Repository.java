package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import java.util.List;

public abstract class Repository<T> {
    
    private DAO<T> dao;
    
    public void add(T t) throws UserDublicateException, ModelNullFieldException{
        if (isExist(t)){
            //TODO throw ProdcutDublicateException
        }
        else{
            dao.save(t);
        }
    }
    
    public boolean isExist(T t){
        List<T> tes;
        tes = dao.getAll();
        for (T tt: tes){
            if(tt.equals(t)){
                return true;
            }
        }
        return false;
    }
    
    public List<T> getAll(){
        List<T> tes = dao.getAll();
        if(tes.isEmpty()){
//            throw new DBException();
        }
        return tes;
    }
    
    public void update(T t, String[] fields){
        dao.update(t, fields);
    }
    
    public void delete(T t){
        dao.delete(t);
    }
    
}
