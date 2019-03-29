package buu.mypizza.presentation;

import buu.mypizza.models.Admin;
import buu.mypizza.models.Client;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author Kseniia
 */
public class AdministrationVlogConsoleApplication {
    private enum Products{GARLIC, CHEESE,EXOTIC ,TOMATO ,CREAMY, Dough, Chicken, Bacon, Shrimp, Sausage, Pineapples, Tomatoes, Pepper, Broccoli, Onion, Olives};
    private final String space="         ";
    private Admin user;
     Scanner input = new Scanner(System.in);
    AdministrationVlogConsoleApplication(Admin user) {
        this.user=user;
    }

    public void start() {
        System.out.println("Enter the product number and quantity, separated by a space.");
        Stream.of(Products.values()).forEach(x->System.out.println(space+(x.ordinal())+" : "+x.toString()));
         System.out.println("To complete the order, enter \"the end\"");
         String id;
        String count;
        while (true) {
            System.out.print(user.toString());
            try {
                id = input.next();
                count = input.next();
                        
                if (id.equals("the") && count.equals("end")) {
                    readCommands();
                    break;
                    //noDone=false;
                } else {
                    if (0 <= Integer.parseInt(id) && Integer.parseInt(id) < Products.values().length) {
                        ProductsRepository rep = new ProductsRepository();
                        Product product = rep.get(Products.values()[Integer.parseInt(id)].toString());
                        String[] fields = {product.getName(), product.getPrice().toString(),
                            "" + (product.getBalance() + Integer.parseInt(count))};
                        rep.update(product, fields);
                        //************** db******************--->>>
                        //Products.values()[Integer.parseInt(id)].toString - название продукта
                        //Integer.parseInt(count) - количество, которое следует прибавить
                        
                    } else {
                        System.out.println("Sorry, I don't know this Filling.\nTry to enter again.");

                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Sorry, we're out of filling at the moment.\n Please choose another one.");
                start();
            }

        }
    }
    public void readCommands(){ 
         CommandsForConsoleApplication.getCommandsForConsoleApplication().readingCommand();
    }
    
}
