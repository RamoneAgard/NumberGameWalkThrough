package agard.spring.service;

import agard.spring.Game;
import agard.spring.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class SimpleGameService implements GameService{

    // Fields //
    private final Game game;
    private final MessageGenerator msgGen;

    // Constructors //
    @Autowired
    public SimpleGameService(Game game, MessageGenerator msgGen) {
        this.game = game;
        this.msgGen = msgGen;
    }

    // Init Methods
    @PostConstruct
    private void postConstruct(){
        log.debug(getMainMessage());
        log.debug("Web: The number to guess is = {}",game.getNumber());
    }

    @Override
    public boolean isGameOver() {
        return game.isGameWon() || game.isGameLost();
    }

    @Override
    public String getMainMessage() {
        return msgGen.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return msgGen.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
