package deque;

import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    private T[] items;
    private int head;
    private int tail;
    private int length = 100;

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
    public boolean contains(T item){
        for(T x: this){
            if(x == item){
                return true;
            }
        }
        return false;
    }
    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    public boolean isFull(){
        return (tail + 1 ) % length == head;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    public class ArrayDequeIterator implements Iterator<T>{
        private int pointor;
        public ArrayDequeIterator(){
            pointor = head;
        }

        @Override
        public boolean hasNext() {
            return (pointor + 1) % length != tail;
        }

        @Override
        public T next() {
            pointor = (pointor + 1) % length;
            return items[pointor];
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayDeque oas){
            if(oas.size() != size()){
                return false;
            }
            for (T item: this){
                if (!oas.contains(item)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        Iterator<T> it = iterator();
        if(!it.hasNext()){
            return "[]";
        }
        StringBuilder strings = new StringBuilder("[");
        while (true){
            strings.append(it.next().toString());
            if(!it.hasNext()){
                return strings.append(']').toString();
            }
            strings.append(", ");
        }
    }
    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(3);
        a.addFirst(3);
        a.addFirst(3);
        System.out.println(a.toString());
    }
}
