package Chapter1.Section3;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        assert queue.isEmpty();
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        assert !queue.isEmpty();

        assert queue.size() == 10;
        for (int i = 0; i < 10; i++)
            assert queue.dequeue() == i;
        assert queue.size() == 0;
        assert queue.isEmpty();

        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        for (int num : queue) {
            assert queue.dequeue() == num;
        }
    }

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;
    private int N;

    public void enqueue(T item) {
        Node newNode = new Node();
        newNode.item = item;

        if (last == null) {
            first = newNode;
            last = first;
        }

        last.next = newNode;
        last = newNode;
        N++;
    }

    public T dequeue() {
        Node oldFirst = first;
        first = first.next;
        N--;

        if (first == null)
            last = null;

        return oldFirst.item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            Node node = currentNode;
            currentNode = currentNode.next;
            return node.item;
        }

        @Override
        public void remove() {
        }
    }
}
