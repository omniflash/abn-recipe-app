package recipes.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 * Web config class
 *
 * @author wabel
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    // Set proper timezone when mapping date
    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

}
