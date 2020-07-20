package Chapter1.Section4;

import Library.StdDraw;
import Library.StdRandom;

import java.awt.*;
import java.util.ArrayList;

public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;
    private ArrayList<Integer> results = new ArrayList<>();

    private DoublingTest() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.0010);
        results.add(1);
        results.add(1);
    }

    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public void draw(int scaleX, int scaleY) {
        StdDraw.setXscale(0, Math.log(scaleX));
        StdDraw.setYscale(0, Math.log(scaleY));
        StdDraw.clear();
        for (int i = 0; i < results.size() - 3; i += 2) {
            StdDraw.line(
                    Math.log(results.get(i)),
                    Math.log(results.get(i + 1)),
                    Math.log(results.get(i + 2)),
                    Math.log(results.get(i + 3))
            );
        }
    }

    public static void main(String[] args) {
        DoublingTest doublingTest = new DoublingTest();
        for (int n = 10; true; n += 10) {
            double time = timeTrial(n);
            doublingTest.results.add(n);
            doublingTest.results.add((int) time);
            doublingTest.draw(n, (int) time);
            System.out.printf("%7d %7.1f\n", n, time);
        }
    }
}