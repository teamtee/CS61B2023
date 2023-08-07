import org.junit.Assert;
import org.junit.jupiter.api.Test;
import deque.ArrayDeque;
import org.junit.Assert;
public class ArrayDequeTest {
    @Test
    public void TestToString(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(3);
        a.addFirst(3);
        a.addFirst(3);
        Assert.assertEquals("[3, 3, 3]",a.toString());
    }
    @Test
    public void TestEqual(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(3);
        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addFirst(3);
        b.addFirst(2);
        b.addFirst(3);
        Assert.assertTrue(a.equals(b));
    }
}
