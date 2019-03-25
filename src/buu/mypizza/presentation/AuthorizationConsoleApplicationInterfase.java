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
public interface AuthorizationConsoleApplicationInterfase {
    final String  beautiesTop ="*****************Pizza*****************";
    final String  beautiesFooter="_______________________________________";
    void start();
    void createUser(String email, String password);
    void forwardTo();
    void formFilling();
    
    final String header = "<MYPIZZA>";
    final String space =" "; //для красоты 
    void readCommands(); 
}

