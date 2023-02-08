package agard.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


public class SimpleNumberGen implements NumberGenerator {
    // Fields
    private final Random random = new Random();

    @Autowired
    @MaxNumber
    private int maxNumber;

    @Autowired
    @MinNumber
    private int minNumber;

    // Public methods
    @Override
    public int next() {
        return minNumber + random.nextInt(maxNumber - minNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber(){
        return minNumber;
    }
}
