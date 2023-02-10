package agard.spring;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Component
public class SimpleNumberGen implements NumberGenerator {

    // Fields
    private final int maxNumber;
    private final int minNumber;
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    // Constructors
    @Autowired
    public SimpleNumberGen(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // Public methods
    @Override
    public int next() {
        return minNumber + random.nextInt(maxNumber - minNumber);
    }

}
