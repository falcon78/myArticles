package Chapter1.Section3;

/*
Exercise 1.1.13
 */
public class ResizingArrayQueue<T> {
    public static void main(String[] args) {
        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>(1);
        assert queue.internalArraySize() == 1;
        assert queue.size() == 0;

        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        assert queue.internalArraySize() > 100;
        assert queue.size() == 100;

        for (int i = 0; i < 100; i++)
            assert queue.dequeue() == i;
        assert queue.size() == 0;
    }

    private T[] queue;
    private int N;
    private int head = 0;

    public ResizingArrayQueue(int N) {
        this.N = N;
        queue = (T[]) new Object[N];
    }

    public void enqueue(T item) {
        if (head >= N) expand();

        queue[head++] = item;
    }

    public T dequeue() {
        if (head < 0) throw new IndexOutOfBoundsException();

        T item = queue[0];
        if (head - 1 >= 0) System.arraycopy(queue, 1, queue, 0, head - 1);
        queue[--head] = null;
        if ((double) head / N < 0.25) shrink();
        return item;
    }

    public T getAt(int i) {
        if (i >= head) throw new IllegalArgumentException("Out of bounds");
        return queue[i];
    }

    public int size() {
        return head;
    }

    public int internalArraySize() {
        return N;
    }

    private void shrink() {
        int newSize = N / 2;
        T[] newArray = (T[]) new Object[newSize];

        if (head >= 0) System.arraycopy(queue, 0, newArray, 0, head);
        queue = newArray;
        N = queue.length;
    }

    private void expand() {
        int newSize = (int) (N + Math.ceil(N * 0.5));
        T[] newArray = (T[]) new Object[newSize];

        System.arraycopy(queue, 0, newArray, 0, head);
        queue = newArray;
        N = queue.length;
    }
}
