import sortAlgorithms.*;
import util.Measurement;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] array = {4, 1, 2, 8, 6, 5, 7, 12};
        Quicksort quicksort = new Quicksort();
        Measurement measure = quicksort.sort(array);
        System.out.println("measure quicksort = " + measure);

        array = new int[]{4, 1, 2, 8, 6, 5, 7, 12};
        BubbleSort bubbleSort = new BubbleSort();
        measure = bubbleSort.sort(array);
        System.out.println("measure bubblesort = " + measure);

        array = new int[]{4, 1, 2, 8, 6, 5, 7, 12};
        BubbleSortAdvanced bubbleSortAdvanced = new BubbleSortAdvanced();
        measure = bubbleSortAdvanced.sort(array);
        System.out.println("measure bubblesort advanced = " + measure);

        array = new int[]{4, 1, 2, 8, 6, 5, 7, 12};
        TreeSort treeSort = new TreeSort();
        measure = treeSort.sort(array);
        System.out.println("measure treesort = " + measure);
    }

    private void measureSorts() {
        var sorters = new ArrayList<Sorter>();

        sorters.add(new BubbleSort());
        sorters.add(new BubbleSortAdvanced());
        sorters.add(new Quicksort());
        sorters.add(new Quicksort());
    }
}
