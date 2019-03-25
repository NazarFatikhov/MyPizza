package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.models.User;
import java.util.List;

/**
 *
 * @author nazar
 */
public class UserRepository extends Repository<User>{
    
    DAO<User> dao;
    
    public UserRepository(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void add(User user) throws UserDublicateException, ModelNullFieldException{
        if(isExist(user)){
            throw new UserDublicateException();
        }
        else if(user.getEmail() == null || user.getPassword() == null){
            throw new ModelNullFieldException();
        }
        else{
            dao.save(user);
        }
    }
    
    public User get(String email){
        List<User> users = dao.getAll();
        for(User u : users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    
    @Override
    public boolean isExist(User user){
        List<User> users;
        users = dao.getAll();
        for (User u : users){
            if(user.getEmail().equals(u.getEmail())){
                return true;
            }
        }
        return false;
    }
    
}
