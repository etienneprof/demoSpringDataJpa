package fr.ovalt.demo_spring_data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean // Permet d'indiquer à Spring qu'il doit utiliser cette méthode pour se procurer un SecurityFilterChain
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Cross Site Request Forgery // NE SURTOUT PAS METTRE CA EN PROD
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers("/individus").authenticated()
                                        .requestMatchers("/individus/**").hasRole("ADMIN")
                                        .requestMatchers("/actuator/**").permitAll()
                                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults()) // Permet d'indiquer à Spring qu'on utilise une Basic Auth
                .build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder()
                .username("etienne")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("thomas")
                .password("{noop}password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
