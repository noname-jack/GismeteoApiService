package ru.tn.nnjack.GismeteoApiService.client;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tn.nnjack.GismeteoApiService.exception.CustomErrorDecoder;

@Configuration
public class FeignConfig {
    public static final String token = "X-Gismeteo-Token";
    @Value("${gismeteo.token}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("accept", "application/json");
            template.header(token, apiKey);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
