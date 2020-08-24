package ee.swan.importSelect.config;

import ee.swan.importSelect.UseMyBean;
import ee.swan.importSelect.annotation.EnableAutoModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoModule("someValue")
public class MainConfig {

    @Bean
    UseMyBean useMyBean() {
        return new UseMyBean();
    }
}
