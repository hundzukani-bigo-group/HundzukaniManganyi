package za.co.finbond.assessment.hundzukani.manganyi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {


    @Bean
    public SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http) {

        return http
                .cors()
                .configurationSource(request -> corsConfiguration())
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf()
                .disable()
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                .and().build();
    }

    private CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        return corsConfiguration;
    }
}
