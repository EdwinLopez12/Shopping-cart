package api.shopping.cart.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket shoppingCart(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getShoppingCartApiInfo());
    }

    private ApiInfo getShoppingCartApiInfo(){
        return new ApiInfoBuilder()
                .title("Shopping Cart")
                .version("V1.0.0")
                .description(" Shopping cart with paypal integration")
                .contact(new Contact("Edwin Lopez", "http://github.com/EdwinLopez12", "edwin.lopezb.1297@email.com"))
                .license("MIT License")
                .build();
    }
}
