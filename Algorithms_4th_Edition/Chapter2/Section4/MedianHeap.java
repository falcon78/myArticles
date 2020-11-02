package Chapter2.Section4;

import javax.print.attribute.standard.Media;
import java.util.*;

/**
 * Exercise 2.4.30 Dynamic Median Finding
 * Design a data type that supports finding the median in constant time,
 * deleting the median logarithm time and insert in logarithm time.
 */
public class MedianHeap {
    // stores elements bigger than median.
    MinPriorityQueue<Double> minPQ = new MinPriorityQueue<>();
    // stores elements smaller than median.
    MaxPriorityQueue<Double> maxPQ = new MaxPriorityQueue<>();

    protected Double median = null;

    public void insert(Double item) throws Exception {
        if (median == null) {
            median = item;
            return;
        }


        if (less(item, median)) {
            maxPQ.insert(item);
            return;
        } else {
            minPQ.insert(item);
        }

        int sizeDiff = minPQ.size() - maxPQ.size();
        if (sizeDiff < -1) {
            // remove from maxPQ (too much on maxPQ)
            minPQ.insert(median);
            median = maxPQ.removeMax();

        } else if (sizeDiff > 1) {
            // remove from minPQ (too much on minPQ)
            maxPQ.insert(median);
            median = minPQ.removeMin();
        }
    }

    public double removeMedian() throws Exception {
        if (isEmpty()) {
            throw new Exception("Out of elements");
        }

        double removedMedian = 0;
        if (minPQ.size() > maxPQ.size()) {
            removedMedian = (median + minPQ.removeMin()) / 2;
        } else if (minPQ.size() < maxPQ.size()) {
            removedMedian = (median + maxPQ.removeMax()) / 2;
        } else if (!minPQ.isEmpty()) {
            removedMedian = median;
        }

        if (!maxPQ.isEmpty())
            median = maxPQ.removeMax();
        else if (!minPQ.isEmpty())
            median = minPQ.removeMin();
        else
            median = null;

        return removedMedian;
    }

    public boolean isEmpty() {
        return median == null;
    }

    /**
     * Returns true when a is less than a.
     */
    protected boolean less(Double a, Double b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) throws Exception {
        MedianHeap medianHeap = new MedianHeap();
        for (double i = 0; i < 10; i++) {
            medianHeap.insert(i);
        }
        for (double expected : new double[]{4.5, 4.5, 4.5, 4.5, 4.5}) {
            double actual = medianHeap.removeMedian();
            assert expected == actual;
        }

        for (double v : new double[]{20, 12, 18, 30, 24, 1, 3, 7, 10, 8, 5, 26, 31, 27}) {
            medianHeap.insert(v);
        }
        for (double expected : new double[]{15, 15, 16, 16.5, 16, 16.5, 16}) {
            double actual = medianHeap.removeMedian();
            assert expected == actual;
        }


        for (double v : new double[]{4, 10, 20, 42, 66, 54, 80}) {
            medianHeap.insert(v);
        }
        assert medianHeap.removeMedian() == 42.;
        medianHeap.insert(42.);
        assert medianHeap.removeMedian() == 42.;
        medianHeap.insert(42.);
        medianHeap.insert(20.);
        assert medianHeap.removeMedian() == 31.;

        while (!medianHeap.isEmpty())
            medianHeap.removeMedian();

        for (double v: new double[]{1, 2, 5, 6, 7, 99, 100}) {
            medianHeap.insert(v);
        }
        assert medianHeap.removeMedian() == 6;
        while (!medianHeap.isEmpty())
            medianHeap.removeMedian();
    }
}