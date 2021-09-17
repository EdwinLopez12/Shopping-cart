package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.Country;

/**
 * CountryRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppincart
 * @since v1.0.0 - sep. 2021
 */
public interface CountryRepository {

    void save(Country country);
}
