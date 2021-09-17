package api.shopping.cart.app.boot;

import api.shopping.cart.domain.repository.StateRepository;
import api.shopping.cart.infrastructure.persistence.entity.State;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * ApiStatesAndTownsSetUpDataLoader class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */
@Component
@AllArgsConstructor
@Order(2)
public class ApiStatesAndTownsSetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final StateRepository stateRepository;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        File jsonFile = ResourceUtils.getFile("classpath:static/Colombia/StatesAndTownsColombia.json");
        // Read data
        String json = FileUtils.readFileToString(jsonFile);
        JSONArray jsonArray = JSON.parseArray(json);
        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;
            String region = jsonObject.getString("region");
            String daneCodeState = jsonObject.getString("c_digo_dane_del_departamento");
            String stateName = jsonObject.getString("departamento");
            String daneCodeTown = jsonObject.getString("c_digo_dane_del_municipio");
            String townName = jsonObject.getString("municipio");

            State state = State.builder()
                    .region(region)
                    .daneCodeState(daneCodeState)
                    .stateName(stateName)
                    .daneCodeTown(daneCodeTown)
                    .townName(townName)
                    .build();

            stateRepository.save(state);
        }
    }
}
