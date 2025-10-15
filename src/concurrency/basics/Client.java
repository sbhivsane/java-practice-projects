package concurrency.basics;

public class Client {

    static void main() throws InterruptedException {
        for (int i =1;i<=100;i++){
            Thread printNumber = new Thread(new PrintNumber(i));
            printNumber.start();
        }
        Thread wildCardThread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("This is wildcard thread");
        });
        wildCardThread.start();
        wildCardThread.join();
        System.out.println("Main Thread  After wildcard thread");

        Thread infiniteThread = new Thread(()->{
            try {
                Thread.sleep(2000);
                while(true){

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        infiniteThread.setName("Infinite-Thread");
        infiniteThread.start();
        System.out.println("Main Thread  After starting :"+infiniteThread.getName());

        if(infiniteThread.isAlive()){
            infiniteThread.interrupt();
            System.out.println("Main Thread  After Interupting infinite thread");
        }





    }
}
