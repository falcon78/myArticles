package Chapter1.Chapter1_1;

class logarithm {
    public static void main(String[] args) {
        int ans = 10;
        System.out.println(lg(ans));
    }

    public static int lg(int n) {
        if (n == 1)
            return 0;

        int count = 0;

        while (n > 0) {
            count++;

            n /= 2;
        }

        return count - 1;
    }
}