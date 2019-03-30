package buu.mypizza.dto;

import java.sql.Date;

/**
 *
 * @author nazar
 */
public class OrderDTO {
    
    private int id;
    private int userId;
    private double price;
    private Date date;
    private String address;
    private String comment;

    public OrderDTO() {
    }

    public OrderDTO(int id, int user_id, double price, Date date, String address, String comment) {
        this.id = id;
        this.userId = user_id;
        this.price = price;
        this.date = date;
        this.address = address;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" + "id=" + id + ", user_id=" + userId + ", price=" + price + ", date=" + date + ", address=" + address + ", comment=" + comment + '}';
    }
    
    
    
            
    
}
