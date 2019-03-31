package buu.mypizza.mappers;

import buu.mypizza.dto.OrderDTO;
import buu.mypizza.models.Order;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nazar
 */
public class OrderDTOMapper implements BiMapper<Order, Integer, OrderDTO>{

    @Override
    public OrderDTO map(Order order, Integer userId) {
        OrderDTO orderDto = new OrderDTO(order.getOrdeNum(), userId, order.getPrice(), 
                new Date(order.getDate().getTime()), order.getAddress(), order.getComment());
        return orderDto;
    }

    @Override
    public List<OrderDTO> mapList(List<Order> fromList1, List<Integer> fromList2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    
}
