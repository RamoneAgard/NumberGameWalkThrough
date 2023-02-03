package agard.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "agard.spring")
public class AppConfig {
    //  Bean Methods ----
    @Bean
    public  NumberGenerator numberGenerator(){
        return new SimpleNumberGen();
    }

    @Bean
    public Game game(){
        return new GameImpl();
    }

    @Bean
    public MessageGenerator messageGenerator(){
        return new SimpleMessageGen();
    }
}
