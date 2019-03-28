package buu.mypizza.dto;

/**
 *
 * @author nazar
 */
public class UserDTO {
    
    private int id;
    private String email;
    private String pass;
    private boolean is_admin;

    public UserDTO(int id, String email, String pass, boolean is_admin) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.is_admin = is_admin;
    }

    public UserDTO(String email, String pass, boolean is_admin) {
        this.email = email;
        this.pass = pass;
        this.is_admin = is_admin;
    }
    

    public UserDTO(String email, String pass){
        this.email = email;
        this.pass = pass;
    }
    
    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", email=" + email + ", pass=" + pass + ", is_admin=" + is_admin + '}';
    }
    
    
}
