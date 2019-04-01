package buu.mypizza.presentation;

import buu.mypizza.exceptions.UserDublicateLoggedException;
import buu.mypizza.exceptions.IncorrectPasswordException;
import buu.mypizza.exceptions.UserNotFoundException;
import buu.mypizza.models.Client;
import buu.mypizza.models.User;
import buu.mypizza.services.SecurityService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kseniia
 */
public class LoginConsoleApplication implements  AuthorizationConsoleApplicationInterfase{
    Scanner input = new Scanner(System.in);
    private List<String> list = new ArrayList<String>();
    private String email;
    private String password;
    private User user;
    private String header ="<MYPIZZA>"; 

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    @Override
    public void start(){
        println(beautiesTop);
       formFilling();
       
    }
     private boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

    @Override
    public void createUser(String email, String password) {
        // *************создание пользователя********************
        user = new Client(email, password);
        SecurityService secServ = SecurityService.newInstance();
        try {
            secServ = SecurityService.newInstance();
            secServ.signInUser(email, password);
            println("You have logined successfully!");
            setHeader(user.toString());
            CommandsForConsoleApplication.getCommandsForConsoleApplication().setHeader(user.toString());
            println(beautiesFooter);
            readCommands();
        } catch (UserDublicateLoggedException ex) {
            println(ex.getMessage());
            readCommands();
        } catch (IncorrectPasswordException ex) {
            println(ex.getMessage());
            readCommands();
        } catch (UserNotFoundException ex) {
            println(ex.getMessage());
            readCommands();
        } 
         
    }

   
    

    @Override
    public void formFilling() {
        print("Enter email:");
        email = input.next();
        if (isValidEmailAddress(email)) {
            print("Enter password:");
            password = input.next();
            createUser(email, password);
        } 
        else{
            println("This email does not exist");
            readCommands();
        }
    }
    
    @Override 
public void readCommands(){ 
     CommandsForConsoleApplication.getCommandsForConsoleApplication().readingCommand();
}
    
    private void print(String s){
        System.out.print(s);
    }
    private void println(String s){
        System.out.println(s);
    }
      
}
   
