package Chapter2.Section4;

/**
 * Exercise 2.4.30 Dynamic Median Finding
 * Design a data type that supports finding the median in constant time,
 * deleting the median logarithm time and insert in logarithm time.
 */
public class MedianHeap<T extends Comparable<T>> {
    enum Orientation {
        MIN, MAX
    };

    public static void main(String[] args) {

    }

    protected T median = null;
    protected T[] minHeap;
    protected int minHeapCurrentIndex = 1;
    protected T[] maxHeap;
    protected int maxHeapCurrentIndex = 1;

    public void insert(T item) {
        if (median == null) {
            median = item; 
            return;
        }

        if (less(item, median)) {
            minHeap[minHeapCurrentIndex] = item;
            swim(minHeap, minHeapCurrentIndex, Orientation.MIN);
            minHeapCurrentIndex++;
            return;
        }
        
        maxHeap[maxHeapCurrentIndex] = item;
        swim(maxHeap, maxHeapCurrentIndex, Orientation.MAX);
        maxHeapCurrentIndex++;
    }

    public void removeMedian() {

    }
    
    protected T removeMin() {
        
    }

    protected T removeMax() {
        
    }
    
    /**
     * Returns true when a is less than a.
     */
    protected boolean less(T a, T b) {
        return a.compreTo(b) < 0;
    }
    
    protected boolean isEmpty(Orientation orientation) {
        if (orientation == Orientation.MAX)
            return maxHeapCurrentIndex <= 1;
        
        return minHeapCurrentIndex <= 1;
    }

    public void swim(T[] arr, int index, Orientation orientation) {
        
    }
    
    public void sink(T[] arr, int index, Orientation orientation) {
        
    }
}