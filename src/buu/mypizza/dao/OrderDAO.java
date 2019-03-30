package buu.mypizza.dao;

import buu.mypizza.db.Db;
import buu.mypizza.dto.OrderDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nazar
 */
public class OrderDAO implements DAO<OrderDTO>{

    @Override
    public Optional<OrderDTO> get(long id) {
        OrderDTO orderDTO = null;
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setLong(1, id);
            ResultSet orderSet = statement.executeQuery();
            while (orderSet.next()) {
               orderDTO = new OrderDTO(
                    orderSet.getInt("id"),
                    orderSet.getInt("user_id"),
                    orderSet.getDouble("price"),
                    orderSet.getDate("date"),
                    orderSet.getString("address"),
                    orderSet.getString("comment"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(orderDTO);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> orders = new ArrayList<>();
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                orders.add(new OrderDTO(resultSet.getInt("id"), resultSet.getInt("user_id"), resultSet.getFloat("price"),
                    resultSet.getDate("date"), resultSet.getString("address"), resultSet.getString("comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    @Override
    public void save(OrderDTO order) {
        Connection conn ;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO orders (user_id, price, date, address, comment) "
                    + "VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, order.getUserId());
            statement.setDouble(2, order.getPrice());
            statement.setDate(3, order.getDate());
            statement.setString(4, order.getAddress());
            statement.setString(5, order.getComment());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(OrderDTO order, String[] params) {
        Connection conn;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE orders SET (user_id, price, date, address, comment) = (?, ?, ?, ?, ?) "
                    + "WHERE id = ?;");
            statement.setInt(6, order.getId());
            statement.setInt(1, Integer.parseInt(params[0]));
            statement.setDouble(2, Double.parseDouble(params[1]));
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            Date parsed = new Date(format.parse(params[2]).getTime());
            statement.setDate(3, parsed);
            statement.setString(4, params[3]);
            statement.setString(5, params[4]);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(OrderDTO order) {
        Connection conn ;
        try{
            conn = Db.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM orders WHERE id = ?");
            statement.setInt(1, order.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
    
    
