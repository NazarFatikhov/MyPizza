package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.UserDAO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.mappers.UserDTOMapper;
import buu.mypizza.mappers.UserMapper;
import buu.mypizza.models.User;
import java.util.List;

/**
 *
 * @author nazar
 */
public class UserRepository extends Repository<User>{
    
    DAO<UserDTO> dao;
    private Mapper userMapper = new UserMapper();
    private Mapper userDTOMapper = new UserDTOMapper();
    
    public UserRepository(DAO dao) {
        this.dao = dao;
    }

    public UserRepository() {
        this.dao = new UserDAO();
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
            dao.save((UserDTO) userMapper.map(user));
        }
    }
    
    public User get(String email){
        List<User> users = userDTOMapper.mapList(dao.getAll());
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
        users = userDTOMapper.mapList(dao.getAll());
        for (User u : users){
            if(user.getEmail().equals(u.getEmail())){
                return true;
            }
        }
        return false;
    }
    
}
