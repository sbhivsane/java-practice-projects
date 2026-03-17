package concurrency.synchronization;

import java.util.Collections;
import java.util.List;

public class Demo {
    static void main() {
        List<Integer> integerList = Collections.synchronizedList(List.of(1, 2, 3));
    }
}
