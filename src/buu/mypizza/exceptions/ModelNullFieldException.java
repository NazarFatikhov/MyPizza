/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.exceptions;

/**
 *
 * @author nazar
 */
public class ModelNullFieldException extends Exception{

    @Override
    public String getMessage() {
        return "You have empty inputs"; 
    }
    
    
    
}
