package api.shopping.cart.app.boot;

import api.shopping.cart.domain.repository.CountryRepository;
import api.shopping.cart.infrastructure.persistence.entity.Country;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * ApiCountrySetUpDataLoader class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
@AllArgsConstructor
public class ApiCountrySetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CountryRepository countryRepository;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        File jsonFile = ResourceUtils.getFile("classpath:static/countries.json");
        // Read data
        String json = FileUtils.readFileToString(jsonFile);
        JSONArray jsonArray = JSON.parseArray(json);
        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;
            String name = jsonObject.getString("name");
            String iso2 = jsonObject.getString("iso2");
            String iso3 = jsonObject.getString("iso3");
            String phoneCode = jsonObject.getString("phone_code");

            Country country = Country.builder()
                    .name(name)
                    .iso2(iso2)
                    .iso3(iso3)
                    .phoneCode(phoneCode)
                    .build();

            countryRepository.save(country);
        }
    }
}
