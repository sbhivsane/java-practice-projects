package concurrency.futuresandcallables;

import concurrency.basics.ThreadLifeCycle;

import java.util.List;
import java.util.concurrent.*;

class Task1 implements Callable<List<Integer>>{

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> integers = List.of(2, 4, 6, 8, 10);
        return integers;
    }
}

public class CallableExample {
    static void main() throws ExecutionException, InterruptedException {
        // callable is simillar to runable but it can return some value

        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));



        Future<List<Integer>> submit = executorService.submit(new Task1());
        Thread.sleep(1000);

        if(submit.isDone()){
            System.out.println(submit.get());
        }

        executorService.shutdown();




    }
}
