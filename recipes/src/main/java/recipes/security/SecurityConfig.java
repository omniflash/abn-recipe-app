package recipes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration
 *
 * @author wabel
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
        .csrf().disable()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/dev/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/external/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/external/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/external/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/external/**").hasRole("ADMIN")
        .anyRequest().hasRole("USER")
        .and()
        .formLogin();

    }

}