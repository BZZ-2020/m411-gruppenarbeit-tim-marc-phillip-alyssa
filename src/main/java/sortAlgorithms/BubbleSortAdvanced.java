package sortAlgorithms;

import util.Measurement;

/**
 * Bubblesort implementation to sort an array of ints
 * This implementation is done with a while loop and a boolean flag, which should run faster with bigger amounts of datas
 *
 * @author Tim Irmler
 * @version 1.0
 * @since 04-01-2022
 */
public class BubbleSortAdvanced implements Sorter {
    @Override
    public Measurement sort(int[] array) {
        long time = System.currentTimeMillis();
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < array.length - 1; j++) {
                measure.increaseIterations();
                measure.increaseComparisons();
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
            }
        }
        measure.setTime(System.currentTimeMillis() - time);
        return measure;
    }
}
