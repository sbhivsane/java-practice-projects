package misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysToList {
    static void main() {
        int[] nums = {1,2,3,4,5};
        Integer[] nums2 ={1,3,2323,3,23};
        List<Integer> numberList2 = Arrays.asList(nums2);
        List<Integer> numberList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numberList2.add(45);
        numberList.add(6);
        System.out.println(numberList2);
        System.out.println(numberList);

    }
}
