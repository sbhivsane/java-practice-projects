package concurrency.basics;

public class ThreadLifeCycle {

    public static class Task1 implements  Runnable{

        @Override
        public void run() {
            System.out.println("HelloWorld");
        }

    }

    static void main() {

        Task1 t1 = new Task1();
        Task1 t2 = new Task1();




    }


}
