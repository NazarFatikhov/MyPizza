package buu.mypizza.dao;

import buu.mypizza.db.Db;
import buu.mypizza.dto.ProductDTO;
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
public class ProductDAO implements DAO<ProductDTO>{

    public ProductDAO() {
    }

    @Override
    public Optional<ProductDTO> get(long id) {
        ProductDTO productDTO = null;
        try(Connection conn = Db.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                productDTO = new ProductDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("balance")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(productDTO);
    }

    @Override
    public List getAll() {
        List<ProductDTO> products = new ArrayList<>();
        try(Connection conn = Db.getConnection()){
            Statement state = conn.createStatement();
            ResultSet resSet = state.executeQuery("SELECT * FROM products;");
            while (resSet.next()) {
                ProductDTO product = new ProductDTO(resSet.getInt("id"), resSet.getString("name"), resSet.getFloat("price"), resSet.getInt("balance"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
        
    }

    @Override
    public void save(ProductDTO product) {
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
    public void update(ProductDTO product, String[] params) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("UPDATE products SET (name, price, balance) = (?, ?, ?) WHERE id = ?;");
            st.setString(1, params[0]);
            st.setDouble(2, Double.parseDouble(params[1]));
            st.setInt(3, Integer.parseInt(params[2]));
            st.setInt(4, product.getId());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(ProductDTO product) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("DELETE FROM products WHERE name = ?;");
            st.setString(1, product.getName());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
