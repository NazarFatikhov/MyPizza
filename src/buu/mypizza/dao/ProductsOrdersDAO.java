package buu.mypizza.dao;

import buu.mypizza.db.Db;
import buu.mypizza.dto.ProductDTO;
import buu.mypizza.dto.ProductsOrdersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nazar
 */
public class ProductsOrdersDAO implements DAO<ProductsOrdersDTO> {

    @Override
    public Optional get(long id) {
        ProductsOrdersDTO productsOrdersDTO = null;
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products_orders WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                productsOrdersDTO = new ProductsOrdersDTO(resultSet.getInt("id"), resultSet.getInt("order_id"), resultSet.getInt("product_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(productsOrdersDTO);
    }
    
    @Override
    public List getAll() {
        List<ProductsOrdersDTO> productsOrdersList = new ArrayList<>();
        Connection conn;
        try{
            conn = Db.getConnection();
            Statement state = conn.createStatement();
            ResultSet resSet = state.executeQuery("SELECT * FROM products_orders;");
            while (resSet.next()) {
                ProductsOrdersDTO productsOrders = new ProductsOrdersDTO(resSet.getInt("id"), resSet.getInt("order_id"), resSet.getInt("product_id"));
                productsOrdersList.add(productsOrders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productsOrdersList;
    }

    @Override
    public void save(ProductsOrdersDTO productsOrdersDTO) {
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO products_orders (order_id, product_id) VALUES (?, ?);");
            st.setInt(1, productsOrdersDTO.getOrderId());
            st.setInt(2, productsOrdersDTO.getProductId());
            st.execute();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ProductsOrdersDTO productsOrdersDTO, String[] params) {
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement st = conn.prepareStatement("UPDATE products_orders SET (order_id, product_id) = (?, ?) WHERE id = ?;");
            st.setInt(1, Integer.parseInt(params[0]));
            st.setInt(2, Integer.parseInt(params[1]));
            st.setInt(4, productsOrdersDTO.getId());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(ProductsOrdersDTO productsOrdersDTO) {
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM products_orders WHERE id = ?;");
            st.setInt(1, productsOrdersDTO.getId());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
