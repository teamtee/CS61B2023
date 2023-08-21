import java.util.ArrayList;

public class QuickUnion {
    private int[] items;
    public QuickUnion(int n){
        items = new int[n];
    }
    public void validate(int v1){
        if(v1 > items.length){
            throw new IndexOutOfBoundsException();
        }
    }
    public int parents(int v1){
        return items[v1];
    }
    public int sizeOf(int v1){
        int size = 0;
        int root = find(v1);
        for (int x: items){
            if( root == find(x)){
                size++;
            }
        }
        return size;
    }
    public boolean isConnected(int v1,int v2){
        return find(v1) == find(v2);
    }
    public void  union(int v1,int v2){
        if(sizeOf(v1) >= sizeOf(v2)){
            connect(find(v1),find(v2));
            return;
        }
        connect(find(v2),find(v1));
        return;
    }
    private void connect(int v1,int v2){
        if (items[v1] < 0){
            if (items[v2] < 0){
                items[v1] = items[v1] + items[v2];
            }
            else {
                items[v1] = items[v1] - 1;
            }
            items[v2] = v1;
        }
    }
    public int find(int v1){
        int node = v1;
        ArrayList<Integer> path = new ArrayList<>();
        while (items[node] >= 0 && items[node] != node){
            path.add(node);
            node = items[node];
        }
        for(int x: path){
            items[x] = node;
        }
        return node;
    }
}
