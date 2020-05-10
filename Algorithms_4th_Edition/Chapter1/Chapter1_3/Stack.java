package Chapter1.Chapter1_3;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    public static void main(String[] args) throws IllegalAccessException {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; i++)
            stack.push(i);

        assert stack.size() == 10;
        for (int i = 9; i >= 0; i--)
            assert stack.pop() == i;

        assert stack.isEmpty();

        for (int i = 0; i < 10; i++)
            stack.push(i);

        for (int num : stack)
            assert stack.pop() == num;

    }

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int N = 0;

    public T pop() throws IllegalAccessException {
        if (isEmpty()) throw new IllegalAccessException();

        T item = first.item;
        first = first.next;
        N--;

        return item;
    }

    public void push(T item) {
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
