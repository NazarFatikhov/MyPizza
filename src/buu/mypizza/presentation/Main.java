package buu.mypizza.presentation;

import buu.mypizza.dto.OrderDTO;
import buu.mypizza.exceptions.DBException;
import buu.mypizza.mappers.BiMapper;
import buu.mypizza.mappers.OrderDTOMapper;
import buu.mypizza.repositorys.OrderRepository;
import buu.mypizza.models.Order;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import buu.mypizza.repositorys.UserRepository;
import buu.mypizza.services.OrderActionService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nazar
 */
public class Main {
    
    public static void main(String[] args) throws DBException {
        CommandsForConsoleApplication.getCommandsForConsoleApplication().welcome(); 
    }
}
