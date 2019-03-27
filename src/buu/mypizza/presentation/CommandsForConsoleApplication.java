import buu.mypizza.models.Admin;
import buu.mypizza.presentation.OrderConsoleApplication;
import buu.mypizza.services.SecurityService;
import java.util.Scanner;

/**
 *
 * @author Kseniia
 */
public class CommandsForConsoleApplication {
    private static CommandsForConsoleApplication cmd;
    private CommandsForConsoleApplication(){}
    public static CommandsForConsoleApplication getCommandsForConsoleApplication(){
        if(cmd == null){	
            cmd = new CommandsForConsoleApplication();	
        }
        return cmd;	
    }
    
    
        
    private Scanner input = new Scanner(System.in);
    private String header ="<MYPIZZA>";
    private String space ="         "; //для красоты
    private String command;
    
    
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
    
    
    public void setHeader(String s) {
        this.header= s;
    }
    
    //чтение введенных команд
    public void readingCommand(){
        //String command = input.next();
        SecurityService securityService;
        System.out.print(header);
        switch (command = input.nextLine()) {
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
               System.out.println(header+"'reg' - registration\n"+
                       space+"'login' - login\n"+
                       space+"'exit' - exit\n"+
                       space+"'logout' - logout\n"+
                       space+"'create order' - create order\n");
               readingCommand();
               break;
            case ("logout"):
                securityService = SecurityService.newInstance();
                if(securityService.isLoggedUser()){ 
                    SecurityService secServ = SecurityService.newInstance();
                secServ.signOutUser();
                setHeader("<MYPIZZA>");
                //Some changes
                }
                else{
                    System.out.println(header+"Sorry, you are not logged in.\nTry to enter again.");
                    readingCommand();                 
                }
                break;
            case ("create order"):
                securityService = SecurityService.newInstance();
                if(securityService.isLoggedUser()){
                    forwardTo();
                }
                else{
                    System.out.println(header+"Sorry, you are not logged in.\nTry to enter again.");
                    readingCommand();
                }
                break;
           default:
               System.out.println(header+"Sorry, I don't know this ocommand.\nTry to enter again.");
               readingCommand();
               break;
       }
        
    }
    
     public void forwardTo() {
        SecurityService securityService = SecurityService.newInstance(); 
        if(securityService.getLoggedUser().getClass().equals(Admin.class)){ //!!!!!!!!!!! user == admin?????
            AdministrationVlogConsoleApplication admin = new AdministrationVlogConsoleApplication(securityService.getLoggedUser());
            admin.start();
        }else{
            OrderConsoleApplication oca = new OrderConsoleApplication(securityService.getLoggedUser()); // input user
            oca.start();
        }
    }
    } 
    
