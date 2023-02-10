package agard.spring.config;

import agard.spring.GuessCount;
import agard.spring.MaxNumber;
import agard.spring.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "agard.spring")
public class GameConfig {

    //fields
    @Value("${game.maxNumber:50}")
    private int maxNumber;

    @Value("${game.minNumber:20}")
    private int minNumber;

    @Value("${game.guessCount:5}")
    private int guessCount;



    // bean methods
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

}

