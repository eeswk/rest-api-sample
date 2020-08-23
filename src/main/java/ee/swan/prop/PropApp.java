package ee.swan.prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({MyProperties.class})
public class PropApp {
    public static void main(String[] args) {
        SpringApplication.run(PropApp.class, args);
    }
    @Autowired
    MyProperties prop;

    @Bean
    CommandLineRunner values() {
        return args -> {
            System.out.println("server ip:" + prop.getServerIp());
            System.out.println("app name:" + prop.getName());
        };
    }

}
