package Stack_Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaxElemStackTest {

    @Test
    public void pop() {
        MaxElemStack stack = new MaxElemStack();
        stack.push(4);
        assertEquals(4, stack.peekMax());
        stack.push(2);
        assertEquals(4, stack.peekMax());
        stack.push(14);
        assertEquals(14, stack.peekMax());
        stack.push(1);
        assertEquals(14, stack.peekMax());
        stack.push(18);
        assertEquals(18, stack.peekMax());
        stack.pop(); // removes 18
        assertEquals(14,stack.peekMax());
        stack.pop(); // removes 1
        assertEquals(14,stack.peekMax());
        stack.pop(); // removes 14
        assertEquals(4, stack.peekMax());

    }
}