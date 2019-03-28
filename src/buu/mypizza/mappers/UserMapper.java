package buu.mypizza.mappers;

import buu.mypizza.dto.UserDTO;
import buu.mypizza.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
public class UserMapper implements Mapper<User, UserDTO>{

    @Override
    public UserDTO map(User user) {
        UserDTO dto = new UserDTO(user.getEmail(), user.getPassword());
        return dto;
    }
    
    @Override
    public List<UserDTO> mapList(List<User> userList) {
        List<UserDTO> list = new ArrayList<>();
        for (User user : userList){
            UserDTO userDTO = map(user);
            list.add(userDTO);
        }
        return list;
    }
}
