package buu.mypizza.services;

import buu.mypizza.models.Order;
import buu.mypizza.models.User;
import buu.mypizza.models.Product;
import buu.mypizza.repositorys.OrderRepository;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import buu.mypizza.repositorys.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nazar
 */
public class OrderActionService {
    
    public void createOrder(String emailUser, Map<String, Integer> products, String address, String comment){
        Repository orderRepository = new OrderRepository();
        Repository productRepository = new ProductsRepository();
        Repository userRepository = new UserRepository();
        User owner = (User) userRepository.getByStringKey(emailUser);
        List<Product> productsList = new ArrayList<>();
        int priceOfAllProducts = 0;
        for (String productName : products.keySet()){
            Product product = (Product) productRepository.getByStringKey(productName);
            String[] newProductFields = {product.getName(), Double.toString(product.getPrice()), Integer.toString(product.getBalance() - products.get(productName))};
            productRepository.update(product, newProductFields);
            priceOfAllProducts += products.get(productName) * product.getPrice();
            productsList.add(product);
        }
        Order order = new Order(owner, productsList, priceOfAllProducts, Date.from(Instant.now()), address, comment);
        orderRepository.add(order);
    }
    
    public double getPriceForProduct(String productName){
        Repository productRepository = new ProductsRepository();
        Product product = (Product) productRepository.getByStringKey(productName);
        return product.getPrice();
    }
    
}
