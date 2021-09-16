package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.user.UserResponse;
import api.shopping.cart.infrastructure.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class UserMapper {

    public UserResponse userToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if (user.getId() != null) userResponse.setId(user.getId());
        if (user.getUsername() != null) userResponse.setUsername(user.getUsername());
        if (user.getEmail() != null) userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public List<UserResponse> userListToUserResponseList(List<User> userList) {
        List<UserResponse> users = new ArrayList<>();
        for (User user : userList) {
            users.add(userToUserResponse(user));
        }
        return users;
    }
}
