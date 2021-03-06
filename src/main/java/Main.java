import sortAlgorithms.*;
import util.Export;
import util.InputReader;
import util.Measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class to run the program
 * <li>Reads files
 * <li>Runs tests
 * <li>Calls export
 *
 * @author Marc Andri Fuchs
 * @since 04-01-2022
 */
public class Main {
    private final static int SORTER_ITERATIONS = 10;

    /**
     * Main function to run read all test files
     *
     * @param args the provided arguments
     */
    public static void main(String[] args) {
        System.out.print("Please ensure that all input samples are in the \"resources/inputfiles\" folder (enter 'y' to continue) > ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (!input.equalsIgnoreCase("y")) {
            System.out.println("Programm will stop...");
            return;
        }
        sc.close();

        var main = new Main();

        var inputsamples = InputReader.getFileContent();
        var measurements = main.measureSorts(inputsamples);

        var export = new Export();

        try {
            export.export(measurements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Measures all sorting algorithms
     *
     * @param inputsamples the arrays to sort
     * @return a list of Measurements
     */
    private ArrayList<Measurement> measureSorts(int[][] inputsamples) {
        var measurements = new ArrayList<Measurement>();

        var sorters = createSorters();

        int iter = 0;
        int totalIters = inputsamples.length * sorters.size() * SORTER_ITERATIONS;

        for (int[] is : inputsamples) {
            for (var sorter : sorters) {
                var sorterMeasurements = new ArrayList<Measurement>();
                for (int i = 0; i < SORTER_ITERATIONS; i++) {
                    iter++;
                    printProgress(iter, totalIters);

                    sorter.reset();

                    var isCopy = Arrays.copyOf(is, is.length);

                    var m = sorter.sort(isCopy);

                    sorterMeasurements.add(m);
                }

                var avgMeasurements = new Measurement();

                avgMeasurements.setSorterName(sorter.getClass().getSimpleName());
                avgMeasurements.setSampleSize(is.length);

                avgMeasurements.setComparisons(
                        (long) sorterMeasurements.stream().mapToLong(Measurement::getComparisons).average().orElse(0)
                );

                avgMeasurements.setIterations(
                        (long) sorterMeasurements.stream().mapToLong(Measurement::getIterations).average().orElse(0)
                );

                avgMeasurements.setTimeInNs(
                        (long) sorterMeasurements.stream().mapToLong(Measurement::getTimeInNs).average().orElse(0)
                );

                avgMeasurements.setMemory(
                        (long) sorterMeasurements.stream().mapToLong(Measurement::getMemory).average().orElse(0)
                );

                measurements.add(avgMeasurements);
            }
        }

        return measurements;
    }

    /**
     * Prints the amount progress of running all the tests
     *
     * @param iter  the amount of ran tests
     * @param total the total amount of test
     */
    private void printProgress(int iter, int total) {
        int progressLength = Math.min(total * 4, 120);

        double progress = (double) progressLength / (double) total * iter;
        double progressInCent = 100f / progressLength * progress;
        System.out.printf("\rTesting Sorters: [%s%s] %.0f%%",
                "=".repeat((int) progress),
                " ".repeat((int) (progressLength - progress)),
                progressInCent
        );

        if (iter == total)
            System.out.println();
    }

    /**
     * Creates an ArrayList with all sorters which will be tested
     *
     * @return the List
     */
    private ArrayList<Sorter> createSorters() {
        var sorters = new ArrayList<Sorter>();

        sorters.add(new BubbleSort());
        sorters.add(new BubbleSortAdvanced());
        sorters.add(new Quicksort());
        sorters.add(new TreeSort());

        return sorters;
    }
}
