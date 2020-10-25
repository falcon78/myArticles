package Chapter2.Section4;

import java.math.BigInteger;

/**
 * Exercise 2.4.25
 * Calculate (a^3 + b^3) where a and b are integer between 0 and N.
 * The result should be printed in sorted order and you are constrained to use
 * constant space. i.e. (Not O(N^2) but O(N)).
 * Then, if two combinations have same sum, print them. (a^3 + b^3 == c^3 + d^3)
 */
public class CubeSum {
    static class SumNode implements Comparable<SumNode> {
        BigInteger cubeSum;
        BigInteger i;
        BigInteger j;

        SumNode(BigInteger i, BigInteger j) {
            this.i = i;
            this.j = j;
            this.cubeSum = getCubeSum();
        }

        BigInteger getCubeSum() {
            return i.pow(3).add(j.pow(3));
        }

        @Override
        public int compareTo(SumNode sumNode) {
            return this.cubeSum.compareTo(sumNode.cubeSum);
        }
    }


    public void sum() throws Exception {
        int N = 5;
        MinPriorityQueue<SumNode> pq = new MinPriorityQueue<>(N + 1);
        for (int i = 0; i <= N; i++) {
            pq.insert(new SumNode(BigInteger.valueOf(i), BigInteger.valueOf(i)));
        }

        SumNode prev = null;
        while (!pq.isEmpty()) {
            SumNode min = pq.removeMin();
            if (min.j.compareTo(BigInteger.valueOf(N)) < 0) {
                pq.insert(new SumNode(min.i, min.j.add(BigInteger.valueOf(1))));
            }
            if (prev != null) {
                printIfSame(prev, min);
            }
            print(min);
            prev = min;
        }
    }

    public void printIfSame(SumNode prev, SumNode current) {
        if (prev.cubeSum.compareTo(current.cubeSum) == 0) {
            System.out.println("-- same! -- ");
            print(prev);
            print(current);
            System.out.println();
        }
    }

    public void print(SumNode node) {
        System.out.printf("i: %d, j: %d, cubeSum: %d\n", node.i, node.j, node.cubeSum);
    }

    public static void main(String[] args) throws Exception {
        CubeSum cubeSum = new CubeSum();
        cubeSum.sum();
    }
}