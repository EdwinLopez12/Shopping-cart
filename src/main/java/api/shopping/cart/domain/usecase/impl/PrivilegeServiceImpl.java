package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.privilege.PrivilegeRequest;
import api.shopping.cart.domain.dto.privilege.PrivilegeResponse;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.repository.PrivilegeRepository;
import api.shopping.cart.domain.usecase.PrivilegeService;
import api.shopping.cart.infrastructure.RoutesMapping;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.PrivilegeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PrivilegeServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */

@Service
@AllArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private static final String PRIVILEGE_NOT_FOUND = "The privilege doesn't exist or couldn't be found";

    private final PrivilegeRepository privilegeRepository;
    private final GeneralResponseModelMapper generalMapper;
    private final PrivilegeMapper privilegeMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Privilege> p = privilegeRepository.findAll(pageable);
        return pageable(p, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Privilege> p, String type){

        List<PrivilegeResponse> privileges = privilegeMapper.privilegeListToPrivilegeResponse(p.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed privileges", privileges, p.getSize(), p.getTotalPages(), p.getTotalElements(), p.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(p.getPageable().hasPrevious(), p.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_PRIVILEGES_V1, pageableData);
    }

    @Override
    public List<Privilege> privilegesRequestToPrivilege(List<PrivilegeRequest> privileges) {
        List<Privilege> privilegeList = new ArrayList<>();
        for (PrivilegeRequest privilegeRequest : privileges) {
            Privilege privilege = privilegeRepository.findByName(privilegeRequest.getName()).orElseThrow(() -> new ApiNotFoundException(PRIVILEGE_NOT_FOUND));
            privilegeList.add(privilege);
        }
        return privilegeList;
    }
}
