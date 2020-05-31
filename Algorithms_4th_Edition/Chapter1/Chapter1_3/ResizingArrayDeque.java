package Chapter1.Chapter1_3;

import java.util.Arrays;

/**
 * Exercise 1.3.33
 * Implementation of Deque ADT using resizing arrays.
 */
public class ResizingArrayDeque<T> {
    private T[] items;
    private int size;

    public ResizingArrayDeque(int capacity) {
        items = (T[]) new Object[capacity];
    }

    public void pushLeft(T item) {
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = item;
        size++;
        expand();
        System.out.println(Arrays.toString(items));
    }

    public void pushRight(T item) {
        items[size] = item;
        size++;
        expand();
        System.out.println(Arrays.toString(items));
    }

    public T popLeft() {
        if (isEmpty()) throw new IllegalArgumentException("Out of bounds");
        T popValue = items[0];
        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
        size--;
        shrink();
        System.out.println(Arrays.toString(items));
        return popValue;
    }

    public T popRight() {
        if (isEmpty()) throw new IllegalArgumentException("Out of bounds");
        T popValue = items[size - 1];
        items[size - 1] = null;
        size--;
        shrink();
        System.out.println(Arrays.toString(items));
        return popValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void shrink() {
        if (items.length / 4 >= size) {
            int newSize = items.length / 2;
            T[] newArray = (T[]) new Object[newSize];
            for (int i = 0; i < size; i++) {
                newArray[i] = items[i];
            }
            items = newArray;
        }
    }

    private void expand() {
        if (size > items.length * 0.8) {
            int newSize = items.length + (int) Math.max((items.length * 0.5), 1);
            T[] newArray = (T[]) new Object[newSize];
            for (int i = 0; i < size; i++) {
                newArray[i] = items[i];
            }
            items = newArray;
        }
    }

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>(10);

        deque.pushLeft(1);
        deque.pushLeft(2);
        assert deque.popLeft() == 2;
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
