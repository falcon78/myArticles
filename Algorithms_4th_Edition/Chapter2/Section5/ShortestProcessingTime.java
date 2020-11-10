package Chapter2.Section5;

import Chapter2.Section4.MinPriorityQueue;
import Library.StdRandom;

/**
 * Exercise 2.5.12
 * Program that prints a schedule that minimizes average completion time using
 * the shortest processing time first rule.
 */
public class ShortestProcessingTime {
    MinPriorityQueue<Job> jobQueue = new MinPriorityQueue<>();

    static class Job implements Comparable<Job> {
        String job;
        int timeToComplete;

        public Job(String job, int timeToComplete) {
            this.job = job;
            this.timeToComplete = timeToComplete;
        }

        public int compareTo(Job that) {
            return Integer.compare(this.timeToComplete, that.timeToComplete);
        }

        public String toString() {
            return this.job + ": " + this.timeToComplete;
        }
    }

    public void add(Job job) throws Exception {
        jobQueue.insert(job);
    }

    public Job removeJob() {
        return jobQueue.removeMin();
    }

    public boolean isEmpty() {
        return jobQueue.size() < 1;
    }

    public static void main(String[] args) throws Exception {
        ShortestProcessingTime spt = new ShortestProcessingTime();
        int N = 10;
        for (int i = 0; i < N; i++) {
            spt.add(new Job("job" + i, StdRandom.uniform(0, 100)));
        }
        while (!spt.isEmpty()) {
            Job j = spt.removeJob();
            System.out.println(j);
        }
    }
}
