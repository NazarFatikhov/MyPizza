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
    void formFilling();
    
    final String header = "<MYPIZZA>";
    final String space =" "; //для красоты 
    void readCommands(); 
}
