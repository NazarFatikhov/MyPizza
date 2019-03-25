package buu.mypizza.models;

/**
 *
 * @author nazar
 */
public class Product {
    
    private String name;
    private Double price;
    private int balance;

    public Product(String[] fields){
        this.name = fields[0];
        this.price = Double.parseDouble(fields[1]);
        this.balance = Integer.parseInt(fields[2]);
    }
    
    public Product(String name, Double price, int balance) {
        this.name = name;
        this.price = price;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
}
