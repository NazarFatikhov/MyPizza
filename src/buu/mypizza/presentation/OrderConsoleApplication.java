package buu.mypizza.presentation;

import buu.mypizza.models.Client;
import buu.mypizza.services.OrderActionService;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author Kseniia
 */
public class OrderConsoleApplication {
    private Client user;
    
    Scanner input = new Scanner(System.in);
    private Order order;
    private final String  forBeautiesTop ="*****************Pizza*****************";
    private final String  forBeautiesFooter="_______________________________________";
    private final String space="         "; 
    
    private enum Dough{thick,thin };
    private enum Sauce{GARLIC, CHEESE,EXOTIC ,TOMATO ,CREAMY };
    private enum Filling{Chicken, Bacon, Shrimp, Sausage, Pineapples, Tomatoes, Pepper, Broccoli, Onion, Olives};
    
    public OrderConsoleApplication(Client user) {
        this.user = user;
        this.order = new Order();
    }
    
    public void start(){
        println(forBeautiesTop);       
        choiceOfSize();
        println(forBeautiesTop);
        choiceOfDough();
        println(forBeautiesTop);
        choiceOfSauce();
        println(forBeautiesTop);
        choiseOfFilling();
        orderRegistration();
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
        println("Enter the number of desired dough:");
        Stream.of(Dough.values()).forEach(x->println(space+(x.ordinal())+" : "+x.toString()));
        print(user.toString());
        try{
            int id = input.nextInt();
            
            if(0<=id && id<Dough.values().length){
                order.setDough(Dough.values()[id].toString());
                OrderActionService serv = new OrderActionService();
                double price = serv.getPriceForProduct("Dough");
                order.getFilling().put("Dough", id + 1);
                priceChange((int) (price * (id + 1)));//***********
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
        
        println("Enter the number of desired sauce:");
        Stream.of(Sauce.values()).forEach(x->println(space+(x.ordinal())+" : "+x.toString()));
        print(user.toString());
        
        try{
            int id = input.nextInt();
            if(0<=id && id<Sauce.values().length){
                order.setSauce(Sauce.values()[id].toString());
                OrderActionService serv = new OrderActionService();
                double price = serv.getPriceForProduct(Sauce.values()[id].toString());
                order.getFilling().put(Sauce.values()[id].toString(), 1);
                priceChange((int) price);//***********
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
    
    private void choiseOfFilling() { // выбор начинки
        println("Enter the product number and quantity, separated by a space.");
        Stream.of(Filling.values()).forEach(x -> println(space + (x.ordinal()) + " : " + x.toString()));
        println("To complete the order, enter \"the end\"");
        
        String id;
        String count;
        while (true) {
            print(user.toString());
            try {
                id = input.next();
                count = input.next();
                        
                if (id.equals("the") && count.equals("end")) {
                    println(forBeautiesFooter);
                    break;
                    //noDone=false;
                } else {
                    if (0 <= Integer.parseInt(id) && Integer.parseInt(id) < Filling.values().length) {
                        OrderActionService serv = new OrderActionService();
                        double price = serv.getPriceForProduct(Filling.values()[Integer.parseInt(id)].toString());
                        order.getFilling().put(Filling.values()[Integer.parseInt(id)].toString(), Integer.parseInt(count));
                        priceChange((int) price * Integer.parseInt(count));// доделать!!!!
                    } else {
                        println("Sorry, I don't know this Filling.\nTry to enter again.");

                    }
                }
            } catch (Exception ex) {
                println("Sorry, we're out of filling at the moment.\n Please choose another one.");
                choiseOfFilling();
            }

        }

    }

    
    private void print(String s){
        System.out.print(s);
    }
    private void println(String s){
        System.out.println(s);
    }
    
    private void orderRegistration(){
        println("Are you sure you're ready to order?(Y/N)");
        String command;
        print(user.toString());
        switch (command = input.next()) {
           case  ("Y"):
               fillingInFields();
               break;
           case ("N"):
               CommandsForConsoleApplication.getCommandsForConsoleApplication().readingCommand();
               break;
           case ("y"):
               
               fillingInFields();
               break;
           case ("n"):
               CommandsForConsoleApplication.getCommandsForConsoleApplication().readingCommand();
               break;
           default:
               System.out.println(space+"Sorry, I don't know this ocommand.\nTry to enter again.");
               orderRegistration();
               break;
       }
    }
    
    private void fillingInFields() {
        String s;
        println(forBeautiesTop);
        input.nextLine();
        getCity();
        getStreet();
        getHouseNumber();
        getApartment();
        input.nextLine();
        getComment();
        println("Your order is issued");
        OrderActionService serv = new OrderActionService();
        String address = order.getCity() + " " + order.getHouseNumber() + " " + order.getApartment(); //TODO set address from order
        serv.createOrder(user.getEmail(), order.getFilling(), address, order.getComment());
        println(forBeautiesFooter);
        CommandsForConsoleApplication.getCommandsForConsoleApplication().readingCommand();
    }

    
    
    private void priceChange(int sum) {
        order.setTotalPrice(order.getTotalPrice() + sum);// доделать!!!! извлекать стоимость из бд
        println("Present value: " + order.getTotalPrice());
    }
    private void getCity(){
        try {
            
            print("City: ");
            order.setCity(input.nextLine());
            
        } catch (Exception ex) {
            println("Invalid value. Try to enter again.");
            getCity();
        }
    }
    
    private void getStreet(){
        try {
             print("Street: ");
            order.setStreet(input.nextLine());
        } catch (Exception ex) {
            println("Invalid value. Try to enter again.");
            getStreet();
        }
    }
    
    private void getHouseNumber(){
        try {
             print("House Number: ");
            order.setHouseNumber(input.next());
        } catch (Exception ex) {
            println("Invalid value. Try to enter again.");
            getHouseNumber();
        }
    }
    
    private void getApartment(){
        try {
             print("Apartment: ");
            order.setApartment(input.nextInt());
        } catch (Exception ex) {
            println("Invalid value. Try to enter again.");
            getApartment();
        }
    }
    
    private void getComment(){
        try {
            print("Leave a comment: ");
            order.setComment(input.nextLine());
        } catch (Exception ex) {
            println("Invalid value. Try to enter again.");
            getComment();
        }
    }
}
