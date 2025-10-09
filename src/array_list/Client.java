package array_list;

import java.util.Arrays;

public class Client {
    static void main() {
        SampleDynamicArrayImpl arr = new SampleDynamicArrayImpl(3);

        arr.add(1);
        arr.add(2);
        arr.add(3);

        arr.printTheArray();

        System.out.println("size: "+arr.getSizeOfArray());
        System.out.println("capacity: "+arr.getArrayCapacity());
        arr.add(4);
        arr.printTheArray();

        System.out.println("size: "+arr.getSizeOfArray());
        System.out.println("capacity: "+arr.getArrayCapacity());

    }
}
