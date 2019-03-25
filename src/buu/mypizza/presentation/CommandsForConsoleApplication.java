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
public class CommandsForConsoleApplication {
    Scanner input = new Scanner(System.in);
    private String header ="<MYPIZZA>";
    private String space ="         "; //для красоты
    private String command;
    
    //вывод приветсвия для старта программы
    public void welcome(){
        System.out.println(header+"Welcome to MYPIZZA \n"
                +space+ "For Help input \'-h\'"); 
        System.out.println(space+"If you are registered enter 'login'"); 
        System.out.println(space+"Otherwise register, enter 'reg'");
        readingCommand();
    }

    public String getHeader() {
        return header;
    }

    //измемнение хедера, как хотел Назар + это можно получать из класса НАЗАРА ПОЛЬЗОВАТЕЛЬ.toString
    public void setHeader(User user) { //******** я не помню как называется твой класс пользователя****************
        this.header= user.toString();
    }
    
    //чтение введенных команд
    public void readingCommand(){
        //String command = input.next();
        System.out.print(header);
        switch (command = input.next()) {
           case  ("reg"):
               RegistrationConsoleApplication rca = new RegistrationConsoleApplication();
               rca.start();
               break;
           case ("login"):
               LoginConsoleApplication lca = new LoginConsoleApplication();
               lca.start();
               break;
           case ("exit"):
               System.exit(0);
               break;
           case ("-h"):
               System.out.println(header+"'reg' - registration\n"+space+"'login' - login\n"+space+"'exit' - exit");
               readingCommand();
               break;
           default:
               System.out.println(header+"Sorry, I don't know this ocommand.\nTry to enter again.");
               readingCommand();
               break;
       }
        
    }
    
    //реагирование на определенную команду
    
    
}
