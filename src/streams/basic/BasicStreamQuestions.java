package streams.basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BasicStreamQuestions {

    static void main() {

        Map<Integer ,Integer> freqMap = new HashMap<>();
        freqMap.put(1, freqMap.getOrDefault(1,0)+1);
        freqMap.put(1, freqMap.getOrDefault(1,0)+1);
        freqMap.put(1, freqMap.getOrDefault(1,0)+1);
        System.out.println(freqMap);




        // 1. Given a list of integers, print all even numbers using Java Streams.
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        integers.stream().filter(i->i%2==0).forEach(System.out::println);
        
        //2. Given a list of strings, convert all strings to uppercase and collect them into a new list.

        List<String> names = List.of("sagar", "kamlesh", "nilesh");
        List<String> upperCaseName = names.stream().map(String::toUpperCase).toList();
        System.out.println(upperCaseName);

        //3. From a list of numbers, find and print only the distinct elements.
        List<Integer> integersWithDuplicate = List.of(1, 2, 3, 4, 5,2,4,5);
        integers.stream().distinct().forEach(System.out::println);

        //4. Given a list of integers, find the sum of all elements using Streams.
        List<Integer> integers2 = List.of(1, 2,3);
        //Integer sumOfElements = integers2.stream().collect(Collectors.summingInt((val1) -> val1));
        int sum = integers2.stream().mapToInt(i -> i).sum();
        System.out.println(sum);

        //5. Given a list of integers, find the maximum and minimum numbers using Streams.
        List<Integer> integers3 = List.of(1, 2,3);
        int max = integers3.stream().mapToInt(i -> i).max().getAsInt();
        Integer min = integers3.stream().min((i1,i2)->i1-i2).get();
        System.out.println("Max: "+max+" Min : "+min);
        //Q **Note : how can i do this in one stream

        
        //6. Given a list of strings, sort them in alphabetical order using Streams.
        List<String> namesList = List.of("sagar", "kamlesh", "nilesh");
        List<String> sortedList = namesList.stream().sorted().toList();
        System.out.println(sortedList);

        //7. Given a list of strings, filter and print all strings that start with the letter “S”.
        List<String> namesList2 = List.of("sagar", "kamlesh", "nilesh","Sagar");
        namesList2.stream().filter(s -> s.startsWith("s") || s.startsWith("S")).forEach(System.out::println);

        //8. Given a list of integers, print the square of each even number.
        List<Integer> integers4 = List.of(1, 2,3,4);
        integers4.stream().filter(i->i%2==0).map(i->i*i).forEach(System.out::println);

        //9. Given a list of names, count how many names start with the letter ‘A’.
        List<String> namesList3 = List.of("adam", "roman", "alex","ani");
        long count = namesList3.stream().filter(s -> s.startsWith("a") || s.startsWith("A")).count();
        System.out.println(count);

        //10. Given a list of numbers, find the average of all even numbers.
        List<Integer> integers5 = List.of(1, 2,3,4);
        Double average = integers5.stream().filter(i -> i % 2 == 0).collect(Collectors.averagingInt(i -> i));
        System.out.println(average);

        //11. a . Convert a list of String to a comma-separated string using Streams.
        List<String> namesList4 = List.of("adam", "roman", "alex","ani");
        String joiningString = namesList4.stream().collect(Collectors.joining(", "));
        System.out.println(joiningString);

        //11. b . Convert a list of integers to a comma-separated string using Streams.
        List<Integer> integerList = List.of(1, 2, 3, 4);
        String collect = integerList.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(collect);


        //12. From a list of words, find the word with the maximum length using Streams.
        List<String> namesList5 = List.of("adam", "roman", "alex","ani");
        Optional<String> max1 = namesList5.stream().max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max1.get());

        //13. Given a list of integers, print the first 3 even numbers using Streams.
        List<Integer> integers6 = List.of(1, 2,3,4,5,6,8);
         integers6.stream().filter(i -> i % 2 == 0).limit(3).forEach(i->System.out.println(i));

         //14. Given a list of strings, check if any string contains the word “Java”.
        List<String> madStrings = List.of("You are mad", "i love Java", "Still figouring out","Roman loves java");

        boolean hasJava = madStrings.stream().anyMatch(s -> s.toLowerCase().contains("java"));
        System.out.println(hasJava);



        //15. Given a list of integers, find if all numbers are positive using Streams.
        List<Integer> integers7 = List.of(1, 2,3);
        boolean allPositive = integers7.stream().allMatch(i -> i > 0);
        System.out.println(allPositive);

    }
}


/*

1. Given a list of integers, print all even numbers using Java Streams.
2. Given a list of strings, convert all strings to uppercase and collect them into a new list.
3. From a list of numbers, find and print only the distinct elements.
4. Given a list of integers, find the sum of all elements using Streams.
5. Given a list of integers, find the maximum and minimum numbers using Streams.
6. Given a list of strings, sort them in alphabetical order using Streams.
7. Given a list of strings, filter and print all strings that start with the letter “S”.
8. Given a list of integers, print the square of each even number.
9. Given a list of names, count how many names start with the letter ‘A’.
10. Given a list of numbers, find the average of all even numbers.
11. Convert a list of integers to a comma-separated string using Streams.
12. From a list of words, find the word with the maximum length using Streams.
13. Given a list of integers, print the first 3 even numbers using Streams.
14. Given a list of strings, check if any string contains the word “Java”.
15. Given a list of integers, find if all numbers are positive using Streams.


 */
