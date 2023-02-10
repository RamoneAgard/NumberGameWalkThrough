package agard.spring.console;

import agard.spring.Game;
import agard.spring.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess {

    // Fields
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator msgGen;


    // Events
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start()--> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(msgGen.getMainMessage());
            System.out.println(msgGen.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(msgGen.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainStr = scanner.nextLine().trim();
                if(!playAgainStr.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }
        }
    }
}
