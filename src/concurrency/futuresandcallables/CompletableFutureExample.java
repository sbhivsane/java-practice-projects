package concurrency.futuresandcallables;


import java.util.concurrent.*;

public class CompletableFutureExample {

    static void main() throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    return "Task Completed";
                },executorService
        );



        System.out.println(completableFuture.get());

        System.out.println("-----------------------------------------------------");


        CompletableFuture<String> completableFuture1= CompletableFuture.supplyAsync(
                ()->{
                    return "Task Completed";
                },executorService
        ).thenApplyAsync((String str)->{
            return str+" By Some Thread";
        });

        System.out.println(completableFuture1.get());

        System.out.println("-----------------------------------------------------");

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("This is ASync Task1 by " + Thread.currentThread().getName());
            return "Concept and ";
        }).thenCompose((String str) -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("This is Async Task2 by " + Thread.currentThread().getName());
                return str + "Coding";
            });
        });

        System.out.println(stringCompletableFuture.get());

        System.out.println("-----------------------------------------------------");

        //  then accept the last stage of chaing does not return any thing

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("This is Async Task1 by Thread : " + Thread.currentThread().getName());
            return "Some Sample String";
        }).thenAccept((String str) -> {
            System.out.println("This is " + str + "  from previous async opreation");
        });
        System.out.println("Ayo Ayo Rama");
        System.out.println(voidCompletableFuture.get());

        System.out.println("-----------------------------------------------------");

        // then combine is used to combine the result of 2 async tasks

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("This is Async Task1 By Thread : " + Thread.currentThread().getName());
            return 10;
        });


        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("This is Async Task2 By Thread : " + Thread.currentThread().getName());
            return 20;
        });


        CompletableFuture<Integer> integerCompletableFuture2 = integerCompletableFuture.thenCombine(integerCompletableFuture1, (val1, val2) -> {
            return val1 + val2;
        });

        System.out.println(integerCompletableFuture2.get());
        executorService.shutdown();
    }



}

// Question :
// 1] how are theads comping in completablefulture what is diffrence between executer and this ;
// 2] what , how ,why
