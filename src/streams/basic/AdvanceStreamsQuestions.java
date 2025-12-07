package streams.basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvanceStreamsQuestions {
    public static class Students{
        String name;
        Integer marks;

        public Students(String name, Integer marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMarks() {
            return marks;
        }

        public void setMarks(Integer marks) {
            this.marks = marks;
        }
    }

    public static class Employee{
        Integer id;
        String name;
        String dept;
        Double salary;

        public Employee(Integer id, String name, String dept, Double salary) {
            this.id = id;
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }
    }

    public static class Transaction{
        Integer id;
        String name;
        Double amount ;

        public Transaction(Integer id, String name, Double amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    static void main() {

        //1. Given a list of students (name, marks), find the top 3 scorers using Streams.
        List<Students> students = List.of(new Students("A", 55), new Students("B", 65),
                new Students("C", 75), new Students("D", 85),
                new Students("E", 55), new Students("F", 95));
        List<Students> topScorer = students.stream().sorted((s1, s2) -> s2.getMarks().compareTo(s1.getMarks()))
                .limit(3).collect(Collectors.toList());
        System.out.println(topScorer);
        //2. Given a map of productName → price, find the average price of all products using Streams.

        Map<String, Integer> productPrice = Map.of("P1", 10, "P2", 11, "P3", 12);
        // 10+11+12/3 == 33/3 --11
        Double averageOfProducts = productPrice.values().stream().collect(Collectors.averagingDouble(i -> i));
        System.out.println(averageOfProducts);

        //3. Given a list of employees (id, name, dept, salary), group by department and find the highest salary in each department.
        List<Employee> employees = List.of(new Employee(1, "A", "Tech", 25.0),
                new Employee(2, "B", "Tech", 30.0),
                new Employee(3, "C", "HR", 20.0),
                new Employee(4, "D", "HR", 22.0));


        Map<String, Double> employeeSalary = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDept(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy((e1, e2) -> {
                                    return e1.getSalary().compareTo(e2.getSalary());
                                }),
                                (e2) -> {
                                    return e2.get().getSalary();
                                }
                        )));

        System.out.println(employeeSalary);


        //4. From a list of employees, find names of all employees who earn more than the department’s average salary.


        //5. Given a list of strings, reverse each string and collect them into a list using Streams.
        List<String> listOfStrings = List.of("sagar", "kamlesh", "nilesh");
        List<String> reverseOfStrings = listOfStrings.stream().map(s -> {
            StringBuilder strb = new StringBuilder(s);
            return strb.reverse().toString();
        }).toList();
        System.out.println(reverseOfStrings);


        //6. Merge two lists of integers and remove duplicates using Streams.
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        List<Integer> list2 = List.of(1, 6, 7, 4, 5);
        List<Integer> mergedList = Stream.of(list1, list2)
                .flatMap(l -> l.stream())
                .distinct().toList();
        System.out.println(mergedList);


        //  7. Given a list of transactions, find the transaction with the minimum amount.

        List<IntermediateStreamQuestion.Transaction> transactions = List.of(new IntermediateStreamQuestion.Transaction(1, "sagar", 100.0),
                new IntermediateStreamQuestion.Transaction(2, "sagar", 150.0),
                new IntermediateStreamQuestion.Transaction(3, "sagar", 10.0),
                new IntermediateStreamQuestion.Transaction(4, "kamlesh", 100.0),
                new IntermediateStreamQuestion.Transaction(5, "kamlesh", 180.0),
                new IntermediateStreamQuestion.Transaction(6, "nilesh", 1000.0));

        IntermediateStreamQuestion.Transaction transaction = transactions.stream()
                .collect(Collectors.minBy((t1, t2) -> t1.getAmount().compareTo(t2.getAmount()))).get();
        System.out.println(transaction);

        //8. Convert a list of objects to a map where key = id and value = object, using Streams.
        Map<Integer, IntermediateStreamQuestion.Transaction> transactionsMap = transactions.stream().collect(Collectors.toMap(t -> t.getId(), t1 -> t1));
        System.out.println(transactionsMap);


        //9. From a list of sentences, extract all unique words using Streams.
        List<String> myName = List.of("my name", "is sagar", " my name bhivsane", "my name");// my name is sagar bhivsane

        List<String> distinctWords = myName.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .distinct().collect(Collectors.toList());
        System.out.println(distinctWords);

        //10. Given a list of integers, find the sum of squares of odd numbers using Streams.
        List<Integer> numbersList = List.of(1, 2, 3, 4, 5);
        Integer sum = numbersList.stream().filter(e -> e % 2 == 1).map(e -> e * e).reduce(0, (e1, e2) -> e1 + e2);
        System.out.println(sum);

        long startTime = System.currentTimeMillis();
        int sum1 = IntStream.range(1, 1000000).parallel().sum();
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);

        List<String> strList = List.of("madam", "sagar");

        List<String> pallidrom = strList.stream().filter(s -> {
            StringBuilder srb = new StringBuilder(s);
            String reverseStr = srb.reverse().toString();
            return s.equals(reverseStr);
        }).collect(Collectors.toList());
        System.out.println(pallidrom);

        List<String> ate = List.of("ate", "eat", "ab", "ba");

        Collection<List<String>> collect = ate.stream().collect(Collectors.collectingAndThen(

                Collectors.groupingBy(s -> {
                            char[] charArray = s.toCharArray();
                            Arrays.sort(charArray);
                            return Arrays.toString(charArray);
                        },
                        Collectors.toList()
                ),
                (m) -> m.values()


        ));

        System.out.println(collect);


        //15. Implement a custom collector that counts the number of elements greater than a given threshold.
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        Integer threshold = 3;
        ArrayList<Integer> valGtThTheashold = integers.stream().collect(
                () -> new ArrayList<Integer>(),
                (al, i) -> {
                    if (i > threshold)
                        al.add(i);
                },
                (al1, al2) -> al1.addAll(al2)

        );
        System.out.println(valGtThTheashold);

        // Given the STring TestYYY count return the char with max count;

        String str = "TESTYYYY";
        Character key = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting())).entrySet().stream()
                .max((e1, e2) ->
                        e1.getValue().compareTo(e2.getValue())
                ).get().getKey();
        System.out.println(key);


    }

    /*
    *
    *
    *
    * ## **ADVANCED LEVEL (36–50)**

1. Given a list of students (name, marks), find the top 3 scorers using Streams.
2. Given a map of productName → price, find the average price of all products using Streams.
3. Given a list of employees (id, name, dept, salary), group by department and find the highest salary in each department.
4. From a list of employees, find names of all employees who earn more than the department’s average salary.
5. Given a list of strings, reverse each string and collect them into a list using Streams.
6. Merge two lists of integers and remove duplicates using Streams.
7. Given a list of transactions, find the transaction with the minimum amount.
8. Convert a list of objects to a map where key = id and value = object, using Streams.
9. From a list of sentences, extract all unique words using Streams.
10. Given a list of integers, find the sum of squares of odd numbers using Streams.
11. Given a CSV file of users (id, name, age, city), read data and group users by city using Streams.
12. Use parallel streams to calculate the sum of 1 million integers and measure execution time.
13. Given a list of books (title, author, price, category), find the most expensive book in each category.
14. Given a list of strings, find all palindromes using Streams.
15. Implement a custom collector that counts the number of elements greater than a given threshold.
    *
    *
    *
    *
     */

}
