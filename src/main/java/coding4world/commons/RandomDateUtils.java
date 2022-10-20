package coding4world.commons;

import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateUtils {

    public static void main(String[] args) {
        System.out.println(Instant.ofEpochSecond(date().getEpochSecond()));
    }

    public static Instant date() {
        return Instant.MIN;
    }
}
