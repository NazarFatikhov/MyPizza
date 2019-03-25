package buu.mypizza.models;

import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;

/**
 *
 * @author nazar
 */
public class Admin extends User{
    
    public Admin(String email, String password) {
        super(email, password);
    }
    
    public Admin(String[] fields){
        super(fields);
    }
    
    public void addProduct(Product product) throws UserDublicateException, ModelNullFieldException{
        Repository repo = new ProductsRepository();
        repo.add(product);
    }
    
}
