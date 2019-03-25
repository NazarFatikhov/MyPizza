package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.ProductDAO;
import buu.mypizza.models.Product;
import java.util.List;

/**
 *
 * @author nazar
 */
public class ProductsRepository extends Repository<Product>{
    
    private DAO<Product> dao;

    public ProductsRepository() {
        this.dao = new ProductDAO();
    }

    public ProductsRepository(DAO dao) {
        this.dao = dao;
    }
    
    @Override
    public void add(Product product){
        if (isExist(product)){
            //TODO throw ProdcutDublicateException
        }
        else if(product.getName() == null || 
                product.getPrice() == null){
            //TODO throw ModelNullFieldException
        }
        else{
            dao.save(product);
        }
    }
    
    public Product get(String name){
        List<Product> products = dao.getAll();
        for(Product p : products){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    
    @Override
    public boolean isExist(Product product){
        List<Product> products = dao.getAll();
        for (Product p : products){
            if(p.getName().equals(product.getName())){
                return true;
            }
        }
        return false;
    }
}
