package Chapter1.Chapter1_3;

/***
 * Exercise 1.3.29
 * Implementation of Queue ADT using Circular Linked list.
 * @param <T>
 */
public class CircularQueue<T> {
    public static void main(String[] args) {
        runTest();
    }

    private class Node {
        T item;
        Node next;
    }

    private Node last;
    private int size;

    public void enqueue(T item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = last;
            size++;
            return;
        }

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = last.next;
        last.next = newNode;
        last = newNode;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Queue empty");

        if (last.next == last) {
            Node returnNode = last;
            last = null;
            size--;
            return returnNode.item;
        }
        Node returnNode = last.next;
        last.next = last.next.next;
        size--;
        return returnNode.item;
    }

    public String toString() {
        if (isEmpty()) return "Empty";

        Node firstNode = last.next;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstNode.item.toString());
        stringBuilder.append(" -> ");
        Node currentNode = firstNode.next;
        while (currentNode != firstNode) {
            stringBuilder.append(currentNode.item.toString());
            stringBuilder.append(" -> ");
            currentNode = currentNode.next;
        }
        return stringBuilder.toString();
    }

    boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    static void runTest() {
        CircularQueue<Integer> circularQueue = new CircularQueue<>();
        assert circularQueue.isEmpty();

        circularQueue.enqueue(1);
        assert circularQueue.last.item.equals(circularQueue.last.next.item);

        circularQueue.enqueue(2);
        assert circularQueue.last.item.equals(2);
        assert circularQueue.last.next.item.equals(1);
        assert circularQueue.size == 2;

        circularQueue.enqueue(3);
        circularQueue.enqueue(4);
        assert circularQueue.last.item.equals(4);
        assert circularQueue.last.next.item.equals(1);

        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.dequeue();
        assert circularQueue.toString().equals("Empty");
        assert circularQueue.isEmpty();
        assert circularQueue.size == 0;
    }
}
