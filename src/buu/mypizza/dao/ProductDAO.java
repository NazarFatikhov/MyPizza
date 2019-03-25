package buu.mypizza.dao;

import buu.mypizza.db.Db;
import buu.mypizza.models.Admin;
import buu.mypizza.models.Client;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
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
public class ProductDAO implements DAO<Product>{

    @Override
    public Optional get(long id) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String[] fields = {resultSet.getString("name"), 
                    resultSet.getString("price"), resultSet.getString("balance")};
            Product product = new Product(fields);
            return Optional.ofNullable(product);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    @Override
    public List getAll() {
        try(Connection conn = Db.getConnection()){
            List<Product> products = new ArrayList<>();
            Statement state = conn.createStatement();
            ResultSet resSet = state.executeQuery("SELECT * FROM products;");
            while (resSet.next()) {
                String[] fields = {resSet.getString("name"), 
                    resSet.getString("price"), resSet.getString("balance")};
                Product product = new Product(fields);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList();
        
    }

    @Override
    public void save(Product product) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("INSERT INTO products (name, price, balance) VALUES (?, ?, ?);");
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getBalance());
            st.execute();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product product, String[] params) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("UPDATE products SET (name, price, balance) = (?, ?, ?) WHERE name = ?;");
            st.setString(1, params[0]);
            st.setDouble(2, Double.parseDouble(params[1]));
            st.setInt(3, Integer.parseInt(params[2]));
            st.setString(4, product.getName());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Product product) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("DELETE FROM products WHERE name = ?;");
            st.setString(1, product.getName());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
