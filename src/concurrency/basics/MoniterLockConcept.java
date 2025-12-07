package concurrency.basics;

import java.util.ArrayList;
import java.util.List;

public class MoniterLockConcept {
    List<Integer> numberList = new ArrayList<>();


    public synchronized void task1(Integer number){
        System.out.println("This is Task1 By thread : "+number);
        while(true){
            ;
        }
    }

    public synchronized void task11(Integer number){
        System.out.println("This is Task11 By thread : "+number);
        while(true){
            ;
        }
    }
    public void task2(Integer number){
        System.out.println("This is Task2 By Thread : "+number);
        System.out.println("This is before synchronized");
        synchronized(numberList){
            System.out.println("This is inside Task2 Sync Block");
            while (true){
                ;
            }
        }
    }

    public void task22(Integer number){
        System.out.println("This is Task22 By Thread : "+number);
        System.out.println("This is before synchronized");
        synchronized(numberList){
            System.out.println("This is inside Task22 Sync Block");
            while (true){
                ;
            }
        }
    }

    public void task3(Integer number){
        System.out.println("This is task 3 By Thread ; "+number);
    }

}
