package Chapter1.Section3;

public class LinkedList<T> {
    public static void main(String[] args) {
        runTest();
    }

    private class Node {
        T item;
        Node next;
    }

    private int size = 0;
    private Node first;
    private Node last;

    /**
     * Insert a item at the end of a linked list.
     *
     * @param item Item to insert.
     */
    void insert(T item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = first;
            size++;
            return;
        }
        last.next = node;
        last = node;
        size++;
    }

    /**
     * Exercise 1.3.20
     * Deletes element on a linked list at a given index i.
     *
     * @param i Index of element to delete
     * @throws IllegalArgumentException When index is out of bounds.
     */
    void delete(int i) {
        checkAccessBounds(i);
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return;
        }
        if (i == 0) {
            first = first.next;
            size--;
            return;
        }
        Node current = first;
        for (int j = 0; j < i - 1; j++) {
            current = current.next;
        }
        current.next = current.next.next;
        if (current.next == null) {
            last = current;
        }
        size--;
    }

    /**
     * Get item of a linked list at index i.
     *
     * @param i Index of a element.
     * @return Item at a given index of a linked list.
     * @throws IllegalArgumentException When index is out of bounds.
     */
    T get(int i) {
        checkAccessBounds(i);
        if (i > size - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
        Node current = first;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * Exercise 1.3.21
     * Check whether a given item is present in the linked list.
     *
     * @param item Value to check.
     * @return Return true if the given item exists in the list,
     * returns false otherwise.
     */
    boolean find(T item) {
        Node current = first;
        while (current != null) {
            if (item.equals(current.item)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Exercise 1.3.26
     * Remove every element in a linked list whose value is equal to
     * the given item.
     *
     * @param item Item to delete in liked list.
     */
    void removeIfEqual(T item) {
        if (isEmpty() || item == null) return;

        Node current = first;
        while (current.next != null) {
            if (current.next.item.equals(item)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
        last = current;
        if (first.item.equals(item)) {
            first = first.next;
            size--;
        }
    }

    /**
     * Exercise 1.3.24
     * Remove element that is next to the given node.
     *
     * @param node Node of a linked list
     */
    void removeAfter(Node node) {
        if (node == null || node.next == null) return;
        node.next = node.next.next;
        if (node.next == null) last = node;
        size--;
    }

    /**
     * Exercise 1.3.25
     * Insert nodeB next to nodeA.
     *
     * @param nodeA Node before the insert position
     * @param nodeB Node to insert
     */
    void insertAfter(Node nodeA, Node nodeB) {
        if (nodeA.next == null) {
            nodeA.next = nodeB;
            last = nodeB;
            size++;
            return;
        }

        nodeB.next = nodeA.next;
        nodeA.next = nodeB;
        size++;
    }

    /**
     * Exercise 1.3.28
     * Recursive function to find the maximum integer in a linked list.
     *
     * @param node Starting position of a linked list.
     * @param max  Current maximum value for the recursive function.
     * @return maximum value
     */
    int max(Node node, int max) {
        if (!(first.item instanceof Integer)) {
            return -1;
        }

        if (node.next == null) {
            return Math.max((int) node.item, max);
        }

        return max(node.next, Math.max((int) node.item, max));
    }

    /**
     * Exercise 1.3.28
     * recursive function to find the maximum integer in a linked list.
     *
     * @param node starting position of a linked list.
     * @return Maximum value.
     */
    int max(Node node) {
        return max(node, 0);
    }

    /**
     * Exercise 1.3.30
     * Reverse a linked list in place.
     *
     * @return First item of a linked list.
     */
    T reverseInPlace() {
        if (size < 2) return first.item;

        Node previous = null;
        Node current = first;
        last = first;

        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        first = previous;

        assert first != null;
        return first.item;
    }

    /**
     * Returns the string representation of a linked list.
     *
     * @return String representation of a linked list.
     */
    public String toString() {
        if (isEmpty()) return "Empty List";
        StringBuilder str = new StringBuilder("First = " + first() + "\n");
        str.append("Last = ").append(last()).append("\n");
        Node current = first;
        while (current != null) {
            str.append(current.item.toString());
            if (current.next != null) str.append(" --> ");
            current = current.next;
        }
        return str.toString();
    }

    /**
     * Check if the insert position or delete position i is inside the size range of
     * a linked list.
     *
     * @param i Index to perform insertion or deletion.
     * @throws IllegalArgumentException When index is out of bounds.
     */
    void checkAccessBounds(int i) {
        if (isEmpty()) throw new IllegalArgumentException("Empty List");

        if (i > size - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
    }

    /**
     * @return First element of a linked list.
     */
    T first() {
        return first.item;
    }

    /**
     * @return Last element of a linked list.
     */
    T last() {
        return last.item;
    }

    /**
     * Check is a linked list is empty.
     *
     * @return Boolean that indicates whether a linked list is empty or nor.
     */
    boolean isEmpty() {
        return size == 0;
    }

    private static void runTest() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        assert list.get(4) == 4;

        list.delete(4);
        assert list.get(4) == 5;

        list.removeAfter(list.first.next.next.next); // remove 5th element
        assert list.size == 8;
        assert list.get(4) == 6;


        list = new LinkedList<>();
        list.insert(1);
        list.insert(2);
        assert list.get(1) == 2;

        list.delete(1);
        assert list.get(0) == 1;
        assert list.size == 1;
        assert list.first() == 1;
        assert list.last() == 1;

        list.delete(0);
        assert list.size == 0;
        assert list.first == list.last && list.first == null;

        LinkedList<String> stringList = new LinkedList<>();
        stringList.insert("string1");
        stringList.insert("string2");
        assert stringList.find("string2");

        stringList.removeAfter(stringList.first);
        assert stringList.size == 1;
        assert stringList.first == stringList.last;
        assert stringList.first.item.equals("string1");

        LinkedList<String> stringList2 = new LinkedList<>();
        stringList2.insert("string2");
        stringList.insertAfter(stringList.first, stringList2.first);
        assert stringList.get(1).equals("string2");

        stringList2.delete(0);
        assert stringList2.isEmpty();
        assert stringList2.last == null;

        stringList2.insert("string3");
        stringList.insertAfter(stringList.last, stringList2.first);
        assert stringList.size == 3;
        assert stringList.get(2).equals(stringList2.get(0));

        stringList.removeAfter(stringList.first.next);
        assert stringList.last().equals("string2");

        stringList.delete(0);
        assert stringList.get(0).equals("string2");

        stringList.delete(0);
        assert stringList.isEmpty();

        list = new LinkedList<>();
        list.insert(2);
        list.insert(2);
        list.insert(4);
        list.insert(5);
        list.insert(2);
        list.insert(2);
        list.insert(6);
        list.insert(2);
        list.insert(2);
        list.insert(2);
        list.insert(8);
        list.insert(2);
        list.insert(10);
        list.removeIfEqual(2);
        assert list.size == 5;
        assert list.last() == 10;
        assert list.first() == 4;

        list.insert(500);
        assert list.max(list.first) == 500;
        System.out.println(list);

        LinkedList<Integer> reverseTest = new LinkedList<>();
        reverseTest.insert(1);
        assert reverseTest.reverseInPlace() == 1;

        reverseTest.insert(2);
        assert reverseTest.reverseInPlace() == 2;

        reverseTest.delete(0);
        reverseTest.delete(0);

        for (int i = 0; i < 10; i++) {
            reverseTest.insert(i);
        }
        reverseTest.reverseInPlace();
        System.out.println(reverseTest);
        int index = 0;
        for (int i = 9; i >= 0; i--) {
            assert reverseTest.get(index).equals(i);
            index++;
        }
    }
}
