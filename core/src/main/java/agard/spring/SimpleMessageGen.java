package agard.spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SimpleMessageGen implements MessageGenerator {

    // Constants //
    private static final String MAIN_MESSAGE = "game.main.message";
    // Fields
    private final Game game;
    private final MessageSource messageSource;

    //Constructors
    @Autowired
    public SimpleMessageGen(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // Init Methods
    @PostConstruct
    public void postConstruct(){
        log.debug("The number to guess is = {}", game.getNumber());
    }

    //Public Methods
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
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
            return direction + "! You have " + game.getRemainingGuesses() +
                    " guesses left";
        }
    }

    // privage methods //
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }


}

