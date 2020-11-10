package Chapter2.Section5;

import java.util.*;

/**
 * Exercise 2.5.13
 * Assign jobs to M processors in a way that minimizes the time when the 
 * last job completes using the longest processing time first rule.
 */
class LoadBalancing {
    static class Processor implements Comparable<Processor> {
        private AbstractQueue<Job> jobs = new PriorityQueue<>();
        private int totalProcessingTime = 0;
        
        public int compareTo(Processor that) {
            return Integer.compare(this.totalProcessingTime, that.totalProcessingTime);
        }
        
        public void insert(Job job) {
            totalProcessingTime += job.timeToComplete;
            jobs.add(job);
        }
        
        public Job remove() {
            Job removedJob = jobs.remove();
            totalProcessingTime -= removedJob.timeToComplete;
            return removedJob;
        }
    }
    
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
}