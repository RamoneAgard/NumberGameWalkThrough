package agard.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    private final static String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        //Create context (container)
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // Get Number Generator Bean from context/container
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        // Call Method next() to get a random number
        int number = numberGenerator.next();

        //log generated number
        log.info("number = {}", number);

        // Get Game Bean from context/container
        Game game = context.getBean(Game.class);


        //close container
        context.close();

    }
}
