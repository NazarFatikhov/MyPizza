package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.OrderDAO;
import buu.mypizza.dao.ProductDAO;
import buu.mypizza.dao.ProductsOrdersDAO;
import buu.mypizza.dao.UserDAO;
import buu.mypizza.dto.OrderDTO;
import buu.mypizza.dto.ProductDTO;
import buu.mypizza.dto.ProductsOrdersDTO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.exceptions.DBException;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.mappers.OrderMapper;
import buu.mypizza.mappers.ProductDTOMapper;
import buu.mypizza.mappers.ThreeMapper;
import buu.mypizza.models.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
public class OrderRepository implements Repository<Order>{

    DAO orderDao;

    public OrderRepository() {
        this.orderDao = new OrderDAO();
    }

    public OrderRepository(DAO orderDao) {
        this.orderDao = orderDao;
    }
    
    @Override
    public boolean isExist(Order order) {
        List<OrderDTO> orderDtos = orderDao.getAll();
        for(OrderDTO orderDto : orderDtos){
            if(orderDto.getId() == order.getOrdeNum()){
                return true;
            }
        }
        return false;
    }
    
    private Order getOrder(OrderDTO orderDto) throws DBException{
        DAO poDao = new ProductsOrdersDAO();
        DAO pDao = new ProductDAO();
        DAO uDao = new UserDAO();
        UserDTO user = (UserDTO) uDao.get(orderDto.getUserId()).get();
        Mapper mapper = new ProductDTOMapper();
        ThreeMapper orderMapper = new OrderMapper();
        List<ProductDTO> productDtos = new ArrayList<>();
        List<ProductDTO> allProductDtos = pDao.getAll();
        List<ProductsOrdersDTO> poDtos = poDao.getAll();
        for(ProductDTO productDto : allProductDtos){
            for(ProductsOrdersDTO poDto : poDtos){
                if(productDto.getId() == poDto.getProductId() && orderDto.getId() == poDto.getOrderId()){
                    productDtos.add(productDto);
                    break;
                }
            }
        }
        Order order = (Order) orderMapper.map(orderDto, productDtos, user);
        if(isExist(order)){
            return order;
        }
        else{
            return null;
        }
    }

    @Override
    public Order getByStringKey(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Order t, String[] fields) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
