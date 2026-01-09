import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class concurrentDemo {
    public static void main(String[] args) {
        // List
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);

        // Map
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        // Set
        Set<String> set = new CopyOnWriteArraySet<>();
        set.add("a");
        set.add("b");

        // Double-ended queue
        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
    }
}
