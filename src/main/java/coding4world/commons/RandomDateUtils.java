package coding4world.commons;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateUtils {

    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate getDateUntilNow() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.now();
        return between(start, end);
    }

    public static LocalDate getFutureDateFrom(LocalDate currentDate) {
        return currentDate.plusDays(ThreadLocalRandom.current().nextLong(1,61));
    }
}
