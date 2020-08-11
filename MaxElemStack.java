package Stack_Queue;
import java.util.Stack;

public class MaxElemStack {
    private final Stack<Integer> stack;
    private final Stack<Integer> auxiliaryStack;

    public MaxElemStack(){
        stack = new Stack<>();
        auxiliaryStack = new Stack<>();
    }

    public void push(int x){
        if (stack.empty()){
            auxiliaryStack.push(x);
        }else{
            if (x > auxiliaryStack.peek()){
                auxiliaryStack.push(x);
            }else {
                auxiliaryStack.push(auxiliaryStack.peek());
            }
        }
        stack.push(x);
    }

    public int pop(){
        if (size() > 0) {
            stack.pop();
            return auxiliaryStack.pop();
        }else {
            return -1;
        }
    }

    public int peekMax(){
        return auxiliaryStack.peek();
    }

    public int size(){
        return stack.size();
    }

}
