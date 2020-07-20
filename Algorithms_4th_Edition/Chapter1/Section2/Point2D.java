package Chapter1.Section2;

import Library.StdDraw;

import java.awt.*;
import java.util.ArrayList;

public class Point2D {
    double x;
    double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distanceTo(Point2D a, Point2D b) {
        double xDiff = Math.abs(a.x - b.x);
        double yDiff = Math.abs(a.y - b.y);

        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

    public static void main(String[] args) {
        int N = 20;
        try {
            N = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No argument provided, using default value of 20");
        }

        StdDraw.setXscale(-0.2, 1.2);
        StdDraw.setYscale(-0.2, 1.2);
        StdDraw.setPenRadius(0.01);

        ArrayList<Point2D> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            Point2D point = new Point2D(Math.random(), Math.random());
            list.add(point);
            StdDraw.point(point.x, point.y);
        }

        int closestPointOne = 0, closestPointTwo = 0;
        double closestDistance = 2;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) continue;
                double distance = Point2D.distanceTo(list.get(i), list.get(j));
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPointOne = i;
                    closestPointTwo = j;
                }
            }
        }

        Point2D point1 = list.get(closestPointOne);
        Point2D point2 = list.get(closestPointTwo);

        StdDraw.setPenColor(Color.RED);
        StdDraw.line(point1.x, point1.y, point2.x, point2.y);
    }


}
