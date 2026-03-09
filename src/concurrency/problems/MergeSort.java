package concurrency.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MergeSort {

    // here its not good idea to use executer service directly , if thread pool size is less then chance of
    // deadlock , should not use chace TP as well as lot of recursive call
    // might affect the  performance
    // hece we should Use ForkJoinPool
    private static class  Sorter implements Callable<List<Integer>>{
        List<Integer> arr = null;
        ExecutorService executorService = null;
        public Sorter(List<Integer> arr, ExecutorService executorService) {
                this.arr = arr;
                this.executorService = executorService;
        }

        @Override
        public List<Integer> call() throws Exception {

            if(arr.size()<=1) return arr;

            int n = arr.size();
            int half = n/2;

            // separate the left half and right half of the list
             List<Integer> leftHalf = new ArrayList<>();
             List<Integer> rightHalf = new ArrayList<>();

            for(int i=0;i<arr.size();i++){
                    if(i<half){
                        leftHalf.add(arr.get(i));
                    }else{
                        rightHalf.add(arr.get(i));
                    }
                }

            // sort the left and right half
            Future<List<Integer>> leftSortedList = executorService.submit(new Sorter(leftHalf, executorService));
            Future<List<Integer>> rightSortedList = executorService.submit(new Sorter(rightHalf, executorService));


            // merge the sorted left and right
            List<Integer> integerList = leftSortedList.get();
            List<Integer> integerList1 = rightSortedList.get();
            return mergeSortedList(integerList,integerList1);
        }

        private List<Integer> mergeSortedList(List<Integer> left,List<Integer> right){
            List<Integer> result = new ArrayList<>();
            int i=0;
            int j=0;
            while(i<left.size()&&j<right.size()){
                if(left.get(i)< right.get(j)){
                    result.add(left.get(i));
                    i++;
                }else{
                    result.add(right.get(j));
                    j++;
                }
            }
            if(i<left.size()){
                while (i<left.size()){
                    result.add(left.get(i));
                    i++;
                }
            }

            if(j<right.size()){
                while (j<right.size()){
                    result.add(right.get(j));
                    j++;
                }
            }

            return result;
        }
    }

    static void main() throws ExecutionException, InterruptedException {

        List<Integer> integerList = List.of(2, 1, 3, 6, 4, 8);
        //ExecutorService executorService = Executors.newCachedThreadPool();
        // deadLock example;
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<List<Integer>> sortedFuture = executorService.submit(new Sorter(integerList, executorService));

        List<Integer> integerList1 = sortedFuture.get();
        System.out.println(integerList1);
    }
}
