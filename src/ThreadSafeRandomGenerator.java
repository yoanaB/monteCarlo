/**
 * Created by Yoana on 29.6.2016 г..
 */
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSafeRandomGenerator implements RandomGenerator{

    @Override
    public double generate() {
        return ThreadLocalRandom.current().nextDouble();
    }

}