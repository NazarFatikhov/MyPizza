/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.dao;

import buu.mypizza.models.Admin;
import buu.mypizza.models.Client;
import buu.mypizza.models.User;
import buu.mypizza.db.Db;
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
public class UserDAO implements DAO<User>{
    
    @Override
    public Optional<User> get(long id) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String[] fields = {resultSet.getString("email"), 
                    resultSet.getString("pass")};
            User user = null;
            if(resultSet.getBoolean("is_admin")){
                user = new Admin(fields);
            }else{
                user = new Client(fields); 
            }
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    @Override
    public List getAll() {
        try{
            List<User> users = new ArrayList<>();
            Connection conn = Db.getConnection();
            Statement state = conn.createStatement();
            ResultSet resSet = state.executeQuery("SELECT * FROM users;");
            while (resSet.next()) {
                User user;
                String[] fields = {resSet.getString("email"), 
                    resSet.getString("pass")};
                if(resSet.getBoolean("is_admin")){
                    user = new Admin(fields);
                }else{
                    user = new Client(fields);
                }
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList();
        
    }    

    @Override
    public void save(User user) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("INSERT INTO users (email, pass) VALUES (?, ?);");
            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.execute();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User user, String[] params) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("UPDATE users SET (email, pass) = (?, ?) WHERE email = ?;");
            st.setString(1, params[0]);
            st.setString(2, params[1]);
            st.setString(3, user.getEmail());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User user) {
        try(Connection conn = Db.getConnection()){
            PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE email = ?;");
            st.setString(1, user.getEmail());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
