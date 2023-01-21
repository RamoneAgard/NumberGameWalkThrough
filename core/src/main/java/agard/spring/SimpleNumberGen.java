package agard.spring;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SimpleNumberGen implements NumberGenerator {
    // Fields
    private final Random random = new Random();
    private int maxNumber = 100;

    // Public methods
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
