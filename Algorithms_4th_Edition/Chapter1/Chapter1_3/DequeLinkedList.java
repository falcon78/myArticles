package Chapter1.Chapter1_3;

/**
 * Exercise 1.3.33
 * Implementation of Deque ADT using doubly-linked list
 */
public class DequeLinkedList<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        T item;
        Node next;
        Node prev;
    }

    public void pushLeft(T item) {
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public void pushRight(T item) {
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        last.next = newNode;
        newNode.prev = last;
        last = newNode;
        size++;
    }

    public T popLeft() {
        if (isEmpty()) throw new IllegalArgumentException("Out of bounds");
        Node popNode = first;
        first = first.next;
        if (first == null)
            last = null;
        size--;
        return popNode.item;
    }

    public T popRight() {
        if (isEmpty()) throw new IllegalArgumentException("Out of bounds");
        Node popNode = last;
        last = last.prev;
        if (last.prev == null)
            first = null;
        size--;
        return popNode.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DequeLinkedList<Integer> deque = new DequeLinkedList<>();

        deque.pushLeft(1);
        assert deque.popLeft() == 1;
        assert deque.size == 0;

        deque.pushRight(1);
        assert deque.size == 1;
        assert deque.popRight() == 1;

        deque.pushRight(1);
        deque.pushRight(2);
        assert deque.popRight() == 2;
        assert deque.popRight() == 1;

        deque.pushLeft(2);
        deque.pushLeft(1);
        assert deque.popLeft() == 1;
        assert deque.popLeft() == 2;

        for (int i = 0; i < 10; i++) {
            deque.pushLeft(i);
        }
        for (int i = 9; i >= 0; i--) {
            assert deque.popLeft() == i;
        }

        for (int i = 0; i < 10; i++) {
            deque.pushRight(i);
        }
        for (int i = 9; i >= 0; i--) {
            assert deque.popRight() == i;
        }
    }
}
