package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.privilege.PrivilegeResponse;
import api.carrito.compras.domain.exception.PageableDataResponseModel;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.repository.PrivilegeRepository;
import api.carrito.compras.domain.usecase.PrivilegeService;
import api.carrito.compras.infrastructure.RoutesMapping;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.carrito.compras.infrastructure.persistence.mapper.PrivilegeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
