package ee.swan.importSelect.config;

import ee.swan.importSelect.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BConfig {
    @Bean
    MyBean myBean() {
        return new MyBean("from BConfig");
    }
}
