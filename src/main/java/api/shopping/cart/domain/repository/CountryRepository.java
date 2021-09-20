package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.Country;

import java.util.Optional;

/**
 * CountryRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppincart
 * @since v1.0.0 - sep. 2021
 */
public interface CountryRepository {

    Optional<Country> findByName(String country);
    void save(Country country);
}
