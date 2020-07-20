package Chapter1.Section4;

public class Stopwatch {
    private long time;

    public Stopwatch() {
        time = System.currentTimeMillis();
    }

    public double elapsedTime() {
        return System.currentTimeMillis() - time;
    }
}
