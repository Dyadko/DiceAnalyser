package data;

import io.restassured.response.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.get;

@EqualsAndHashCode
public class DiceRoll {
    private static final Pattern DICE_FIND_PATTERN = Pattern.compile("<img src=\"dice[1-6].png\" alt=\"([1-6])\"\\s?/>");

    @Getter
    private List<String> results;

    public DiceRoll(int numberOfDice) {
        results = new ArrayList<>();
        String path = String.format("https://www.random.org/dice/?num=%d&format=json&rnd=new", numberOfDice);
        Response response = get(path);
        String body = response.getBody().prettyPrint();
        extractDiceValues(body);
    }

    /**
     * Gets dice values from HTML structure.
     *
     * @param body HTML-page.
     */
    private void extractDiceValues(String body) {
        Matcher matcher = DICE_FIND_PATTERN.matcher(body);
        while (matcher.find()) {
            this.results.add(matcher.group(1));
        }
    }
}
