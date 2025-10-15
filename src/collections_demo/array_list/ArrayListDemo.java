package collections_demo.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo {

    static void main() {


        // Adding the elements to the ArrayList

        List<Integer> numbers = new ArrayList<>();

        numbers.add(10); // idx=0
        numbers.add(20); // idx=1
        numbers.add(30);
        numbers.add(40);
        numbers.add(50); // idx=4

        System.out.println(numbers);

        // set 35 on idx 3 : it will set and override the index 3 with value 35
        numbers.set(3,35);

        System.out.println(numbers);

        List<Integer> evenNumbers = new ArrayList<>();
        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);

        numbers.addAll(evenNumbers); // this will add all the evenNumbers at the last of numbers

        System.out.println(numbers);

        List<Integer> oddNumbers = new ArrayList<>();
        oddNumbers.add(1);
        oddNumbers.add(3);
        oddNumbers.add(5);
        numbers.addAll(0,oddNumbers); // this will add all the odd numbers to the start of numbers

        System.out.println(numbers);

        // removing the elements from the Arraylist

     //   numbers.remove(35);// this will give an error as we dont have 35 Index
        // here if we give number directly it is treted as int and acts like index and not element or object of the list

        numbers.remove(Integer.valueOf(35)); // this will the remove the 35 integer object from the numbers

        numbers.remove(1); // this will remove the element at index 1;

        System.out.println(numbers);

        numbers.removeAll(evenNumbers); // this will remove all the elements from numbers that are present in evenNumber

        System.out.println(numbers);

        System.out.println(numbers.contains(Integer.valueOf(30))); // check whether the element 30 is prsent in numbers if yes return true or else false

        System.out.println(numbers.get(3)); // get the elmenet at index 3

        System.out.println(numbers.indexOf(Integer.valueOf(30))); // give the first occurence index of value 30

        System.out.println(numbers.lastIndexOf(Integer.valueOf(30))); // give the Last occurence index of value 30


        System.out.println(numbers.isEmpty()); // check if arrayList is empty

        // iterate over the list

        ListIterator<Integer> listIterator = numbers.listIterator();
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }


        Object[] array = numbers.toArray();
        System.out.println(Arrays.toString(array));
        List<Integer> subList = numbers.subList(2, 5); // get the subList of the arrayList
        System.out.println(subList);


        System.out.println(numbers.size()); // get the size of the arrayList




    }
}
