package Chapter1.Section3;

public class JosephusProblem {
    public static void main(String[] args) {
        int N = 10;
        int M = 2;
        if (args.length >= 2) {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        }


        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>(N);
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }

        for (int i = 1; queue.size() > 0; i++) {
            if (i % M == 0) {
                System.out.println(queue.dequeue());
            } else {
                queue.enqueue(queue.dequeue());
            }
        }

    }
}
