package streams.basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class IntermediateStreamQuestion {

    public static class Employee{
        String name;
        Long salary;
        String dept;

        public Employee(String name, Long salary, String dept) {
            this.name = name;
            this.salary = salary;
            this.dept = dept;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }

        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", dept=" + dept +
                    '}';
        }
    }

    public static class Product{
        Integer id;
        String name;
        Double price;

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

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Product(Integer id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
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

        //1. **Given a list of integers**, find the *second highest number* using Streams.
        List<Integer> integers1 = List.of(1, 2, 3, 4, 5);
        Optional<Integer> secondLarget = integers1.stream().sorted((i1, i2) -> i2 - i1).skip(1).limit(1).findAny();
        System.out.println(secondLarget.get());

        //2. **Given a list of employees**, find the *employee with the highest salary* using Streams.
        List<Employee> employees = List.of(new Employee("sagar", 1000L,"A"), new Employee("kamlesh", 200L,"A"), new Employee("nilesh", 400L,"B"));
        Employee employee = employees.stream().max((e1,e2)->e1.getSalary().compareTo(e2.getSalary())).get();
        System.out.println(employee);

        //3. **From a list of employees**, *group them by department name*.
        Map<String, List<Employee>> employeeByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept));
        System.out.println(employeeByDept);

        //4. **Count the number of employees** in each department using Streams.
        Map<String, Long> employyeByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
        System.out.println(employyeByDept);
        //5. **Given a list of employees**, find the *average salary per department* using Streams.
        Map<String, Double> deptAvgSalary = employees.stream().collect(Collectors.groupingBy(e -> e.getDept(), Collectors.averagingDouble(e -> e.getSalary())));
        System.out.println(deptAvgSalary);

        //6. **Given a list of employees**, create a `Map` with *department as key* and *list of employee names as value*.
        Map<String, List<String>> empOfDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(e -> e.getName(), Collectors.toList())));
        System.out.println(empOfDept);

        //7. **Given a list of products** (id, name, price), find the *most expensive product* using Streams.
        List<Product> products = List.of(new Product(1, "A", 12.0), new Product(2, "B", 6.5), new Product(3, "C", 10.0));
        Optional<Product> maxPriceProduct = products.stream().max((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        System.out.println(maxPriceProduct.get());

        //8. **Given a list of integers**, *partition them into even and odd* using `Collectors.partitioningBy()`.
        List<Integer> integers = List.of(1, 3, 4, 5, 2);
        Map<Boolean, List<Integer>> evenOddNum = integers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(evenOddNum);

        //9. **Given a list of transactions** (id, customerName, amount), find *total amount spent by each customer*.

        List<Transaction> transactions = List.of(new Transaction(1, "sagar", 100.0),
                new Transaction(2, "sagar", 150.0),
                new Transaction(3, "sagar", 100.0),
                new Transaction(4, "kamlesh", 100.0),
                new Transaction(5, "kamlesh", 180.0),
                new Transaction(6, "nilesh", 1000.0));

        Map<String, Double> totalAmountByCustomer = transactions.stream().collect(Collectors.groupingBy(Transaction::getName,
                Collectors.summingDouble(Transaction::getAmount)
        ));
        System.out.println(totalAmountByCustomer);
        
        //10. **Given a list of integers**, *remove all duplicates* using Streams.
        List<Integer> integers2 = List.of(1, 1, 2, 3, 4, 5, 55, 5, 6);
        List<Integer> distinctIntegers = integers2.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctIntegers);

        //11. **Flatten a list of lists** of integers into a *single list* using Streams.

        List<List<Integer>> lists = List.of(
                List.of(1, 1, 1, 1),
                List.of(2, 2, 2, 2),
                List.of(3, 3, 3, 3)

        );
        List<Integer> list = lists.stream().flatMap(l -> l.stream()).toList();
        System.out.println(list);

        //12. **From a list of strings**, get the *unique characters present* in all strings.
        List<String> namesList = List.of("sagar", "kamlesh", "nilesh");
        Character[] array = namesList.stream().flatMapToInt(s -> s.chars()).distinct().mapToObj(i -> (char) i).toArray(Character[]::new);


        //13. **Given a list of strings**, *sort them by length* using Streams.
        List<String> sortedBasedOnLength = namesList.stream().sorted((s1, s2) -> s1.length() - s2.length()).toList();
        System.out.println(sortedBasedOnLength);
        
        //14. **Given a list of employees**, *sort them first by department, then by salary*.
        List<Employee> sortedList = employees.stream().sorted((e1, e2) -> {
                    if (e1.getDept().equals(e2.getDept())) {
                        return e1.getSalary().compareTo(e2.getSalary());
                    } else {
                        return e1.getDept().compareTo(e2.getDept());
                    }
                }

        ).toList();
        System.out.println(sortedList);

        //15. **Find the longest word** in a sentence using Streams.
        String sentence = "my name is sagar";
        String maxString = Arrays.stream(sentence.split(" ")).max((s1,s2) -> s1.length()-s2.length()).get();
        System.out.println(maxString);

        //16. **Count the frequency of each character** in a string using Streams.
        String str="ab bc aa";
        Map<Character, Long> charByFreq = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(charByFreq);

        //17. **Count the frequency of each word** in a paragraph using Streams.
        String paragraph = " my name is sagar , my name";

        Map<String, Long> wordByCOunt = Arrays.stream(paragraph.split(" ")).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(wordByCOunt);

        //18. **Given a list of numbers**, return a *list of prime numbers* using Streams.
        List<Integer> integers3 = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> primeList = integers3.stream().filter(n -> {
            for (int i = 2; i < n; i++) {
                if (n % i == 0)
                    return false;
            }
            return true;
        }).toList();
        System.out.println(primeList);

        //19. **Given a list of integers**, *multiply all elements* using `reduce()`.
        List<Integer> integers4 = List.of(1, 2, 3);
        Integer reduce = integers4.stream().reduce(1, (i1, i2) -> i1 * i2);
        System.out.println(reduce);

        //20. **Given a list of integers**, *check if there exists any number divisible by both 3 and 5*.

        List<Integer> integers5 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,15);
        boolean b = integers5.stream().anyMatch(n -> n % 3 == 0 && n % 5 == 0);
        System.out.println(b);
    }


}



