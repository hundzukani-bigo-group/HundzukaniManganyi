package za.co.finbond.assessment.client.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CsrfHeaderFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var xsrfToken = exchange.getRequest().getCookies().getFirst("XSRF-TOKEN").getValue();
        exchange = exchange.mutate().request(it -> {
                it.header("X-XSRF-TOKEN", xsrfToken);
        }).build();
        log.debug(xsrfToken);
        return chain.filter(exchange);
    }
}
