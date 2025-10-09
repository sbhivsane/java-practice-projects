package arrays;

import java.util.Arrays;

public class ArraysDemo {

    static void main() {
        // initialize the array
        int[] numbers = {11,2,33,24,15};

        // declar the arrays of size 5
        int[] fiveNumbers = new int[5];

        // update the  each index manually by default its set to 0
        fiveNumbers[0]=1;

        // print array
        for (int num :numbers){
            System.out.println(num);
        }

        System.out.println("Arrays Before Sorting : "+Arrays.toString(numbers));

        // sorting the elements in the array
        // by default use natuaral order of sorting
        Arrays.sort(numbers);

        System.out.println("Arrays After Sorting : "+Arrays.toString(numbers));

        String[] names ={"aaa","aaa","aad","q","abb"};

        System.out.println("Arrays Before Sorting : "+Arrays.toString(names));

        Arrays.sort(names);

        System.out.println("Arrays After Sorting : "+Arrays.toString(names));

        //Arrays of custom objects

        Employee[] employee = {new Employee("aac",26,11.3),new Employee("aaa",26,10.2),
        new Employee("aab",25,100.00)};


        System.out.println("employee Arrays before sorting : "+Arrays.toString(employee));

        Arrays.sort(employee);

        System.out.println("employee Arrays after sorting : "+Arrays.toString(employee));


        //  for explict sorting we can use comparater class
        // we compare string based on the asci value
        /*
        * +ve = if o1 is small
        * 0 = if both o1 and o2 are equal
        * -ve = if o2 is small
        *
        * */

        Arrays.sort(employee,(o1,o2)->{
           return o1.name.compareTo(o2.name);
        });

        System.out.println("employee Arrays after sorting by Name : "+Arrays.toString(employee));











    }





}
