package buu.mypizza.mappers;

import buu.mypizza.dto.ProductDTO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.models.Admin;
import buu.mypizza.models.Client;
import buu.mypizza.models.Product;
import buu.mypizza.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
public class UserDTOMapper implements Mapper<UserDTO, User>{
    
    public User map(UserDTO dto){
        if(dto.isAdmin()) {
            return new Admin(dto.getEmail(), dto.getPass());
        }
        else{
            return new Client(dto.getEmail(), dto.getPass());
        }
    }

    @Override
    public List<User> mapList(List<UserDTO> dtoList) {
        List<User> list = new ArrayList<>();
        for (UserDTO dto : dtoList){
            User user = map(dto);
            list.add(user);
        }
        return list;
    }
    
    
    
}
