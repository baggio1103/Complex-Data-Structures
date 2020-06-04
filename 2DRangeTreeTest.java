import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RangeTreeTest {

    @Test
    public void test(){
        int[] x = {15,5};
        int[] y = {85,90};
        RangeTree tr = new RangeTree(x, y);
        List<Node> list = tr.rangeSearch(tr, 6, 65);
        assertEquals(15, list.get(0).point.xCoordinate);
        assertEquals(85, list.get(0).point.yCoordinate);
    }

    @Test
    public void twoDimRangeSearch(){
        int[] xs = {35, 52, 62, 82, 5, 27, 85,90};
        int[] ys = {42, 10, 77, 65, 45, 35, 15,5};
        RangeTree tree = new RangeTree(xs, ys);
        assertEquals(6, tree.twoDimRangeSearch(15,95,5,65).size());
        assertEquals(8, tree.twoDimRangeSearch(0,100,0,100).size());
    }

    @Test
    public void RangeSearch(){
        int[] xs = {35, 52, 62, 82, 5, 27, 85,90};
        int[] ys = {42, 10, 77, 65, 45, 35, 15,5};
        RangeTree tree = new RangeTree(xs, ys);
        List<Node> leaves = tree.rangeSearch(tree, 28,62);
        assertEquals(35, leaves.get(0).point.xCoordinate);
        assertEquals(42, leaves.get(0).point.yCoordinate);
        assertEquals(52, leaves.get(1).point.xCoordinate);
        assertEquals(10, leaves.get(1).point.yCoordinate);
        assertEquals(62, leaves.get(2).point.xCoordinate);
        assertEquals(77, leaves.get(2).point.yCoordinate);
    }

}
