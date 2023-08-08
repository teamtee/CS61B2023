import java.util.*;

public class BSTMap<K extends Comparable,V> implements Map61B<K,V> {
    private BST root;
    private class BST {
        private K key;
        private V value;
        private BST left;
        private BST right;
        public BST(){
        }
        public BST(K inputKey,V inputValue){
            value = inputValue;
            key = inputKey;
        }
    }
    public BSTMap(){
        root = null;
    }
    public BSTMap(K key,V value){
        root = new BST(key,value);
    }
    @Override
    public void put(K key, V value) {
        root = put(root,key,value);
    }
    private BST put(BST T,K key,V value) {
        if (T == null) {
            return new BST(key, value);
        } else if (T.key.compareTo(key) > 0) {
            T.left = put(T.left, key, value);
        } else if (T.key.compareTo(key) < 0) {
            T.right = put(T.right, key, value);
        } else {
            T.value = value;
        }
        return T;
    }
    @Override
    public V get(K key) {
        return get(root,key);
    }
    public V get(BST T,K key) {
        if (T == null){
            return null;
        }
        else if (T.key.compareTo(key) > 0){
            return get(T.left,key);
        }
        else if (T.key.compareTo(key) < 0){
            return get(T.right,key);
        }
        return T.value;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key);
    }
    public boolean containsKey(BST T,K key) {
        if (T == null){
            return false;
        }
        else if (T.key.compareTo(key) > 0){
            return containsKey(T.left,key);
        }
        else if (T.key.compareTo(key) < 0){
            return containsKey(T.right,key);
        }
        return true;
    }

    @Override
    public int size() {
        return size(root);
    }
    public int size(BST T){
        if (T == null){
            return 0;
        }
        return size(T.left) + size(T.right) + 1;
    }
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        ArrayList<BST> L = new ArrayList<>();
        L.add(root);
        while (!L.isEmpty()){
            BST node = L.remove(0);
            set.add(node.key);
            if (node.left != null){
                L.add(node.left);
            }
            if (node.right != null){
                L.add(node.right);
            }
        }
        return set;
    }
    public ArrayList<K> inOrder(){
        ArrayList<K> list = new ArrayList<K>();
        inOrder(root,list);
        return list;
    }
    public void inOrder(BST T,ArrayList<K> list){
        if (T == null){
            return;
        }
        inOrder(T.left,list);
        list.add(T.key);
        inOrder(T.right,list);
    }
    @Override
    public V remove(K key) {
        V temp = get(root,key);
        if (temp != null){
            root = remove(root,key);
            return temp;
        }
        else
            return null;
    }
    private BST remove(BST T,K key) {
        if (T == null){
            return null;
        }
        else if (T.key.compareTo(key) > 0){
            T.left = remove(T.left,key);
        }
        else if (T.key.compareTo(key) < 0){
            T.right = remove(T.right,key);
        }
        else if (T.left == null){
            return T.right;
        }
        else if (T.right == null){
            return T.left;
        }
        else {
            T.right = swapRightMin(T,T.right);
        }
        return T;
    }
    private BST swapRightMin(BST R,BST T){
        if (T.left == null){
            root.key = T.key;
            root.value = T.value;
            return T.right;
        }
        else {
            T.left = swapRightMin(R,T.left);
            return T.left;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return null;
    }
    public class MapIterator implements Iterator<K>{
        private BST root;
        private BST nowNode;
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    }
}
