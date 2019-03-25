/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.presentation;

import buu.mypizza.exceptions.ModelNullFieldException;
import buu.mypizza.exceptions.UserDublicateException;
import buu.mypizza.models.Client;
import buu.mypizza.models.User;
import buu.mypizza.services.SecurityService;
import java.util.Scanner;

/**
 *
 * @author Kseniia
 */
public class RegistrationConsoleApplication implements  AuthorizationConsoleApplicationInterfase {
    Scanner input = new Scanner(System.in);
    //private List<String> list = new ArrayList<String>();
    private String email;
    private String password;
    private User user;
    
    private String header = "<MYPIZZA>";

    public void setHeader(String header) {
        this.header = header;
    }
    
    
    
    
    
    public RegistrationConsoleApplication() {   }
    
    
    
    @Override
    public void start(){
        println(beautiesTop);
       formFilling();
    }
    
    @Override
    public void formFilling(){
        try{
            print("Enter email:");
            email = input.next();
            if(isValidEmailAddress(email)){
                print("Enter password:");
                password = input.next();
                print("Confirm password:");
                if(isValidPassword(input.next())){
                    createUser(email,password);    
                } else{
                    println("Registration failed: passwords do not match, please try again");
                    readCommands();
                }              
            }
            else{
                println("This email does not exist");
                readCommands();
            }   
        }
        catch(Exception ex){
            println("Registration failed, please try again");
            readCommands();
        }
    }
    
     public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
     public boolean isValidPassword(String pass){
         return password.equals(pass);
     }

     
    @Override
     public void readCommands(){
        print(header);
        String command;
        switch (command = input.next()) {
            case ("reg"):
                formFilling();
                break;
            case ("login"):
                LoginConsoleApplication lca = new LoginConsoleApplication();
                lca.start();
                break;
            case ("exit"):
                System.exit(0);
                break;
            case ("-h"):
                System.out.println(header + "'reg' - registration\n" + space + "'login' - login\n" + space + "'exit' - exit");
                readCommands();
                break;
            default:
                System.out.println(header + "Sorry, I don't know this ocommand.\nTry to enter again.");
                readCommands();
                break;
    } 
}
    @Override
    public void createUser(String email, String password) {
        // *************создание пользователя********************
        user = new Client(email, password);//delete
        SecurityService secService = new SecurityService();
        try {
            secService.signUpUser(user);
            println("You have registered successfully!");
            setHeader(user.toString());
            OrderConsoleApplication oca = new OrderConsoleApplication(this.user);
            oca.start();
            println(beautiesFooter);
        } catch (UserDublicateException ex) {
            println(ex.getMessage());
            readCommands();
        } catch (ModelNullFieldException ex) {
            println(ex.getMessage());
            readCommands();
        }
    }

    @Override
    public void forwardTo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void print(String s){
        System.out.print(s);
    }
    private void println(String s){
        System.out.println(s);
    }
}
