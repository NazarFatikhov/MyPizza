package buu.mypizza.services;

import buu.mypizza.dao.UserDAO;
import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.models.User;
import buu.mypizza.repositorys.UserRepository;
import buu.mypizza.exceptions.UserDublicateLoggedException;
import buu.mypizza.exceptions.IncorrectPasswordException;
import buu.mypizza.exceptions.UserNotFoundException;
import buu.mypizza.repositorys.Repository;

/**
 *
 * @author nazar
 */
public class SecurityService {
    
    private static SecurityService instance = null;
    
    private Repository<User> repo = new UserRepository(new UserDAO());
    
    private User loggedUser = null;
    
    public static SecurityService newInstance(){
        if (instance == null){
            instance = new SecurityService();
        }
        return instance;
    }
    
    public void signUpUser(String email, String password) throws UserDublicateException, ModelNullFieldException {
        User user = repo.getByStringKey(email);
        if(user == null){
            throw new UserDublicateException();
        }
        else if(user.getEmail() == null || user.getPassword() == null){
            throw new ModelNullFieldException();
        }
        else{
            repo.add(user);
        }
    }
    
    public void signInUser(String email, String password) throws UserDublicateLoggedException, IncorrectPasswordException, UserNotFoundException{
        User user = repo.getByStringKey(email);
        if(loggedUser != null){
            throw new UserDublicateLoggedException();
        }
        else if(user != null){
            if(isCorrectPassword(email, password)){
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
    
    public User getUserByEmail(String email) throws UserNotFoundException{
        User user = repo.getByStringKey(email);
        if(user == null){
            throw new UserNotFoundException();
        }
        else{
            return user;
        }
    }
    
    public boolean isCorrectPassword(String email, String password){
        User realUser = repo.getByStringKey(email);
        if(realUser.getPassword().equals(password)){
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

    public Repository getRepo() {
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
