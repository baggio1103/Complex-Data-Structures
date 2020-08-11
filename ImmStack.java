package Stack_Queue;
import java.util.ArrayList;
import java.util.List;

public class ImmStack<T> {
    private final List<T> list;

    public ImmStack() {
        list = new ArrayList<>();
    }

    public ImmStack(ArrayList<T> list){
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public ImmStack<T> pop() {
        if (list.size() > 0) {
            ArrayList<T> temp = new ArrayList<>(list);
            temp.remove(0);
            return new ImmStack<T>(temp);
        } else
            return null;
    }

    public ImmStack<T> push(T val) {
        ArrayList<T> temp = new ArrayList<>(list);
        temp.add(0, val);
        return new ImmStack<>(temp);
    }

    public T peek() {
        if (list.size() > 0) {
            return (T) list.get(0);
        } else
            return null; //if stack is empty
    }

}