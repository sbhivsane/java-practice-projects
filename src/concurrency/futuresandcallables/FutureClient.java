package concurrency.futuresandcallables;

import java.util.concurrent.*;

class SampleTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 101;
    }
}

public class FutureClient {
    static void main() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );
        boolean tryToCancleTask = true;
        Future<?> futureObject = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("This is the Task done by Thread");
        });
        if(tryToCancleTask){
            futureObject.cancel(true);
        }else{
            Thread.sleep(5000);

        }
        if(futureObject.isDone()){
            System.out.println("Task is Done");
        }
        if(futureObject.isCancelled()){
            System.out.println("Task is Canneled");
        }
        executorService.shutdown();

    }
}
