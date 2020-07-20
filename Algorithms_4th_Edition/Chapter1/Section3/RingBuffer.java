package Chapter1.Section3;

import java.util.Arrays;

/**
 * Exercise 1.3.39
 */
public class RingBuffer<T> {
    public static void main(String[] args) {
        RingBuffer<Integer> buffer = new RingBuffer<>(10);
        for (int i = 0; i < 50; i++) {
            buffer.insert(i);
            assert buffer.read() == i;
        }
    }

    int writer;
    int reader;
    int readSemaphore;
    T[] queue;

    public RingBuffer(int N) {
        queue = (T[]) new Object[N];
    }

    public void insert(T item) {
        if (!canWrite()) throw new IllegalArgumentException("must wait");
        int insertIndex = writer % queue.length;
        queue[insertIndex] = item;
        readSemaphore++;
        writer++;
    }

    public boolean canWrite() {
        return readSemaphore < queue.length;
    }

    public T read() {
        if (!canRead()) throw new IllegalArgumentException("must wait");
        int readIndex = reader % queue.length;
        T readItem = queue[readIndex];
        queue[readIndex] = null;
        reader++;
        readSemaphore--;
        return readItem;
    }

    public boolean canRead() {
        return readSemaphore > 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Arrays.toString(queue));
        builder.append("\n");
        builder.append("Semaphore : ");
        builder.append(readSemaphore);
        return builder.toString();
    }
}
