import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * A class that will generate a chart and save it as image
 */
public class ChartBuilder {

    private static final float LINE_THICKNESS = 4.0f;
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private static final String X_AXIS_LABEL = "Jaar";
    private static final String Y_AXIS_LABEL = "Aantal films";

    private List<Year> years;


    private ChartBuilder() {
    }

    /**
     * Generate the chart
     * @param fileName The output image file name
     * @param years A list of year data to display on the chart
     */
    public static void createChart(String fileName, List<Year> years) {
        /*
        Tutorial used from here:
        https://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm
         */

        DefaultCategoryDataset lineChartDataset = new DefaultCategoryDataset();

        for (Year year : years) {
            if (year.getYear() % 5 != 0) // Only show every 5 years
                continue;

            // Add each year to the chart
            for (Map.Entry<String, Integer> genreAndAmount : year.getGenreAndAmounts().entrySet()) {
                String genre = genreAndAmount.getKey();
                int amount = genreAndAmount.getValue();

                lineChartDataset.addValue(amount, genre, Integer.toString(year.getYear()));
            }
        }


        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Populariteit van genres",
                X_AXIS_LABEL,
                Y_AXIS_LABEL,
                lineChartDataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );


        // This increases line thickness
        lineChartObject.getCategoryPlot().getRenderer().setBaseStroke(new BasicStroke(LINE_THICKNESS));
        ((AbstractRenderer) lineChartObject.getCategoryPlot().getRenderer()).setAutoPopulateSeriesStroke(false);


        // Save to JPEG
        File lineChart = new File(fileName);

        try {
            ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, WIDTH, HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
