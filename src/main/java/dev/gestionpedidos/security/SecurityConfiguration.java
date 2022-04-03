package dev.gestionpedidos.security;

import dev.gestionpedidos.service.UserService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    /*
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT u from User u WHERE u.email = ?")
            .authoritiesByUsernameQuery("SELECT u from User u WHERE u.email = ?");
    }
    */

    // TODO: añadir redireccion a pagina de inicio, pagina de error y logout
    //  .defaultSuccessUrl("/main").permitAll()
    //  .failureUrl("/pruebaError").permitAll()
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                        .antMatchers("/registro",
                                                 "/css/**",
                                                 "/img/**",
                                                 "/js/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/acceso").permitAll()
                            .usernameParameter("email").passwordParameter("password")
                        .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll();
    }

    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

}