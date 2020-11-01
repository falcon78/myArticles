package Chapter2.Section4;

/**
 * Exercise 2.4.28 (slight variation)
 * Prints N point closest to point (x, y, z) in Euclidean distance.
 */

public class EuclideanDistance3d {
    public static void main(String[] args) throws Exception {
        Point3d origin = new Point3d(5, 1, 2);
        calculate(origin, 10000);
    }

    static class Point3d implements Comparable<Point3d> {
        int x;
        int y;
        int z;
        double distanceFromOriginPoint;

        public Point3d(Point3d origin, int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.distanceFromOriginPoint = Math.sqrt(Math.pow(origin.x - x, 2)
                    + Math.pow(origin.y - y, 2)
                    + Math.pow(origin.z - z, 2)
            );
        }

        public Point3d(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int compareTo(Point3d other) {
            return Double.compare(this.distanceFromOriginPoint, other.distanceFromOriginPoint);
        }
    }

    public static void calculate(Point3d origin, int N) throws Exception {
        MinPriorityQueue<Point3d> mpq = new MinPriorityQueue<>();

        int maxNums = (int) Math.ceil(Math.cbrt(N));

        for (int x = origin.x; x < origin.x + maxNums; x++) {
            for (int y = origin.y; y < origin.y + maxNums; y++) {
                for (int z = origin.z; z < origin.z + maxNums; z++) {
                    if (mpq.size() > N)
                        break;
                    mpq.insert(new Point3d(origin, x, y, z));
                }
            }
        }

        System.out.println(mpq.size());
        while (!mpq.isEmpty()) {
            Point3d min = mpq.removeMin();
            System.out.printf("distance: %f, x: %d, y: %d, z: %d\n", min.distanceFromOriginPoint, min.x, min.y, min.z);
        }
    }
}