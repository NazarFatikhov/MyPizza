package buu.mypizza.mappers;

import buu.mypizza.dto.OrderDTO;
import buu.mypizza.dto.ProductDTO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.models.Order;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
public class OrderMapper implements ThreeMapper<OrderDTO, List<ProductDTO>, UserDTO, Order> {

    @Override
    public Order map(OrderDTO orderDTO, List<ProductDTO> productDTOs, UserDTO userDTO) {
        ProductDTOMapper productMapper = new ProductDTOMapper();
        UserDTOMapper userMapper = new UserDTOMapper();
        List<Product> products = productMapper.mapList(productDTOs);
        User user = userMapper.map(userDTO);
        Order order = new Order(orderDTO.getId(), user, products, orderDTO.getPrice(), orderDTO.getDate(), orderDTO.getAddress(), orderDTO.getComment());
        return order;
    }

    @Override
    public List<Order> mapList(List<OrderDTO> orderDtos, List<ProductDTO> productDtos, UserDTO user) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDto : orderDtos){
            orders.add(map(orderDto, productDtos, user));
        }
        return orders;
    }
    
    
}
