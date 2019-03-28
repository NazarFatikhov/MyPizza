package buu.mypizza.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nazar
 */
public class Order {

    
    private long id;
    private User owner;
    private List<Product> products;
    private double price;
    private Date date;
    private String address;
    private String comment;

    public Order(long id, User owner, List<Product> products, double price, Date date, String address, String comment) {
        this.id = id;
        this.owner = owner;
        this.products = products;
        this.price = price;
        this.date = date;
        this.address = address;
        this.comment = comment;
    }

    public Order() {
    
    }

    public User getOwner() {
        return owner;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", owner=" + owner + '}';
    }
    
    
    
    
        
       
    
}
