package za.co.finbond.assessment.client.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {

    @Autowired
    private CsrfHeaderFilter csrfHeaderFilter;

    @Bean
    public SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http) {
        return http
                .addFilterBefore(csrfHeaderFilter, SecurityWebFiltersOrder.CSRF)
                .httpBasic().disable()
                .formLogin().disable()
                .cors()
                .disable()
                .csrf(it -> {
                        it.csrfTokenRepository(new CookieServerCsrfTokenRepository());
                })
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                .and().build();
    }
}
