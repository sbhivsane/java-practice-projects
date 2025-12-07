package sorting_algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CountSort {
    static void main() {
        positiveValuesCountSort();

        // TC: O(N);
        // SC : O(1);
        // count sort cant work for large values say around 10^9;
        System.out.println("----------------------------------------");
        negativeValuesCountSort();


    }

    private static void negativeValuesCountSort() {
        List<Integer> integers = List.of(21, 12, 22, -3, 45, 1,12);
        int max = integers.stream().max(Integer::compare).get();
        Integer min = Math.abs(integers.stream().min(Integer::compare).get());
        List<Integer> freqArray = new ArrayList<>(Collections.nCopies(max+min+1,0));
        for (Integer i :integers){
            freqArray.set(i+min, freqArray.get(i+min)+1);
        }

        for (int i=0;i<=max;i++){
            Integer times = freqArray.get(i);
            for(int j=1;j<=times;j++){
                System.out.println(i-min);
            }
        }
    }


    private static void positiveValuesCountSort() {
        List<Integer> integers = List.of(21, 12, 22, 3, 45, 1,12);
        int max = integers.stream().max(Integer::compare).get();
        List<Integer> freqArray = new ArrayList<>(Collections.nCopies(max+1,0));
        for (Integer i :integers){
            freqArray.set(i, freqArray.get(i)+1);
        }

        for (int i=0;i<=max;i++){
            Integer times = freqArray.get(i);
            for(int j=1;j<=times;j++){
                System.out.println(i);
            }
        }
    }
}
