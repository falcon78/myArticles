package Chapter1.Chapter1_3;

/**
 * Exercise 1.3.31
 * Implementation of Doubly Linked List ADT
 */
public class DoublyLinkedList<T> {
    public static void main(String[] args) {
        runTest();
    }

    public class DoubleNode {
        T item;
        DoubleNode before;
        DoubleNode next;
    }

    private DoubleNode first;
    private DoubleNode last;
    private int size;

    public void insertAtBeginning(T item) {
        DoubleNode oldFirst = first;
        first = new DoubleNode();
        first.item = item;
        first.next = oldFirst;

        if (oldFirst != null)
            oldFirst.before = first;
        else
            last = first;

        size++;
    }

    public void insertAtEnd(T item) {
        if (isEmpty()) {
            insertAtBeginning(item);
            return;
        }

        DoubleNode oldLast = last;
        last = new DoubleNode();
        last.item = item;
        last.before = oldLast;
        oldLast.next = last;

        size++;
    }

    public void removeAtBeginning() {
        if (isEmpty()) throw new IllegalArgumentException("Out of Bounds");
        if (first == last) {
            flush();
        } else {
            first = first.next;
            first.before = null;
        }
        size--;
    }

    public void removeAtEnd() {
        if (isEmpty()) throw new IllegalArgumentException("Out of Bounds");
        if (first == last) {
            flush();
        } else {
            last = last.before;
            last.next = null;
        }
        size--;
    }

    private void insertAtIndex(int i, T item, boolean insertBefore) {
        if ((isEmpty()) || (i == 0 && insertBefore)) {
            insertAtBeginning(item);
            return;
        }
        if (i == size - 1 && !insertBefore) {
            insertAtEnd(item);
        }

        checkBounds(i);

        DoubleNode currentNode = first;
        for (int j = 0; j < i; j++) {
            currentNode = currentNode.next;
        }

        if (insertBefore) {
            insertBeforeNode(currentNode, item);
        } else {
            insertAfterNode(currentNode, item);
        }
    }

    public void insertAfterIndex(int i, T item) {
        insertAtIndex(i, item, false);
    }

    public void insertBeforeIndex(int i, T item) {
        insertAtIndex(i, item, true);
    }

    public void removeAtIndex(int i) {
        if (i == 0) {
            removeAtBeginning();
            return;
        }
        if (i == size - 1) {
            removeAtEnd();
            return;
        }

        DoubleNode deleteNode = getNodeAtIndex(i);
        DoubleNode leftNode = deleteNode.before;
        DoubleNode rightNode = deleteNode.next;
        leftNode.next = rightNode;
        rightNode.before = leftNode;
    }

    public void insertAfterNode(DoubleNode leftNode, T item) {
        DoubleNode rightNode = leftNode.next;
        if (rightNode == null) {
            insertAtEnd(item);
            return;
        }
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        leftNode.next = newNode;
        newNode.before = leftNode;

        rightNode.before = newNode;
        newNode.next = rightNode;
        size++;
    }

    public void insertBeforeNode(DoubleNode rightNode, T item) {
        DoubleNode leftNode = rightNode.before;
        if (leftNode == null) {
            insertAtBeginning(item);
            return;
        }
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        leftNode.next = newNode;
        newNode.before = rightNode;

        rightNode.before = newNode;
        newNode.next = rightNode;
        size++;
    }

    public DoubleNode getNodeAtIndex(int i) {
        if (i == 0) return first;
        if (i == size - 1) return last;

        checkBounds(i);

        DoubleNode currentNode = first;
        for (int j = 0; j < i; j++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public T getItemAtIndex(int i) {
        return getNodeAtIndex(i).item;
    }

    public String toString() {
        if (isEmpty()) return "Empty";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("First: ").append(first.item).append("\n");
        stringBuilder.append("Last: ").append(last.item).append("\n");

        DoubleNode currentNode = first;

        while (currentNode != null) {
            stringBuilder.append("( ");

            if (currentNode.before != null)
                stringBuilder.append(currentNode.before.item);
            else
                stringBuilder.append("null");

            stringBuilder.append(" <- ").append(currentNode.item).append(" -> ");

            if (currentNode.next != null)
                stringBuilder.append(currentNode.next.item);
            else
                stringBuilder.append("null");

            stringBuilder.append(" ) ");
            currentNode = currentNode.next;
        }
        return stringBuilder.toString();
    }

    public int getSize() {
        return size;
    }

    private void checkBounds(int i) {
        if (i > size - 1 || i < 0) {
            throw new IllegalArgumentException("Out Of Bounds");
        }
    }

    public void flush() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static void runTest() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.insertAtBeginning(1);
        assert list.first.item == 1;
        assert list.first.before == null;
        assert list.first.next == null;

        list.insertAtBeginning(2);
        assert list.first.item == 2;
        assert list.first.next.item == 1;
        assert list.first.next.before.item == 2;
        assert list.size == 2;

        list.flush();
        list.insertAtEnd(1);
        assert list.first.item == 1;
        list.insertAtEnd(2);
        assert list.first.next.item == 2;
        assert list.first.next.before.item == 1;
        list.insertAtEnd(3);
        assert list.first.next.next.item == 3;
        assert list.first.next.next.before.item == 2;
        assert list.first.item == 1;
        assert list.last.item == 3;
        assert list.first.next.equals(list.last.before) && list.last.before.item.equals(2);
        assert list.size == 3;

        list.flush();
        list.insertAtEnd(1);
        assert list.first.item == 1;
        assert list.last.item == 1;
        assert list.size == 1;

        list.flush();
        list.insertBeforeIndex(0, 1);
        assert list.first.item == 1;

        list.flush();
        list.insertAfterIndex(0, 1);
        assert list.first.item == 1;

        list.insertAfterIndex(0, 2);
        assert list.first.next.item == 2;
        list.insertAfterIndex(0, 3);
        assert list.first.next.item == 3;

        list.insertBeforeIndex(2, 10);
        assert list.first.next.next.item == 10;
        list.insertBeforeIndex(0, 11);
        assert list.first.item == 11;

        list.flush();
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        assert list.getNodeAtIndex(0).item == 1;
        assert list.getNodeAtIndex(1).item == 2;
        assert list.getNodeAtIndex(2).item == 3;
        list.removeAtIndex(2);
        assert list.size == 2;
        assert list.getNodeAtIndex(1).item == 2;
        assert list.first == list.getNodeAtIndex(0);
        assert list.last == list.getNodeAtIndex(1);
        System.out.println(list);
    }
}

