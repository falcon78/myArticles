package Chapter2.Section4;

import Library.StdRandom;

/**
 * Exercise 2.4.25
 * Because swap() is used items are loaded and stored twice than necessary.
 * Give a more efficient solution that doesn't use swap(). (Like insertion sort)
 *
 * @param <T> Type of priority queue elements.
 */
public class EfficientPriorityQueue<T extends Comparable<T>> {
    T[] items;
    int N = 1;
    QueueType queueType;

    public enum QueueType {
        MIN, MAX;
    }

    public static void main(String[] args) {
        int N = 600000;
        EfficientPriorityQueue<Integer> maxPQ = new EfficientPriorityQueue<>(10, QueueType.MAX);
        for (int i = 0; i < N; i++) {
            maxPQ.insert(StdRandom.uniform(Integer.MAX_VALUE));
        }
        assert HeapUtils.verifyMaxHeap(maxPQ.items, maxPQ.N);

        EfficientPriorityQueue<Integer> minPQ = new EfficientPriorityQueue<>(10, QueueType.MIN);
        for (int i = 0; i < N; i++) {
            minPQ.insert(StdRandom.uniform(Integer.MAX_VALUE));
        }
        assert HeapUtils.verifyMinHeap(minPQ.items, minPQ.N);

        int previous = maxPQ.remove();
        for (int i = 0; i < N - 1; i++) {
            int current = maxPQ.remove();
            assert previous >= current;
            previous = current;
        }

        previous = minPQ.remove();
        for (int i = 0; i < N - 1; i++) {
            int current = minPQ.remove();
            assert previous <= current;
            previous = current;
        }
    }

    EfficientPriorityQueue(int size, QueueType queueType) {
        items = (T[]) new Comparable[size];
        this.queueType = queueType;
    }

    public void insert(T item) {
        items[N++] = item;
        resizeArray();
        swim(N - 1);
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }
        T removeItem = items[1];
        swap(1, --N);
        items[N] = null;
        resizeArray();
        sink(1);
        return removeItem;
    }

    private void swim(int currentNodeIndex) {
        T key = items[currentNodeIndex];

        while (currentNodeIndex > 1) {
            int parentNodeIndex = currentNodeIndex / 2;
            T parentNode = items[parentNodeIndex];
            if (queueType == QueueType.MAX && less(parentNode, key)) {
                items[currentNodeIndex] = parentNode;
            } else if (queueType == QueueType.MIN && less(key, parentNode)) {
                items[currentNodeIndex] = parentNode;
            } else {
                break;
            }
            currentNodeIndex = parentNodeIndex;
        }

        items[currentNodeIndex] = key;
    }


    private void sink(int currentNodeIndex) {
        T key = items[currentNodeIndex];

        while (currentNodeIndex * 2 < N) {
            int leftChildIndex = currentNodeIndex * 2;
            int rightChildIndex = currentNodeIndex * 2 + 1;
            if (queueType == QueueType.MAX) {
				int biggerChildIndex = leftChildIndex;
                if (rightChildIndex < N && less(items[leftChildIndex], items[rightChildIndex]))
                    biggerChildIndex = rightChildIndex;
                if (less(key, items[biggerChildIndex])) {
                    items[currentNodeIndex] = items[biggerChildIndex];
                    currentNodeIndex = biggerChildIndex;
                } else {
                    break;
                }
            } else {
                int smallerChildIndex = leftChildIndex;
                if (rightChildIndex < N && less(items[rightChildIndex], items[leftChildIndex]))
                    smallerChildIndex = rightChildIndex;
                if (less(items[smallerChildIndex], key)) {
                    items[currentNodeIndex] = items[smallerChildIndex];
                    currentNodeIndex = smallerChildIndex;
                } else {
                    break;
                }
            }
        }

        items[currentNodeIndex] = key;
    }

    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private void swap(int a, int b) {
        T temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }

    public boolean isEmpty() {
        return N < 2;
    }

    private void resizeArray() {
        int len = items.length;
        T[] newArray;
        int newArraySize;
        if (len == N) {
            newArraySize = (int) (N * 1.5);
        } else if (len / 3 > N) {
            newArraySize = len / 2;
        } else {
            return;
        }

        newArray = (T[]) new Comparable[newArraySize];
        System.arraycopy(items, 0, newArray, 0, N);
        items = newArray;
    }
}