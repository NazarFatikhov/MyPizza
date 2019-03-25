/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.presentation;

/**
 *
 * @author Kseniia
 */
public class Order {
    private int totalPrice;
    private int idSauce;
    private int idDough;
    private int sizeOfThePizza;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIdSauce() {
        return idSauce;
    }

    public void setIdSauce(int idSauce) {
        this.idSauce = idSauce;
    }

    public int getIdDough() {
        return idDough;
    }

    public void setIdDough(int idDough) {
        this.idDough = idDough;
    }

    public int getSizeOfThePizza() {
        return sizeOfThePizza;
    }

    public void setSizeOfThePizza(int sizeOfThePizza) {
        this.sizeOfThePizza = sizeOfThePizza;
    }
    
    
}
