public class QuickFind {
    private int[] items;
    public QuickFind(int n){
        items = new int[n];
    }
    public void validate(int v1){
        if(v1 > items.length){
            throw new IndexOutOfBoundsException();
        }
    }
    public int sizeOf(int v1){
        int setValue = items[v1];
        int count = 1;
        for(int x: items){
            if( x == setValue){
                count++;
            }
        }
        return  count;
    }
    public boolean isConnected(int v1,int v2){
        return items[v1] == items[v2];
    }
    public void  connect(int v1,int v2){
        int setValue = items[v1];
        for(int x = 0 ; x < items.length;x++){
            if(items[x] == items[v2]){
                items[x] = setValue;
            }
        }
    }
}
