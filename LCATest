import org.junit.Test;

import static org.junit.Assert.*;

public class LCATest {

    @Test
    public void query() {
        int[] numbers = {90, 13, 57, 26, 76, 66, 53, 37};
        LCA lca = new LCA(numbers);
        assertEquals(13,lca.query(0,3));
        assertEquals(13,lca.query(0,1));
        assertEquals(26,lca.query(2,3));
        assertEquals(13,lca.query(1,3));
        assertEquals(26,lca.query(2,5));
        assertEquals(26,lca.query(2,7));
        assertEquals(13,lca.query(1,5));
        assertEquals(26,lca.query(2,6));
    }
}
