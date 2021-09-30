package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.user.UserDataResponse;
import api.shopping.cart.domain.dto.user.UserResponse;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
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

    public UserDataResponse userDataToUserResponse(UserData userData) {
        UserDataResponse userDataResponse = new UserDataResponse();
        if (userData.getId() != null) userDataResponse.setId(userData.getId());
        if (userData.getName() != null) userDataResponse.setName(userData.getName());
        if (userData.getLastName() != null) userDataResponse.setLastName(userData.getLastName());
        if (userData.getPhone() != null) userDataResponse.setCellphone(userData.getPhone());
        if (userData.getAddress() != null) userDataResponse.setAddress(userData.getAddress());
        if (userData.getState() != null) {
            userDataResponse.setState(userData.getState().getStateName());
            userDataResponse.setCountry(userData.getState().getCountry().getName());
        }
        if (userData.getUser() != null) {
            userDataResponse.setEmail(userData.getUser().getEmail());
        }
        return userDataResponse;
    }
}
