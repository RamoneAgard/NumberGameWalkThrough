package agard.spring;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // Variables
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    private final int guessCount;

    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;
    @Setter
    private int guess;

    // Constructors
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // Init method
    @PostConstruct
    @Override
    public void reset() {
        this.smallest = this.numberGenerator.getMinNumber();
        this.biggest = this.numberGenerator.getMaxNumber();
        this.guess = this.numberGenerator.getMinNumber();
        this.remainingGuesses = guessCount;
        this.number = this.numberGenerator.next();
        log.debug("the number is {}", this.number);
        log.debug("the max is {}", this.biggest);
        log.debug("the min is {}", this.smallest);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game PreDestroy");
    }

    // Public Methods

    @Override
    public void check() {
        checkValidNumberRange();

        if (this.validNumberRange) {
            if (this.guess > this.number) {
                this.biggest = this.guess - 1;
            } else if (this.guess < this.number) {
                this.smallest = this.guess + 1;
            }
        }

        remainingGuesses--;
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


