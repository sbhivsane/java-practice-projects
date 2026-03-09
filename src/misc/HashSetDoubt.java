package misc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetDoubt {
    static void main() {

        List<Integer> integerList = List.of(1, 2, 2);
        List<Integer> integerList1 = List.of(1, 2, 2);
        System.out.println(integerList.equals(integerList1));

        List<Integer> integerList2 = List.of(2, 1, 2);
        System.out.println(integerList.equals(integerList2));
        // IN List datastructure order is matter heence even though elements are same but order is
        // diffrenet this this comaprision yeild as false

        //********************************* Set **********************************
        Set<Integer> integersSet1 = Set.of(1,2);
        Set<Integer> integersSet2 = Set.of(1,2);
        System.out.println(integersSet1.equals(integersSet2)); // true

        Set<Integer> integersSet3 = Set.of(2,1);
        System.out.println(integersSet1.equals(integersSet3)); // true
        // In Set Sequence of elements does not matter hence if elements are same
        // and order is diffrener then also set will treat it as same




        // hasset should have distict elements in order or elmnets

    }
}
