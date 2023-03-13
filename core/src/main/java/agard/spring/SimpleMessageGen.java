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
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";
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
            return getMessage(WIN, game.getNumber());
        }
        else if(game.isGameLost()) {
            return getMessage(LOSE, game.getNumber());
        }
        else if(!game.isValidNumberRange()){
            return getMessage(INVALID_RANGE);
        }
        else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS);
        }
        else{
            String direction = (game.getGuess() < game.getNumber())?
                    getMessage(HIGHER) : getMessage(LOWER);
            return getMessage(REMAINING, direction, game.getRemainingGuesses());
        }
    }

    // private methods //
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }


}

