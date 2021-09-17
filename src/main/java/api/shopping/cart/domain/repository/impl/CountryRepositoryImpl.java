package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.CountryRepository;
import api.shopping.cart.infrastructure.persistence.entity.Country;
import api.shopping.cart.infrastructure.persistence.jpa.CountryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * CountryRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final CountryJpaRepository countryJpaRepository;

    @Override
    public void save(Country country) {
        countryJpaRepository.save(country);
    }
}
