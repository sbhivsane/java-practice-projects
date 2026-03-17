package concurrency.basics;

public class MoniterClient {
    static void main() throws InterruptedException {
        MoniterLockConcept m = new MoniterLockConcept();

        Thread t1 = new Thread(()->m.task1(1));
        Thread t2 = new Thread(()->m.task2(2));
        Thread t3 = new Thread(()->m.task3(3));

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        System.out.println(t3.getState());
    }

    // Moniter Lock :
    // Moniter lock are use to restrict more than one
    // thread to execute the peice of code simultaneously
    // when we tak lock on object of the class then we
    // cant enter the sychronized method only one can execute at a time
    // simlar we can take lock on any object via synchronized block

}
