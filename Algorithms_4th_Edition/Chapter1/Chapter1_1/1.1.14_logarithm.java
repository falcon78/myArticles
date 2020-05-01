package Chapter1.Chapter1_1;

class logarithm {
    public static void main(String[] args) {
        int ans = lg(Integer.parseInt(args[0]));
        System.out.println(ans);
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