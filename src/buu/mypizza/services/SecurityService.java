package buu.mypizza.services;

import buu.mypizza.dao.UserDAO;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.models.User;
import buu.mypizza.repositorys.UserRepository;
import buu.mypizza.exceptions.UserDublicateLoggedException;
import buu.mypizza.exceptions.IncorrectPasswordException;
import buu.mypizza.exceptions.UserNotFoundException;

/**
 *
 * @author nazar
 */
public class SecurityService {
    
    private static SecurityService instance;
    
    private UserRepository repo = new UserRepository(new UserDAO());
    
    private User loggedUser = null;
    
    public static SecurityService newInstance(){
        if (instance == null){
            instance = new SecurityService();
        }
        return instance;
    }
    
    public void signUpUser(User user) throws UserDublicateException, ModelNullFieldException {
        repo.add(user);
    }
    
    public void signInUser(User user) throws UserDublicateLoggedException, IncorrectPasswordException, UserNotFoundException{
        if(loggedUser != null){
            throw new UserDublicateLoggedException();
        }
        else if(repo.isExist(user)){
            if(isCorrectPassword(user)){
                loggedUser = user;
            }
            else{
                throw new IncorrectPasswordException();
            }
        }
        else{
            throw new UserNotFoundException();
        }
    }
    
    public void signOutUser(){
        this.loggedUser = null;
    }
    
    public boolean isCorrectPassword(User user){
        User realUser = repo.get(user.getEmail());
        if(realUser.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SecurityService{" + "repo=" + repo + ", loggedUser=" + loggedUser + '}';
    }
    
    
    
    
}
