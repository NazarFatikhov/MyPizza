package buu.mypizza.services;

import buu.mypizza.models.Order;
import buu.mypizza.models.User;
import buu.mypizza.models.Product;
import buu.mypizza.repositorys.OrderRepository;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
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
    
    public void createOrder(User owner, Map<String, Integer> products, String address, String comment, int kef){
        Repository orderRepository = new OrderRepository();
        Repository productRepository = new ProductsRepository();
        List<Product> productsList = new ArrayList<>();
        int priceOfAllProducts = 0;
        for (String productName : products.keySet()){
            Product product = (Product) productRepository.getByStringKey(productName);
            priceOfAllProducts += products.get(productName) * product.getPrice();
            productsList.add(product);
        }
        Order order = new Order(owner, productsList, kef * priceOfAllProducts, Date.from(Instant.now()), address, comment);
        orderRepository.add(order);
    }
    
    public double getPriceForProduct(String productName){
        Repository productRepository = new ProductsRepository();
        Product product = (Product) productRepository.getByStringKey(productName);
        return product.getPrice();
    }
    
}
