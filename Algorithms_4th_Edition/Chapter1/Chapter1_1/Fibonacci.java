package Chapter1.Chapter1_1;

/*
Exercise 1.1.19 page 57
 */
public class Fibonacci {
    public static void main(String[] args) {
        for (int N = 0; N < 100; N++) {
            long[] arr = new long[N + 2];
            arr[0] = 0;
            arr[1] = 1;
            System.out.println(N + " " + F(N, arr));
        }
    }

    public static long F(int N, long[] sequences) {
        for (int i = 2; i <= N; i++) {
            sequences[i] = sequences[i - 1] + sequences[i - 2];
        }
        return sequences[N];
    }
}
