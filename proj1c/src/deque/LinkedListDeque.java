package deque;

import net.sf.saxon.functions.ConstantFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private  Node sentitial;
    private Integer size;
    private class LinkedListDequeIterator implements Iterator<T>{
        private Node pointor;
        public LinkedListDequeIterator(){
            pointor = sentitial;
        }
        @Override
        public boolean hasNext() {
            return pointor.next != sentitial;
        }

        @Override
        public T next() {
            pointor = pointor.next;
            return pointor.item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public class Node{
        T item;
        Node prev;
        Node next;
        public Node(){
        }
        public Node(T inputItem,Node prevNode,Node nextNode){
            item = inputItem;
            prev = prevNode;
            next = nextNode;
        }

    }
    public LinkedListDeque(){
        sentitial = new Node();
        sentitial.prev = sentitial;
        sentitial.next = sentitial;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentitial.next.prev = new Node(x,sentitial,sentitial.next);
        sentitial.next = sentitial.next.prev;
        size++;
    }

    @Override
    public void addLast(T x) {
        sentitial.prev.next = new Node(x,sentitial.prev,sentitial);
        sentitial.prev = sentitial.prev.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node temp = sentitial;
        for(Integer iter = 0; iter < size;iter++){
            temp = temp.next;
            list.add(temp.item);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        Node temp = sentitial.next;
        sentitial.next = sentitial.next.next;
        sentitial.next.prev = sentitial;
        return  temp.item;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        Node temp = sentitial.prev;
        sentitial.prev = sentitial.prev.prev;
        sentitial.prev.next = sentitial;
        return  temp.item;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > size - 1){
            return null;
        }
        Node temp = sentitial.next;
        for(Integer iter = 0 ; iter < index;iter++){
            temp = temp.next;
        }
        return temp.item;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index,sentitial);
    }
    public T getRecursive(int index,Node point) {
        if (index == 0) {
            return point.next.item;
        }
        return getRecursive(index - 1, point.next);
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
    public boolean equals(Object obj){
        if ( obj instanceof LinkedListDeque oas){
            if (oas.size() != size()){
                return false;
            }
            for(T item: this){
                if(!oas.contains(item)){
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
}
