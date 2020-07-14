package Chapter2.Chapter2_2;

import Chapter2.Chapter2_1.BaseSort;

public class NaturalMergeSort extends BaseSort {
  public static void main(String[] args) {
    sort(new Comparable[]{
      1, 2, 3, 2, 6, 9, 10, 1, 3, 2, 6, 9, 10,
    });
  }

  public static void merge(Comparable[] a, Comparable[]  aux, int low, int mid, int high) {
    for (int i = low; i <= high; i++) {
      aux[i] = a[i];
    }

    int left = low;
    int right = mid + 1;

    for (int i = low; i <= high; i++) {
      if (left > mid){
        a[i] = aux[right++];
      } else if (right > high) {
        a[i] = aux[left++];
      } else if (less(aux[left], aux[right])) {
        a[i] = aux[left++];
      } else {
        a[i] = aux[right++];
      }
    }
  }

  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux);
  }

  public static void sort(Comparable[] a, Comparable [] aux) {
    while(true){
      int low = 0;
      int mid = 0;
      int high = 1;

      boolean firstArray = false;
      boolean secondArray = false;

      for (int j = 1; j < a.length; j++) {
        if (low >= a.length - 1) {
          return;
        }

        // find the first sorted array
        if (!firstArray && (less(a[j-1], a[j]) || equal(a[j-1], a[j]))) {
          mid = j;
          if (j == a.length - 1) {
            firstArray = true;
          }
          continue;
        } else if (!firstArray) {
          mid = j - 1;
          firstArray = true;
          continue;
        }

        // skip if already sorted
        if (low == 0 && mid == a.length - 1) {
          return;
        }

        // find the second sorted array
        if (less(a[j-1], a[j]) || equal(a[j-1], a[j])){
          high = j;
          if (j == a.length - 1) {
            secondArray = true;
          }
        } else {
          high = j - 1;
          secondArray = true;
        }

        // if two sorted arrays are found merge them.
        if (secondArray) {
          merge(a, aux, low, mid, high);
          low = high + 1;
          firstArray = false;
          secondArray = false;
        }
      }
      print(a);
    }
  }
}
