package IntervalTrees;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class IntervalTreeTest {

    @Test
    public void search() {
        int[][] intervals = {{15, 20}, {10, 30}, {17, 19}, {5, 20}, {12, 15}, {30, 40}};
        IntervalTree tree = new IntervalTree(intervals);
        assertArrayEquals(new int[]{5, 20}, tree.search(new int[]{6,7}));
        assertArrayEquals(new int[]{30, 40}, tree.search(new int[]{25,35}));
        assertArrayEquals(new int[]{15, 20}, tree.search(new int[]{16,18}));
        assertArrayEquals(new int[]{5, 20}, tree.search(new int[]{6,9}));
        assertArrayEquals(new int[]{15, 20}, tree.search(new int[]{18,21}));
    }
}
