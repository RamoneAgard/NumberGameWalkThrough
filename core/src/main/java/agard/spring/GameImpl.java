package agard.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class GameImpl implements Game {

    // Constants
    private final static Logger log = LoggerFactory.getLogger(GameImpl.class);

    // Variables
    @Autowired
    private NumberGenerator numberGenerator;
    @Autowired
    @GuessCount
    private int guessCount;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // Init method
    @PostConstruct
    @Override
    public void reset() {
        this.smallest = this.numberGenerator.getMinNumber();
        this.guess = this.numberGenerator.getMinNumber();
        this.remainingGuesses = guessCount;
        this.biggest = this.numberGenerator.getMaxNumber();
        this.number = this.numberGenerator.next();
        log.debug("the number is {}",this.number);
        log.debug("the max is {}",this.biggest);
        log.debug("the min is {}",this.smallest);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game PreDestroy");
    }

    // Public Method
    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getGuess() {
        return this.guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return this.smallest;
    }

    @Override
    public int getBiggest() {
        return this.biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return this.remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }
    @Override
    public void check() {
        checkValidNumberRange();

        if(this.validNumberRange){
            if(this.guess > this.number){
                this.biggest = this.guess - 1;
            }
            else if(this.guess < this.number){
                this.smallest = this.guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return this.validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return this.guess == this.number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && this.remainingGuesses <= 0;
    }

    //Private Methods
    private void checkValidNumberRange() {
        this.validNumberRange = (this.guess >= this.smallest) && (this.guess <= biggest);
    }


}


