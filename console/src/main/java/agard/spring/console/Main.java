package agard.spring.console;

import agard.spring.config.AppConfig;
import agard.spring.MessageGenerator;
import agard.spring.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    // (the beans.xml config file located in the resources dir)
    //private final static String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        // Create context (container)
        // (when using a beans.xml config file)
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        // (when using a java configuration class)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Number Generator Bean from context/container
        // (when using component annotations(with no name) on a single implementation)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);
        // (when using listing in the beans config file)
        //NumberGenerator numberGenerator = context.getBean("numberGen", NumberGenerator.class);

        // Call Method next() to get a random number
        int number = numberGenerator.next();

        //log generated number
        log.info("number = {}", number);

        // Get MessageGenerator bean
        MessageGenerator msgGen = context.getBean(MessageGenerator.class);
        log.info("main message: {}", msgGen.getMainMessage());
        log.info("result message: {}", msgGen.getResultMessage());


        //close container
        context.close();

    }
}
