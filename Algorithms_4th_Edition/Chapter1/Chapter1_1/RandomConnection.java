package Chapter1.Chapter1_1;

import Library.StdDraw;

import java.util.Random;

/*
Exercise 1.1.31 Page 59
 */
public class RandomConnection {

    public static void main(String[] args) {
        connect(
                Integer.parseInt(args[0]),
                Double.parseDouble(args[1])
        );
    }

    public static void connect(int count, double probability) {
        double[][] dots = new double[count][2];

        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(-1.2, 1.2);
        StdDraw.setYscale(-1.2, 1.2);

        double twoPi = 2 * Math.PI;
        for (int i = 0; i < count; i++) {
            double radian = twoPi * i / count;
            dots[i][0] = Math.cos(radian);
            dots[i][1] = Math.sin(radian);
            StdDraw.point(dots[i][0], dots[i][1]);
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (getProbability(probability)) {
                    StdDraw.line(dots[i][0], dots[i][1], dots[j][0], dots[j][1]);
                }
            }
        }
    }

    public static boolean getProbability(double probability) {
        Random random = new Random();
        double rand = random.nextDouble();
        System.out.println(rand + " " + probability);
        return rand < probability;
    }

}
