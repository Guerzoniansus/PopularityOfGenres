import java.util.*;

public class PopularityProgram {

    private final String INPUT_FILE_NAME = "resources/genre_and_year.csv";
    private final String OUTPUT_FILE_NAME = "output/grafiek.jpeg";

    private DataFile file;

    private HashMap<Integer, Year> years;

    public PopularityProgram() {
        file = new DataFile(INPUT_FILE_NAME);
        years = new HashMap();
    }

    /**
     * Generate the chart that shows the popularity of certain genres each year
     */
    public void generateChart() {
        System.out.println("Processing data...");
        generateYears();
        ChartBuilder.createChart(OUTPUT_FILE_NAME, new ArrayList<Year>(years.values()));
        System.out.println("Done! Saved as " + OUTPUT_FILE_NAME);
    }

    /**
     * Parse the input file and generate each year from it
     */
    private void generateYears() {
        String line;

        // Loop through each line of input
        while ((line = file.readLine()) != null) {

            // Skip first line
            if (line.contains("Genre"))
                continue;

            generateYear(line);

        }
    }

    /**
     * Generate a Year object and store it in the years Map
     * @param input The input to parse and generate a year from
     */
    private void generateYear(String input) {
        final int YEAR_INDEX = 0;
        final int GENRE_INDEX = 1;
        final int AMOUNT_INDEX = 2;

        String[] data = input.split(",");
        int yearNumber  = Integer.parseInt(data[YEAR_INDEX]);

        if (years.containsKey(yearNumber) == false) {
            years.put(yearNumber, new Year(yearNumber));
        }

        String genre = data[GENRE_INDEX].replace("\"", "");
        int amount = Integer.parseInt(data[AMOUNT_INDEX]);

        years.get(yearNumber).setGenreAmount(genre, amount);
    }

}
