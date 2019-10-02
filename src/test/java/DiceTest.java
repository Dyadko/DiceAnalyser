import data.DiceRoll;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import statistic.DiceStatistics;

import java.util.ArrayList;
import java.util.List;

class DiceTest {
    private static final double MAX_DEVIATION = 5;
    private List<DiceRoll> results;

    @BeforeEach
    void resetResults() {
        results = new ArrayList<>();
    }

    @Test
    void hundredRollsTest() {
        for (int i = 0; i < 100; i++) {
            results.add(new DiceRoll(2));
        }
    }

//    @Test
//    void thousandRollsOneDieTest() {
//        for (int i = 0; i < 1000; i++) {
//            results.add(new DiceRoll(1));
//        }
//    }
//
//    @Test
//    void fiveThousandsRollsTwoDiceTest() {
//        for (int i = 0; i < 5000; i++) {
//            results.add(new DiceRoll(2));
//        }
//    }
//
//    @Test
//    void tenThousandsRollsOneDieTest() {
//        for (int i = 0; i < 10000; i++) {
//            results.add(new DiceRoll(1));
//        }
//    }

    @AfterEach
    void statistics() {
        DiceStatistics diceStatistics = new DiceStatistics(results);
        double deviation = diceStatistics.calculateDeviation();
        Assertions.assertThat(deviation)
                .as("Deviation")
                .isLessThanOrEqualTo(MAX_DEVIATION);
    }
}
