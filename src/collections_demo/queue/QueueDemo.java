package collections_demo.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    static void main() {

        Queue<Integer> numberQueue = new LinkedList<>();

        numberQueue.add(2);
        numberQueue.add(1);
        numberQueue.add(3);
         // give the first element of the queue
        System.out.println(numberQueue.peek());

        System.out.println(numberQueue.remove());  // will remove the fisrt element of the quue
        System.out.println(numberQueue);

    }
}
