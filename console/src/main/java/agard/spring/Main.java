package agard.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {

    // (the beans.xml config file located in the resources dir)
    //private final static String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the Number Game");
        // Spring boot Application start-up //

        SpringApplication.run(Main.class, args);


        // Spring Application context //

        // Create context (container)
        // (when using a beans.xml config file)
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        // (when using a java configuration class)
//        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        // Get Number Generator Bean from context/container
        // (when using component annotations(with no name) on a single implementation)
//        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);
        // (when using listing in the beans config file)
//        NumberGenerator numberGenerator = context.getBean("numberGen", NumberGenerator.class);

        // Get MessageGenerator bean
//        MessageGenerator msgGen = context.getBean(MessageGenerator.class);
//        log.info("main message: {}", msgGen.getMainMessage());
//        log.info("result message: {}", msgGen.getResultMessage());


        //close container
//        context.close();
    }
}
