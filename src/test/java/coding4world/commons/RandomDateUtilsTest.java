package coding4world.commons;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomDateUtilsTest {

   @Test
    public void getDateUntilNow(){
        assertThat(RandomDateUtils.getDateUntilNow()).isBetween(LocalDate.MIN, LocalDate.now());
    }
}
