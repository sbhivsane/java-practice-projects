package concurrency.executors;

import java.util.concurrent.*;

public class ThreadPoolExecuterExample {

    static void main() {
        ExecutorService executorService = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                new CutomThreadFactory(),
                new CustomRejectedHandler()
                );
        for (int i=1;i<=100;i++){
            int taskId =i;
            executorService.execute(()->{
                System.out.println("Task "+taskId+" executed by "+Thread.currentThread());
            });
        }
        executorService.shutdown();
    }
}

class CustomRejectedHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Denied : "+r.toString());

    }
}

class CutomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        th.setName("RAVAN-GANG-THREAD");
        return th;
    }
}