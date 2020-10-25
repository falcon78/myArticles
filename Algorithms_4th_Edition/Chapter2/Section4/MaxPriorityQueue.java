package Chapter2.Section4;

import Library.StdRandom;

/**
 * Implementation of max-priority queue using binary heap.
 *
 * @param <T> - Type of elements of priority queue.
 */
public class MaxPriorityQueue<T extends Comparable<T>> extends MinPriorityQueue<T> {
    public MaxPriorityQueue(int size) {
        super(size);
    }
    public MaxPriorityQueue() {
        super();
    }

    // tests
    public static void main(String[] args) throws Exception {
        MaxPriorityQueue<Integer> mpq = new MaxPriorityQueue<>();

        for (int i = 0; i < 20; i++) {
            int random = StdRandom.uniform(100);
            mpq.insert(random);
        }
        assert HeapUtils.verifyMaxHeap(mpq.pq, mpq.N);

        for (int i = 0; i < 20; i++) {
            System.out.println(mpq.removeMax());
        }

        for (int i = 0; i < 2000; i++) {
            int random = StdRandom.uniform(100);
            mpq.insert(random);
        }
        assert HeapUtils.verifyMaxHeap(mpq.pq, mpq.N);

        MaxPriorityQueue<String> strpq = new MaxPriorityQueue<>();
        for (int i = 0; i < 20; i++) {
            int random = StdRandom.uniform(65, 91);
            strpq.insert(new String(Character.toChars(random)));
        }
        assert HeapUtils.verifyMaxHeap(mpq.pq, mpq.N);

        for (int i = 0; i < 20; i++) {
            System.out.println(strpq.removeMax());
        }
    }

    @Override
    @Deprecated
    public T removeMin() {
        throw new UnsupportedOperationException("Min not supported on max priority queue");
    }

    public T removeMax() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }

        T removedItem = pq[1];

        pq[1] = pq[--N];
        pq[N] = null;
        sink(1);

        shrink();
        return removedItem;
    }

    @Override
    public void sink(int currentParent) {
        while (2 * currentParent < N) {
            int leftChild = currentParent * 2;
            int rightChild = leftChild + 1;
            int biggerChild = leftChild;

            if (rightChild < N && less(leftChild, rightChild)) {
                biggerChild = rightChild;
            }

            if (less(currentParent, biggerChild)) {
                swap(biggerChild, currentParent);
                currentParent = biggerChild;
                continue;
            }

            break;
        }
    }

    @Override
    public void swim(int currentNode) {
        while (currentNode > 1) {
            int parentNode = currentNode / 2;
            if (less(parentNode, currentNode)) {
                swap(currentNode, parentNode);
                currentNode = parentNode;
            } else {
                break;
            }
        }
    }
}
