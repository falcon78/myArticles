package Chapter2.Section4;

/**
 * Exercise 2.4.24
 * Max priority queue implemented with triply linked list.
 */
public class MaxPQLinkedList<T extends Comparable<T>> {
    private Node first;
    private int size = 0;

    private class Node {
        T value;
        Node parent;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }

        Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    void insert(T value) {
        size++;

        if (first == null) {
            first = new Node(value);
            return;
        }

        int lastSubHeapParentIndex = size / 2;
        int[] pathToSubHeapParent = getNodePath(lastSubHeapParentIndex);
        Node lastSubHeapParent = getNode(pathToSubHeapParent);

        Node newNode = new Node(value, lastSubHeapParent);
        if (lastSubHeapParent.left == null) {
            lastSubHeapParent.left = newNode;
        } else {
            lastSubHeapParent.right = newNode;
        }

        swim(newNode);
    }

    Node getNode(int[] path) {
        Node currentNode = first;

        for (int nextNodeIndex : path) {
            if (nextNodeIndex % 2 == 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return currentNode;
    }

    int[] getNodePath(int nodeIndex) {
        int maxSteps = (int) Math.floor(Math.log(nodeIndex) / Math.log(2));

        int[] pathToNode = new int[maxSteps];

        for (int i = maxSteps - 1; i >= 0; i--) {
            pathToNode[i] = nodeIndex;
            nodeIndex = nodeIndex / 2;
        }

        return pathToNode;
    }


    T removeMax() {
        if (isEmpty()) throw new IndexOutOfBoundsException("Executed removeMax on empty priority queue");

        T maxValue = first.value;

        int[] lastLeafPath = getNodePath(size);
        Node lastLeafNode = getNode(lastLeafPath);
        swap(first, lastLeafNode);

        Node lastLeafParent = lastLeafNode.parent;
        if (lastLeafParent != null) {
            if (size % 2 == 0) {
            	lastLeafParent.left.parent = null;
                lastLeafParent.left = null;
            } else {
            	lastLeafParent.right.parent = null;
                lastLeafParent.right = null;
            }
        } else {
            first = null;
            size--;
            return maxValue;
        }

        size--;
        sink(first);
        return maxValue;
    }

    void swim(Node currentNode) {
        Node parentNode = currentNode.parent;
        while (parentNode != null) {
            if (less(parentNode, currentNode)) {
                swap(parentNode, currentNode);
                currentNode = parentNode;
                parentNode = currentNode.parent;
            } else {
                break;
            }
        }
    }

    void sink(Node currentNode) {
        while (currentNode.left != null) {
            Node biggerNode = currentNode.left;
            if (currentNode.right != null && less(biggerNode, currentNode.right)) {
                biggerNode = currentNode.right;
            }
            if (less(currentNode, biggerNode)) {
                swap(currentNode, biggerNode);
                currentNode = biggerNode;
            } else {
                break;
            }
        }
    }


    void swap(Node a, Node b) {
        T temp = a.value;
        a.value = b.value;
        b.value = temp;
    }

    boolean isEmpty() {
        return size <= 0;
    }

    boolean less(Node a, Node b) {
        return a.value.compareTo(b.value) < 0;
    }

    public static void main(String[] args) throws Exception {
        MaxPQLinkedList<Integer> pq = new MaxPQLinkedList<>();
        for (int i = 0; i < 10; i++) {
            pq.insert((int) (Math.random() * i));
        }
        for (int i = 0; i < 10; i++)
            System.out.println(pq.removeMax());

        for (int i = 0; i < 6000000; i++) {
            pq.insert((int) (Math.random() * i));
        }

        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < 6000000; i++) {
            int newValue = pq.removeMax();
            if (newValue > prev) {
                throw new Exception("Priority Queue not working!");
            }
            prev = newValue;
        }
    }
}
