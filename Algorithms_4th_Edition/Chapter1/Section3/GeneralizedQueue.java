package Chapter1.Section3;


/**
 * Exercise 1.3.38
 */
public class GeneralizedQueue<T> {
    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<>(10);

        for (int i = 0; i < 10; i++) {
            queue.insert(i);
        }

        for (int i = 0; i < 10; i++) {
            int item = queue.delete(0);
            assert item == i;
        }

        for (int i = 0; i < 10; i++) {
            queue.insert(i);
        }

        for (int i = 0; i < 10; i++) {
            int item = queue.delete(0);
            assert item == i;
        }
    }

    int size = 0;
    T[] queue;

    public GeneralizedQueue(int N) {
        queue = (T[]) new Object[N];
    }

    public void insert(T item) {
        if (size >= queue.length) {
            resize((int) Math.max(1, queue.length + Math.ceil(queue.length * 0.8)));
        }
        queue[size++] = item;
    }

    public T delete(int k) throws ArrayIndexOutOfBoundsException {
        T item = queue[k];
        for (int i = k; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }
        queue[Math.max(0, --size)] = null;

        if (size < queue.length * 0.25) {
            resize((int) (queue.length * 0.25));
        }
        return item;
    }

    private void resize(int N) {
        T[] newArray = (T[]) new Object[N];
        System.arraycopy(queue, 0, newArray, 0, size);
        queue = newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
