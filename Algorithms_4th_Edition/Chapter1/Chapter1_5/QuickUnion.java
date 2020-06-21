package Chapter1.Chapter1_5;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The following class represents union-find data type.
 */
public class QuickUnion {
    int[] id;
    int[] size;
    int count;

    /**
     * Initialize arrays.
     *
     * @param N size of the tree.
     */
    public QuickUnion(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            count++;
        }
    }

    /**
     * Check whether two sites p,q are connected
     *
     * @param p - id of a site
     * @param q - id of a site
     * @return - boolean to indicate whether two given sites are connected or not.
     */
    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Returns the number of sets in union tree.
     *
     * @return count of sets in tree.
     */
    int count() {
        return count;
    }

    /**
     * Join two sites if there are not connected.
     * Do nothing if they are already connected.
     *
     * @param p id of a site.
     * @param q id of a site.
     */
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[q] += size[p];
        } else {
            id[qRoot] = pRoot;
            size[p] += size[q];
        }
        count--;
    }

    /**
     * Find the root of a site.
     *
     * @param site id of a site.
     * @return id of the root of the site.
     */
    int find(int site) {
        int root = id[site];
        while (root != id[root]) {
            root = id[root];
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<int[]> arr = GetPairs.pairs("medium");
        QuickUnion quickUnion = new QuickUnion(arr.size());
        for (int[] pair : arr) {
            quickUnion.union(pair[0], pair[1]);
        }

        assert quickUnion.connected(1, 2);
        assert !quickUnion.connected(1, arr.size() - 1);
        assert quickUnion.connected(1, 9);
        System.out.println(quickUnion.count);
    }
}
