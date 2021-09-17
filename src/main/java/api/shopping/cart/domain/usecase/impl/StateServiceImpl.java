package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.state.StateResponse;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.StateRepository;
import api.shopping.cart.domain.usecase.StateService;
import api.shopping.cart.infrastructure.persistence.entity.State;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.StateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * StateServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

    private final GeneralResponseModelMapper generalMapper;
    private final StateMapper stateMapper;
    private final StateRepository stateRepository;

    @Override
    public GeneralResponseModel listTownsByCountry(String country) {
        List<State> states = stateRepository.findAllByCountry(country);
        List<StateResponse> stateResponses = stateMapper.stateListToStateResponseList(states);
        return generalMapper.responseToGeneralResponseModel(200, "List towns", "Towns listed", Collections.singletonList(stateResponses), "Ok");
    }
}
