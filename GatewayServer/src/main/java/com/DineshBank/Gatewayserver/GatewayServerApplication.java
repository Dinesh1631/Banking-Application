package com.DineshBank.Gatewayserver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
	
	@Bean
	public RouteLocator AccountsDynamicRouting(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route(p -> p.path("/eazybank/accounts/**")
						.filters( f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Responce-Time",LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("accountCircutBreaker")
										.setFallbackUri("forward:/supportInfo"))
								)
						.uri("lb://ACCOUNTS"))
				         .build();
	}
	
	@Bean
	public RouteLocator LonasDynamicRouting(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route(p -> p.path("/eazybank/loans/**")
						.filters(f->f.rewritePath("/eazybank/loans/(?<segment>.*)","/${segment}")
						.addResponseHeader("X-Responce-Time",LocalDateTime.now().toString())
						.retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET)
								.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000),2,true)
								))
						.uri("lb://LOANS")).build();
	}
	

    @Bean
    public RouteLocator cardsDynamicRouting(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/eazybank/cards/**")
                        .filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", System.currentTimeMillis() + "") // Static or simplified dynamic value
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())))
                        .uri("lb://CARDS")) 
                .build(); 
    }
      /*To customize the default timeout of the circut breaker.*/
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()).build());
	}
	
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}

}