/*
*
*
### 🌿 **Java Streams – Intermediate Level (20 Questions)**

1. **Given a list of integers**, find the *second highest number* using Streams.
2. **Given a list of employees**, find the *employee with the highest salary* using Streams.
3. **From a list of employees**, *group them by department name*.
4. **Count the number of employees** in each department using Streams.
5. **Given a list of employees**, find the *average salary per department* using Streams.
6. **Given a list of employees**, create a `Map` with *department as key* and *list of employee names as value*.
7. **Given a list of products** (id, name, price), find the *most expensive product* using Streams.
8. **Given a list of integers**, *partition them into even and odd* using `Collectors.partitioningBy()`.
9. **Given a list of transactions** (id, customerName, amount), find *total amount spent by each customer*.
10. **Given a list of integers**, *remove all duplicates* using Streams.
11. **Flatten a list of lists** of integers into a *single list* using Streams.
12. **From a list of strings**, get the *unique characters present* in all strings.(sepnd lot of time)
13. **Given a list of strings**, *sort them by length* using Streams.
14. **Given a list of employees**, *sort them first by department, then by salary*.
15. **Find the longest word** in a sentence using Streams.
16. **Count the frequency of each character** in a string using Streams.
17. **Count the frequency of each word** in a paragraph using Streams.
18. **Given a list of numbers**, return a *list of prime numbers* using Streams.
19. **Given a list of integers**, *multiply all elements* using `reduce()`.
20. **Given a list of integers**, *check if there exists any number divisible by both 3 and 5*.
*
*
* */
