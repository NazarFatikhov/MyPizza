
package buu.mypizza.dto;

/**
 *
 * @author nazar
 */
public class ProductsOrdersDTO {
    
    private int id;
    private int orderId;
    private int productId;

    public ProductsOrdersDTO(int id, int orderId, int productId) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
    }

    public ProductsOrdersDTO() {
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductsOrdersDTO{" + "id=" + id + ", orderId=" + orderId + ", productId=" + productId + '}';
    }
    
    
     
}
