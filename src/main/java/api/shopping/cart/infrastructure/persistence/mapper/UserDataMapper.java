package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.user.UserDataRequest;
import api.shopping.cart.infrastructure.persistence.entity.State;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import org.springframework.stereotype.Component;

/**
 * UserDataMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class UserDataMapper {


    public UserData UserDataRequestToUserData(UserDataRequest userDataRequest, User user, State state) {
        UserData userData = new UserData();

        if (userDataRequest.getNid() != null) userData.setNid(userDataRequest.getNid());
        if (userDataRequest.getName() != null) userData.setName(userDataRequest.getName());
        if (userDataRequest.getLastName() != null) userData.setLastName(userDataRequest.getLastName());
        if (userDataRequest.getCellphone() != null) userData.setPhone(userDataRequest.getCellphone());
        if (userDataRequest.getAddress() != null) userData.setAddress(userDataRequest.getAddress());
        if (state != null) userData.setState(state);
        if (user != null) userData.setUser(user);

        return userData;
    }
}
