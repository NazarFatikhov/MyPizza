package buu.mypizza.presentation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kseniia
 */
public class Order {
    private int totalPrice;
    private String sauce;
    private String dough;
    private int sizeOfThePizza;
    private Map<String, Integer> filling =new HashMap<>();
    private String comment;
    private String city;
    private String street;
    private String houseNumber;
    private int apartment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public Map<String, Integer> getFilling() {
        return filling;
    }

    
    

    public int getSizeOfThePizza() {
        return sizeOfThePizza;
    }

    public void setSizeOfThePizza(int sizeOfThePizza) {
        this.sizeOfThePizza = sizeOfThePizza;
    }
    
    
}
