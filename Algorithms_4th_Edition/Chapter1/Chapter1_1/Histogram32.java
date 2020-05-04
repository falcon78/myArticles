package Chapter1.Chapter1_1;

import Library.StdDraw;

/*
Exercise 1.1.32 Page 60
 */
public class Histogram32 {
    public static void main(String[] args) {
        histogram(
                1,
                1.0,
                10.0,
                new double[]{0.5, 1.0, 2.0, 2.0, 2.0, 2.8, 3.0, 3.0,
                        3.0, 3.0, 3.3, 4.0, 4.5, 4.5, 4.6, 4.8, 5.0,
                        5.5, 5.6, 5.7, 7.7, 7.7, 8.1, 8.8, 8.8, 9.8, 10.0
                }
        );
    }

    public static void histogram(int N, double l, double r, double[] doubles) {
        int numOfElements = (int) Math.ceil((r - l) / N);
        int[] histogram = new int[numOfElements];

        for (double value : doubles) {
            if (value >= l && value < r) {
                int index = (int) Math.floor((value - l) / N);
                histogram[index] += 1;
            }
        }

        StdDraw.setXscale(-1, 4);
        StdDraw.setYscale(-1, 4 * 4);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < histogram.length; i++) {
            StdDraw.filledRectangle(
                    1.0 * i / histogram.length,
                    histogram[i] / 2.0,
                    0.2 / N,
                    histogram[i] / 2.0
            );
        }
    }
}
