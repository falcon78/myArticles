package Chapter1.Chapter1_1;

public class EuclidsAlgorithm {
    public static void main(String[] args) {
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        System.out.println(greatestCommonDivisor(p, q));
    }

    public static int greatestCommonDivisor(int p, int q) {
        if (q == 0) return p;

        int remainder = p % q;
        return greatestCommonDivisor(q, remainder);
    }
}
