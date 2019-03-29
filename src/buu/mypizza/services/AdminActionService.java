package buu.mypizza.services;

import buu.mypizza.models.Product;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;

/**
 *
 * @author nazar
 */
public class AdminActionService {

    public void addProductBalance(String productName, int add){
        Repository repo = new ProductsRepository();
        Product product = (Product) repo.get(productName);
        int endingPrice = product.getBalance() + add;
        String[] fiedls = {product.getName(), "" + product.getPrice(), "" + endingPrice}; 
        repo.update(product, fiedls);
    }
}
