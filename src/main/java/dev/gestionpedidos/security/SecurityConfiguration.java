package dev.gestionpedidos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class collects the security configuration of the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * A bean of passsword encoder.
     * @return An instance of password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the manager authenticator to control the users authentications.
     * Uses a User Details Service to manage the authentication.
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(this.passwordEncoder());
    }

    /**
     * Configures the access of HTTP requests to application resources.
     * @param httpSecurity the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                        .antMatchers("/acceso",
                                                 "/registro",
                                                 "/css/**",
                                                 "/img/**",
                                                 "/js/**").permitAll()
                        .antMatchers("/admin", "/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                        .loginPage("/acceso")
                        .loginProcessingUrl("/acceso")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/acceso?error=true")
                    .and()
                        .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll();
    }
}