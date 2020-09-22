package Chapter2.Section4;

import Library.StdRandom;

public class MinPriorityQueue<T extends Comparable<T>> {
    private T[] pq;
    private int N = 1;

    public static void main(String[] args) {
        MinPriorityQueue<Integer> pq = new MinPriorityQueue<>(0);
        for (int i = 0; i < 20; i++) {
            int random = StdRandom.uniform(100);
            pq.insert(random);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(pq.removeMin());
        }
    }

    public MinPriorityQueue(int size) {
        pq = (T[]) new Comparable[size + 1];
    }

    public void insert(T item) {
        expand();
        pq[N++] = item;
        swim(N - 1);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        T removedItem = pq[1];
        pq[1] = pq[--N];
        pq[N] = null;
        sink(1);

        shrink();
        return removedItem;
    }

    public void sink(int currentParent) {
        if (currentParent < 1) {
            return;
        }

        while (2 * currentParent < N) {
            int leftChild = 2 * currentParent;
            int rightChild = 2 * currentParent + 1;

            int smallerChild = leftChild;
            if (rightChild < N && less(rightChild, leftChild)) {
                smallerChild = rightChild;
            }

            if (less(smallerChild, currentParent)) {
                swap(smallerChild, currentParent);
                currentParent = smallerChild;
            } else {
                break;
            }
        }
    }

    public void swim(int currentNode) {
        if (currentNode < 1) {
            return;
        }

        while (currentNode > 1) {
            int parentNode = currentNode / 2;
            if (less(currentNode, parentNode)) {
                swap(currentNode, parentNode);
                currentNode = parentNode;
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return N == 1;
    }


    private void shrink() {
        if ((pq.length / 2) < N) {
            return;
        }

        int newLength = pq.length / 2;
        T[] newArray = (T[]) new Comparable[newLength];
        if (N - 1 >= 0) System.arraycopy(pq, 1, newArray, 1, N - 1);
        pq = newArray;
    }

    private void expand() {
        if (pq.length > N + 2) {
            return;
        }

        int newLength = (int) (pq.length * 1.5) + 2;
        T[] newArray = (T[]) new Comparable[newLength];
        if (N - 1 >= 0) System.arraycopy(pq, 1, newArray, 1, N - 1);
        pq = newArray;
    }

    private void swap(int a, int b) {
        T temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    /**
     * returns true if a is smaller than b
     *
     * @param a - index of item to compare
     * @param b - index of item to compare
     * @return true if a is smaller than b, otherwise return false
     */
    private boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }
}
