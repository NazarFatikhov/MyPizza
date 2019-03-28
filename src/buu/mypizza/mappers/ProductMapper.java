package buu.mypizza.mappers;

import buu.mypizza.dto.ProductDTO;
import buu.mypizza.models.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
public class ProductMapper implements Mapper<Product, ProductDTO>{

    @Override
    public ProductDTO map(Product product) {
        ProductDTO dto = new ProductDTO(product.getName(), product.getPrice(), product.getBalance());
        return dto;
    }

    @Override
    public List<ProductDTO> mapList(List<Product> products) {
        List<ProductDTO> list = new ArrayList<>();
        for (Product product : products){
            ProductDTO dto = map(product);
            list.add(dto);
        }
        return list;
    }
    
    
    
}
