package agard.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimpleMessageGen implements MessageGenerator {

    // Constants
    private final static Logger log = LoggerFactory.getLogger(SimpleMessageGen.class);

    // Fields
    private final Game game;

    //Constructors
    @Autowired
    public SimpleMessageGen(Game game) {
        this.game = game;
    }

    // Init Methods
    @PostConstruct
    public void postConstruct(){
        log.debug("The number to guess is = {}", game.getNumber());
    }

    //Public Methods
    @Override
    public String getMainMessage() {
        return "The number is between " + game.getSmallest() +
                " and " + game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return "You Got It! The number was " + game.getNumber();
        }
        else if(game.isGameLost()) {
            return "You ran out of guesses. The number was " + game.getNumber();
        }
        else if(!game.isValidNumberRange()){
            return "Invalid number range";
        }
        else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        }
        else{
            String direction = "Lower";
            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            return direction + "! Your have " + game.getRemainingGuesses() +
                    " guesses left";
        }
    }

}

