package sorting_algo;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    static void main() {
        List<Integer> integers = new ArrayList<>(List.of(21, 23, 11, 1, 23));
        mergeSort(integers,0,integers.size()-1);
        System.out.println(integers);
    }

    private static void mergeSort(List<Integer> integers,int l , int r){
        if(l>=r)
            return ;
        int mid = (l+r)/2;
        mergeSort(integers,l,mid);
        mergeSort(integers,mid+1,r);
        merge(integers,l,mid,r);
    }

    private static void merge(List<Integer> integers,int l ,int mid ,int r){
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i=l;i<=mid;i++){
            left.add(integers.get(i));
        }
        for(int i=mid+1;i<=r;i++){
            right.add(integers.get(i));
        }
        int i=0;
        int j =0;
        int k =l;
        while(i<left.size()&&j<right.size()){
            if(left.get(i)<=right.get(j)){
                integers.set(k,left.get(i));
                i++;
                k++;
            }else{
                integers.set(k,right.get(j));
                j++;
                k++;
            }
        }

        while(i<left.size()){
                integers.set(k,left.get(i));
                i++;
                k++;
        }

        while(j<right.size()){
                integers.set(k,right.get(j));
                j++;
                k++;
        }

    }


}
