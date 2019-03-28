/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.dto;

/**
 *
 * @author nazar
 */
public class ProductDTO {
    
    private int id;
    private String name;
    private double price;
    private int balance;

    public ProductDTO() { 
    }

    public ProductDTO(String name, double price, int balance) {
        this.name = name;
        this.price = price;
        this.balance = balance;
    }

    
    
    public ProductDTO(int id, String name, float price, int balance) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", balance=" + balance + '}';
    }
    
    
    
    
    
}
