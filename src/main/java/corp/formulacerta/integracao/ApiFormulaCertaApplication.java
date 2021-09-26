package corp.formulacerta.integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.formulacerta.integracao.properties.TrayProperties;
import feign.Logger;

@EnableConfigurationProperties({TrayProperties.class})
@SpringBootApplication
@EnableScheduling
public class ApiFormulaCertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFormulaCertaApplication.class, args);
	}
	
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    

}
