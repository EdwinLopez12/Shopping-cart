package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.state.StateResponse;
import api.shopping.cart.infrastructure.persistence.entity.State;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * StateMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class StateMapper {

    public StateResponse stateToStateResponse(State state) {
        StateResponse stateResponse = new StateResponse();
        if (state.getId() != null) stateResponse.setId(state.getId());
        if (state.getRegion() != null) stateResponse.setRegion(state.getRegion());
        if (state.getDaneCodeState() != null) stateResponse.setStateCodeDane(state.getDaneCodeState());
        if (state.getStateName() != null) stateResponse.setState(state.getStateName());
        if (state.getDaneCodeTown() != null) stateResponse.setTownCodeDane(state.getDaneCodeTown());
        if (state.getTownName() != null) stateResponse.setTown(state.getTownName());;
        return stateResponse;
    }

    public List<StateResponse> stateListToStateResponseList(List<State> states) {
        List<StateResponse> stateResponses = new ArrayList<>();
        for (State state : states) {
            stateResponses.add(stateToStateResponse(state));
        }
        return stateResponses;
    }
}
