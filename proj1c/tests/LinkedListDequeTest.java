import deque.ArrayDeque;
import org.junit.Assert;
import org.junit.Test;
import deque.LinkedListDeque;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
public class LinkedListDequeTest {
    @Test
    public void testToString(){
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(3);
        a.addFirst(3);
        a.addFirst(3);
        assertThat(a.toString()).isEqualTo("[3, 3, 3]");
    }
    @Test
    public void TestEqual(){
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(3);
        LinkedListDeque<Integer> b = new LinkedListDeque<>();
        b.addFirst(3);
        b.addFirst(2);
        b.addFirst(3);
        String msg = "Error!";
        assertWithMessage(msg).that(a.equals(b)).isEqualTo(true);
    }
}
