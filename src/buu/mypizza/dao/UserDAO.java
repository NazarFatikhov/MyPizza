/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.dao;

import buu.mypizza.db.Db;
import buu.mypizza.dto.UserDTO;
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
public class UserDAO implements DAO<UserDTO>{
    
    @Override
    public Optional<UserDTO> get(long id) {
        UserDTO user = null;
        try(Connection conn = Db.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user = new UserDTO(resultSet.getInt("id"), resultSet.getString("email"), 
                    resultSet.getString("pass"), resultSet.getBoolean("is_admin"));
            }
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List getAll() {
        List<UserDTO> users = new ArrayList<>();
        try(Connection conn = Db.getConnection()){
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                UserDTO user = new UserDTO(resultSet.getInt("id"), resultSet.getString("email"), 
                            resultSet.getString("pass"), resultSet.getBoolean("is_admin"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
        
    }    

    @Override
    public void save(UserDTO user) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("INSERT INTO users (email, pass) VALUES (?, ?);");
            st.setString(1, user.getEmail());
            st.setString(2, user.getPass());
            st.execute();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(UserDTO user, String[] params) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("UPDATE users SET (email, pass) = (?, ?) WHERE id = ?;");
            st.setString(1, params[0]);
            st.setString(2, params[1]);
            st.setInt(3, user.getId());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(UserDTO user) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE id = ?;");
            st.setInt(1, user.getId());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
