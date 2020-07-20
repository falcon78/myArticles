package Chapter1.Section5;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Exercise 1.5.14
 * Implementation of quick-union data type. (Weighted Quick Union (ranked by height instead of node count.))
 */
public class WeightedQuickUnionByHeight {
    private int[] sites;
    private int[] height;
    public int count;

    public WeightedQuickUnionByHeight(int N) {
        sites = new int[N];
        height = new int[N];
        for (int i = 0; i < N; i++) {
            sites[i] = i;
            height[i] = 1;
        }
        count = N;
    }

    public int count() {
        return count;
    }

    public int find(int site) {
        int root = sites[site];
        while (root != sites[root]) {
            root = sites[root];
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        // join (union) smaller tree to a bigger one
        if (height[pRoot] > height[qRoot]) {
            sites[qRoot] = pRoot;
        } else if (height[pRoot] < height[qRoot]) {
            sites[pRoot] = qRoot;
        } else {
            // if the trees have same size, merge them in no particular order.
            sites[pRoot] = qRoot;
            // Height of a tree only grows if the trees being merged are of same height.
            height[qRoot] += 1;
        }
        count--;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<int[]> arr = GetPairs.pairs("large");
        WeightedQuickUnionByHeight quickUnion = new WeightedQuickUnionByHeight(arr.size());
        for (int[] pair : arr) {
            quickUnion.union(pair[0], pair[1]);
        }

        assert quickUnion.connected(1, 2);
        assert !quickUnion.connected(1, arr.size() - 1);
        assert quickUnion.connected(1, 9);
        System.out.println(quickUnion.count);

        WeightedQuickUnionByHeight quTest = new WeightedQuickUnionByHeight(20);
        assert !quTest.connected(10, 11);
        quTest.union(10, 11);
        assert quTest.connected(10, 11);

        assert !quTest.connected(11, 9);
        quTest.union(11, 9);
        assert quTest.connected(11, 9);

        assert !quTest.connected(3, 9);
        quTest.union(3, 9);
        assert quTest.connected(3, 9);

        quTest.union(5, 3);
        quTest.union(4, 6);
        quTest.union(6, 3);
        assert quTest.connected(4, 9);
        assert quTest.connected(4, 11);
        assert quTest.connected(10, 6);
    }
}
