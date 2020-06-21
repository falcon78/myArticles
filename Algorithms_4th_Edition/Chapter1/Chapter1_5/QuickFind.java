package Chapter1.Chapter1_5;

import java.io.IOException;
import java.util.ArrayList;

public class QuickFind {
    private int[] id;
    int count;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            count++;
        }
    }

    public int find(int p) {
        return id[p];
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];

        if (pId == qId) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) id[i] = qId;
        }
        count--;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<int[]> arr = GetPairs.pairs("medium");
        QuickFind quickFind = new QuickFind(arr.size());
        for (int[] pair : arr) {
            quickFind.union(pair[0], pair[1]);
        }

        assert quickFind.connected(1, 2);
        assert !quickFind.connected(1, arr.size() - 1);
        assert quickFind.connected(1, 9);
    }
}
