package collections_demo.queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    static void main() {

        // by default it store as the min heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(12);
        priorityQueue.add(10);
        priorityQueue.add(11);

        System.out.println(priorityQueue.peek()); // gives the top of the heap

        priorityQueue.poll(); // remove the top of the heap
        System.out.println(priorityQueue);

        // we can all pass the comparter to rever the order i.e to max heap for example


        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        // this will internally use maxHeap
        maxPriorityQueue.add(12);
        maxPriorityQueue.add(11);
        maxPriorityQueue.add(39);
        System.out.println(maxPriorityQueue.peek());
        System.out.println( maxPriorityQueue.remove());
        System.out.println(maxPriorityQueue);



    }
}
