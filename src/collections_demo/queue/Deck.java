package collections_demo.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Deck {

    // we can add or remove the elements from both the ends
    // it can be implemented with arrays or with the help of linkedList

    static void main() {
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(10);
        arrayDeque.addFirst(5);

        arrayDeque.addLast(20);
        arrayDeque.addLast(30);
        System.out.println(arrayDeque.peek());
        arrayDeque.removeFirst();
        System.out.println(arrayDeque);
        arrayDeque.removeLast();
        System.out.println(arrayDeque);


    }



}
