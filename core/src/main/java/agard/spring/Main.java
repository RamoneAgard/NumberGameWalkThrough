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

        NumberGenerator numberGenerator = context.getBean("numberGen", NumberGenerator.class);

        // Call Method next() to get a random number
        int number = numberGenerator.next();

        //log generated number
        log.info("number = {}", number);

        //close container
        context.close();

    }
}
