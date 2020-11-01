package Chapter2.Section4;

import Library.StdRandom;

/**
 * Double ended priority queue implementation using min-max heap.
 * Supports removeMin() and removeMax() in O(logN) time.
 */
class PQNode implements Comparable<PQNode> {
    Comparable value;
    int indexInMinHeap;
    int indexInMaxHeap;

    PQNode(Comparable value, int indexInMinHeap, int indexInMaxHeap) {
        this.value = value;
        this.indexInMinHeap = indexInMinHeap;
        this.indexInMaxHeap = indexInMaxHeap;
    }

    @Override
    public int compareTo(PQNode other) {
        return this.value.compareTo(other.value);
    }
}

public class MinMaxPriorityQueue<T extends Comparable<T>> {
    private PQNode[] minHeap;
    private PQNode[] maxHeap;
    private int N = 1;


    public MinMaxPriorityQueue(int size) {
        this.minHeap = new PQNode[size];
        this.maxHeap = new PQNode[size];
    }

    public void insert(T item) {
        int insertIndex = N++;

        PQNode node = new PQNode(item, insertIndex, insertIndex);
        minHeap[insertIndex] = node;
        maxHeap[insertIndex] = node;

        swimMaxHeap(node);
        swimMinHeap(node);
    }

    public Comparable removeMin() {
        PQNode nodeToRemoveInMinHeap = minHeap[1];

        swapMinHeapElements(nodeToRemoveInMinHeap, minHeap[N - 1]);
        minHeap[N - 1] = null;

        int maxHeapRemovePosition = nodeToRemoveInMinHeap.indexInMaxHeap;
        swapMaxHeapElements(maxHeap[maxHeapRemovePosition], maxHeap[N - 1]);
        maxHeap[N - 1] = null;

        N--;
        if (!isEmpty()) {
            sinkMinHeap(minHeap[1]);
        }
        if (!isEmpty() && maxHeapRemovePosition < N) {
            sinkMaxHeap(maxHeap[maxHeapRemovePosition]);
            swimMaxHeap(maxHeap[maxHeapRemovePosition]);
        }

        return nodeToRemoveInMinHeap.value;
    }


    public Comparable removeMax() {
        PQNode nodeToRemoveInMaxHeap = maxHeap[1];

        swapMaxHeapElements(nodeToRemoveInMaxHeap, maxHeap[N - 1]);
        maxHeap[N - 1] = null;

        int minHeapRemovePosition = nodeToRemoveInMaxHeap.indexInMinHeap;
        swapMinHeapElements(minHeap[N - 1], minHeap[minHeapRemovePosition]);
        minHeap[N - 1] = null;

        N--;
        if (!isEmpty()) {
            sinkMaxHeap(maxHeap[1]);
        }
        if (!isEmpty() && minHeapRemovePosition < N) {
            sinkMinHeap(minHeap[minHeapRemovePosition]);
            swimMinHeap(minHeap[minHeapRemovePosition]);
        }

        return nodeToRemoveInMaxHeap.value;
    }

    public void swimMinHeap(PQNode currentNode) {
        while (currentNode.indexInMinHeap > 1) {
            PQNode parentNode = minHeap[currentNode.indexInMinHeap / 2];
            if (less(currentNode.value, parentNode.value)) {
                swapMinHeapElements(currentNode, parentNode);
            } else {
                break;
            }
        }
    }

    public void swimMaxHeap(PQNode currentNode) {
        while (currentNode.indexInMaxHeap > 1) {
            PQNode parentNode = maxHeap[currentNode.indexInMaxHeap / 2];
            if (less(parentNode.value, currentNode.value)) {
                swapMaxHeapElements(parentNode, currentNode);
            } else {
                break;
            }
        }
    }

    public void sinkMinHeap(PQNode currentNode) {
        while (currentNode.indexInMinHeap * 2 < N) {
            PQNode smallerChild = minHeap[currentNode.indexInMinHeap * 2];
            int rightChildIndex = smallerChild.indexInMinHeap + 1;
            if (rightChildIndex < N && less(minHeap[rightChildIndex], smallerChild)) {
                smallerChild = minHeap[rightChildIndex];
            }
            if (less(smallerChild, currentNode)) {
                swapMinHeapElements(smallerChild, currentNode);
            } else {
                break;
            }
        }
    }

    public void sinkMaxHeap(PQNode currentNode) {
        while (currentNode.indexInMaxHeap * 2 < N) {
            PQNode biggerChild = maxHeap[currentNode.indexInMaxHeap * 2];
            int rightChildIndex = biggerChild.indexInMaxHeap + 1;
            if (rightChildIndex < N && less(biggerChild, maxHeap[rightChildIndex])) {
                biggerChild = maxHeap[rightChildIndex];
            }
            if (less(currentNode, biggerChild)) {
                swapMaxHeapElements(currentNode, biggerChild);
            } else {
                break;
            }
        }
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public boolean isEmpty() {
        return N <= 1;
    }

    public void swapMinHeapElements(PQNode nodeA, PQNode nodeB) {
        int newIndexOfNodeA = nodeB.indexInMinHeap;
        int newIndexOfNodeB = nodeA.indexInMinHeap;
        minHeap[newIndexOfNodeA] = nodeA;
        minHeap[newIndexOfNodeB] = nodeB;
        nodeA.indexInMinHeap = newIndexOfNodeA;
        nodeB.indexInMinHeap = newIndexOfNodeB;
    }

    public void swapMaxHeapElements(PQNode nodeA, PQNode nodeB) {
        int newIndexOfNodeA = nodeB.indexInMaxHeap;
        int newIndexOfNodeB = nodeA.indexInMaxHeap;
        maxHeap[newIndexOfNodeA] = nodeA;
        maxHeap[newIndexOfNodeB] = nodeB;
        nodeA.indexInMaxHeap = newIndexOfNodeA;
        nodeB.indexInMaxHeap = newIndexOfNodeB;
    }

    public static void main(String[] args) {
        int N = 10000;
        MinMaxPriorityQueue<Integer> pq = new MinMaxPriorityQueue<>(N + 1);
        for (int i = 0; i < N; i++) {
            pq.insert(StdRandom.uniform(-Integer.MAX_VALUE / 3, Integer.MAX_VALUE / 3));
            assert HeapUtils.verifyMinHeap(pq.minHeap, pq.N);
            assert HeapUtils.verifyMaxHeap(pq.maxHeap, pq.N);
        }
        for (int i = 0; i < N; i++) {
            pq.removeMax();
            assert HeapUtils.verifyMinHeap(pq.minHeap, pq.N);
            assert HeapUtils.verifyMaxHeap(pq.maxHeap, pq.N);
        }

        for (int i = 0; i < N; i++) {
            pq.insert(StdRandom.uniform(-Integer.MAX_VALUE / 3, Integer.MAX_VALUE / 3));
        }
        for (int i = 0; i < N; i++) {
            pq.removeMin();
            assert HeapUtils.verifyMinHeap(pq.minHeap, pq.N);
            assert HeapUtils.verifyMaxHeap(pq.maxHeap, pq.N);
        }

        assert HeapUtils.verifyMinHeap(pq.minHeap, pq.N);
        assert HeapUtils.verifyMaxHeap(pq.maxHeap, pq.N);
    }
}
