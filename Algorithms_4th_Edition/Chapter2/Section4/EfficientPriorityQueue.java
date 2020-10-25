package Chapter2.Section4;

import java.util.Arrays;

public class EfficientPriorityQueue<T extends Comparable<T>> {
	T[] items;
	int N = 1;
	QueueType queueType;

	public enum QueueType {
		MIN, MAX;
	}

	public static void main(String[] args) {
		EfficientPriorityQueue<Integer> epq = new EfficientPriorityQueue<>(10, QueueType.MAX);
		for (int i = 0; i < 20; i++) {
			epq.insert(i);
		}
		for (int i = 0; i < 20; i++) {
		    epq.remove();
		}
	}

	EfficientPriorityQueue(int size, QueueType queueType) {
		items = (T[]) new Comparable[size];
		this.queueType = queueType;
	}

	public void insert(T item) {
		items[N++] = item;
		resizeArray();
		swim(N - 1);
	}

	public void remove() {
		T removeItem = items[1];
		swap(1, --N);
		resizeArray();
		items[N] = null;
	}

	private void swim(int childNode) {
//		while (childNode > 1) {
//
//		}
	}

	private void sink(int parentNode) {

	}

	private void swap(int a, int b) {
		T temp = items[a];
		items[a] = items[b];
		items[b] = temp;
	}

	private void resizeArray() {
		int len = items.length;
		T[] newArray;
		int newArraySize;
		if (len == N) {
			newArraySize = (int) (N * 1.5);
		} else if (((float)len / (float)N) > 2.5) {
			newArraySize = len / 2;
		} else {
			return;
		}

		newArray = (T[]) new Comparable[newArraySize];
		System.arraycopy(items, 0, newArray, 0, N);
		items = newArray;
	}
}