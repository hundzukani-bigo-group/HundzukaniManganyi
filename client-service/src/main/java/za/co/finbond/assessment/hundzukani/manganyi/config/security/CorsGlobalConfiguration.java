//package za.co.finbond.assessment.client.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebFlux
//public class CorsGlobalConfiguration implements WebFluxConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedMethods("POST", "PUT", "GET", "DELETE", "HEAD")
//                .allowedHeaders("XSRF-TOKEN", "X-XSRF-TOKEN" , "Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
//                .maxAge(3600);
//    }
//
//    @Bean
//   public CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfig.setMaxAge(8000L);
//        corsConfig.addAllowedMethod("POST, PUT, GET, DELETE, HEAD");
//        corsConfig.addAllowedHeader("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Access-Control-Allow-Origin");
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }
//}
