package Stack_Queue;
import java.util.ArrayList;
import java.util.List;

public class ImmQueue<T> {
    private final List<T> list;

    public ImmQueue(){
        list = new ArrayList<>();
    }

    public ImmQueue(List<T> list){
        this.list = list;
    }

    public int size(){
        return list.size();
    }

    public ImmQueue<T> enqueue(T item){
        List<T> list = new ArrayList<>(this.list);
        list.add(item);
        return new ImmQueue<>(list);
    }

    public ImmQueue<T> dequeue(){
        if (size() > 0){
            List<T> list = new ArrayList<>(this.list);
            list.remove(0);
            return new ImmQueue<>(list);
        }else {
            return null;
        }
    }

    public T peek(){
        return size() > 0 ? list.get(0) : null;
    }

}
