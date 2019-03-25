/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.presentation;

import buu.mypizza.models.User;
import java.util.Scanner;

/**
 *
 * @author Kseniia
 */
public class OrderConsoleApplication {
    private User user;
    
    Scanner input = new Scanner(System.in);
    private Order order;
    private final String  forBeautiesTop ="*****************Pizza*****************";
    private final String  forBeautiesFooter="_______________________________________";

    public OrderConsoleApplication(User user) {
        this.user = user;
        this.order = new Order();
    }
    
    public void start(){
        println(forBeautiesTop);       
        choiceOfSize();
        choiceOfDough();
        println(forBeautiesTop);
        choiceOfSauce();
    }
    
    private void choiceOfSize(){ //выбор размера
        
        print("You can choose the size of 15, 25 or 35 cm.\n Choose the size of the desired Pizza:");
       
        
        order.setSizeOfThePizza(input.nextInt());
        if (order.getSizeOfThePizza()==15 | order.getSizeOfThePizza()==25 | order.getSizeOfThePizza()==35) {
            println(forBeautiesFooter);
               
        }else{
            
            System.out.println("Sorry, I don't know this size.\nTry to enter again.");
            choiceOfSize();
        }
    
    }
    
    private void choiceOfDough(){ // выбор теста 
        println("Enter the number of desired dough:\n"+
"	1 thick \n" +
"	2 thin");
        print(user.toString());
        order.setIdDough(input.nextInt());
        try{
            if(0<order.getIdDough() && order.getIdDough()<3){
                //************** по id ·извлекать из бд цену и добалять к исходной цене******************--->>>
                order.setTotalPrice(order.getTotalPrice()+1);// доделать!!!!
                println("Present value: "+order.getTotalPrice());
                println(forBeautiesFooter);
            }else{
                println("Sorry, I don't know this Dough.\nTry to enter again.");
                choiceOfDough();
            }
        }catch(Exception ex){
            println("Sorry, we're out of Dough at the moment.\n Please choose another one.");
            choiceOfDough();
        }
    }
            
    private void choiceOfSauce(){ //выбор соуса
        
        println("Enter the number of desired sauce: \n"+
"        1 garlic\n" +
"	2 cheese \n" +
"	3 exotic \n" +
"	4 tomato \n" +
"	5 creamy");
        order.setIdSauce(2+input.nextInt());
        try{
            if(2<order.getIdSauce() && order.getIdSauce()<8){
                //************** по id ·извлекать из бд цену и добалять к исходной цене******************--->>>
                order.setTotalPrice(order.getTotalPrice()+1);// доделать!!!!
                println("Present value: "+order.getTotalPrice());
                println(forBeautiesFooter);
            }else{
                println("Sorry, I don't know this Sauce.\nTry to enter again.");
                choiceOfSauce();
            }
        }catch(Exception ex){
            println("Sorry, we're out of sauce at the moment.\n Please choose another one.");
            choiceOfSauce();
        }
    }
    
    private void choiseOfFilling(){
        
    }
    private void print(String s){
        System.out.print(s);
    }
    private void println(String s){
        System.out.println(s);
    }
}
