package statistic;

import data.DiceRoll;
import lombok.Getter;

import java.util.*;

@Getter
public class DiceStatistics {
    private Map<Die, Integer> sides = new HashMap<>();
    private Die max;
    private Die min;

    public DiceStatistics(List<DiceRoll> diceRolls) {
        sides.put(Die.ONE, 0);
        sides.put(Die.TWO, 0);
        sides.put(Die.THREE, 0);
        sides.put(Die.FOUR, 0);
        sides.put(Die.FIVE, 0);
        sides.put(Die.SIX, 0);

        diceRolls.forEach(diceRoll -> {
            diceRoll.getResults().forEach(die -> {
                if (die.equals("1")) increment(sides, Die.ONE);
                if (die.equals("2")) increment(sides, Die.TWO);
                if (die.equals("3")) increment(sides, Die.THREE);
                if (die.equals("4")) increment(sides, Die.FOUR);
                if (die.equals("5")) increment(sides, Die.FIVE);
                if (die.equals("6")) increment(sides, Die.SIX);
            });
        });

        max = Collections.max(sides.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        min = Collections.min(sides.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    /**
     * Calculates deviation between maximum and minimum quantities of appearances.
     *
     * @return Deviation in percentage.
     */
    public double calculateDeviation() {
        Double minValue = Double.valueOf(sides.get(min));
        Double maxValue = Double.valueOf(sides.get(max));
        return 100 - minValue / maxValue * 100;
    }

    private void increment(Map<Die, Integer> map, Die key) {
        map.put(key, map.get(key) + 1);
    }

    private enum Die {ONE, TWO, THREE, FOUR, FIVE, SIX}
}
