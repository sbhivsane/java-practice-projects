package collections_demo.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackDemo {
    static void main() {

        Stack<Integer> numbers = new Stack<>();

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        numbers.pop();
        System.out.println(numbers.peek()); // give the top of the stack
        numbers.addFirst(5); // this method is beacuse of list interface
        // along wih the basic implementation of the stack method it also has method implemented from list interface
        System.out.println(numbers);


    }
}
