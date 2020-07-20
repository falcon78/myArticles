package Chapter2.Section1;

public class ShellSort extends BaseSort{
    public static void main(String[] args) {
        sort(unsortedArray(1000));
    }

    public static Comparable[] sort(Comparable[] arr) {
        int h = 1;
        while (h < arr.length / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                int j = i;
                Comparable key = arr[i];
                while (j >= h && less(key, arr[j - h])) {
                    arr[j] = arr[j - h];
                    j = j - h;
                }
                arr[j] = key;
            }
            h = h / 3;
        }
        
        assert ShellSort.isSorted(arr);

        return arr;
    }
}