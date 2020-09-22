package ee.swan.config;

import ee.swan.interceptor.ExecuteTimeInterceptor;
import ee.swan.interceptor.LoginAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ExecuteTimeInterceptor executeTimeInterceptor() {
        return new ExecuteTimeInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(executeTimeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/");

        registry.addInterceptor(new LoginAuthInterceptor());

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/asserts/**")
                .addResourceLocations("classpath:/asserts/", "asserts/");
    }
}
