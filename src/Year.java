import java.util.HashMap;
import java.util.Map;

/**
 * Represents a year, containing info of which genres appear in which amounts
 */
public class Year {

    private int year;
    private Map<String, Integer> genreAndAmounts;

    public Year(int year) {
        this.year = year;
        genreAndAmounts = new HashMap<>();
    }

    /**
     * Set the amount of movies with the specified genre released during this year
     * @param genre The genre
     * @param amount The amount of movies
     */
    public void setGenreAmount(String genre, int amount) {
        genreAndAmounts.put(genre, amount);
    }

    /**
     * Get the year of this year as int value
     * @return The year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns a Map with String Genre and Int amount of movies of this genre that appear in this year
     * @return A Map with String Genre and Int amount
     */
    public Map<String, Integer> getGenreAndAmounts() {
        return new HashMap(genreAndAmounts);
    }
}
