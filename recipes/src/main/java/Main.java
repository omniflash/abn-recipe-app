import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot application entry point
 *
 * @author wabel
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages = {"recipes"})
@EntityScan("recipes.entity")
@EnableJpaRepositories("recipes.repository")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}