package concurrency.basics;

import java.util.ArrayList;
import java.util.List;

public class FactorialClient {
    static void main() throws InterruptedException {
        List<Integer> numberList = List.of(3432434,523424,10234,2342);
        List<Thread> factorialThreadList = new ArrayList<>();
        List<FactorialComputer> factorialTaskList = new ArrayList<>();
        for (Integer num:numberList){
            FactorialComputer factorialComputer = new FactorialComputer(num);
            factorialTaskList.add(factorialComputer);
            Thread factorial = new Thread(factorialComputer);
            factorialThreadList.add(factorial);
        }

        for (Thread t : factorialThreadList)
            t.start();


        for (Thread t : factorialThreadList)
            t.join(2000);



        for(int i=0;i<factorialThreadList.size();i++){
            Thread thread = factorialThreadList.get(i);
            if(!thread.isAlive()){

                factorialTaskList.get(i).getFactorial();
            }else{
                System.out.println("task did not completed in 2 s");
            }
        }
    }
}
