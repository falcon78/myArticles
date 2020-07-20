package Chapter1.Section4;

public class FourSum {
    public static void main(String[] args) {
        System.out.println(count(new int[]{
                1, 2, 3, 4, -4, -5, -6, 2, 4, -1
        }));
    }

    /**
     * Return count of how many 4 elements in array sum to 0.
     *
     * @param arr Array of ints.
     */
    public static int count(int[] arr) {
        BinarySearchArray binarySearch = new BinarySearchArray(arr);
        int count = 0;

        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                for (int k = j + 1; k < arr.length; k++)
                    if (binarySearch.rank(-(arr[i] + arr[j] + arr[k])) > k)
                        count++;
        return count;
    }
}
