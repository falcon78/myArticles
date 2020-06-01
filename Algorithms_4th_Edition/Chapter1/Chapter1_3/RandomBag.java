package Chapter1.Chapter1_3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of bag ADT that iterates randomly
 */
public class RandomBag<T> implements Iterable<T> {
    private final ArrayList<T> items;

    public RandomBag() {
        items = new ArrayList<>();
    }

    void add(T item) {
        items.add(item);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {
        private final ArrayList<T> iteratorItems;
        private int currentIndex = 0;

        private BagIterator() {
            iteratorItems = (ArrayList<T>) items.clone();
            for (int i = 0; i < iteratorItems.size(); i++) {
                int randomIndex = i + (int) (Math.random() * (iteratorItems.size() - i));
                T currentItem = iteratorItems.get(i);
                iteratorItems.set(i, iteratorItems.get(randomIndex));
                iteratorItems.set(randomIndex, currentItem);
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < iteratorItems.size();
        }

        @Override
        public T next() {
            T item = iteratorItems.get(currentIndex);
            currentIndex++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        for (int item : bag) {
            System.out.println(item);
        }
    }
}
