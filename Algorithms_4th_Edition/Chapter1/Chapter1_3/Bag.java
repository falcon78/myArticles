package Chapter1.Chapter1_3;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();

        assert bag.isEmpty();
        assert bag.size() == 0;

        for (int i = 0; i < 10; i++)
            bag.add(i);

        assert bag.size() == 10;
        assert !bag.isEmpty();

        int n = 9;
        for (int num : bag) {
            assert num == n;
            n--;
        }

        assert bag.size() == 10;
    }

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int N = 0;

    public void add(T item) {
        Node newNode = new Node();
        newNode.next = first;
        newNode.item = item;
        first = newNode;

        N++;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private Node currentNode = first;

        public boolean hasNext() {
            return currentNode != null;
        }

        public void remove() {
        }

        public T next() {
            Node current = currentNode;
            currentNode = current.next;
            return current.item;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
