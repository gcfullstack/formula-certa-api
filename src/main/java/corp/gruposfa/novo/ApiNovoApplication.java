package corp.gruposfa.novo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.gruposfa.novo.consinco.properties.QueroDeliveryProperties;
import feign.Logger;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({QueroDeliveryProperties.class})
public class ApiNovoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNovoApplication.class, args);
	}
	
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
    

}
