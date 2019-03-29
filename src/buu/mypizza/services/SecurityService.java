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
    
    private static SecurityService instance = null;
    
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
    
    public User getUserByEmail(String email){
        return repo.get(email);
    }
    
    public boolean isCorrectPassword(User user){
        User realUser = repo.get(user.getEmail());
        if(realUser.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
    
    public boolean isLoggedUser(){
        return loggedUser != null;
    }

    public static SecurityService getInstance() {
        return instance;
    }

    public UserRepository getRepo() {
        return repo;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public static void setInstance(SecurityService instance) {
        SecurityService.instance = instance;
    }

    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    

    @Override
    public String toString() {
        return "SecurityService{" + "repo=" + repo + ", loggedUser=" + loggedUser + '}';
    }
    
    
    
    
}
