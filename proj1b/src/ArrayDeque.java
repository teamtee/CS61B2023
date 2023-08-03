import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int head;
    private int tail;
    private int length;

    public ArrayDeque(){
        items = (T[]) new Object[length];
        head = 0;
        tail = 1;
    }

    @Override
    public void addFirst(T x) {
        if(isFull()){
            return;
        }
        items[head] = x;
        head = ( head + length - 1) % length;
    }

    @Override
    public void addLast(T x) {
        if(isFull()){
            return;
        }
        items[tail] = x;
        tail = (tail + 1 ) % length;
    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (head + 1 ) % length == tail;
    }

    @Override
    public int size() {
        return (tail - head - 1 + length) % length ;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }
        head = (head + 1 ) % length;
        T removed = items[head];
        items[head] = null;
        return removed;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        tail = (tail - 1 + length) % length;
        T removed = items[tail];
        items[tail] = null;
        return removed;
    }

    @Override
    public T get(int index) {
        if ( index + 1 > size()){
            return null;
        }
        return items[(head + index) % length];
    }
    public boolean isFull(){
        return (tail + 1 ) % length == head;
    }
    public static void main(String[] args){
    }
}
