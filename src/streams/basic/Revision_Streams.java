package streams.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Revision_Streams {
    static void main() {
        List<AdvanceStreamsQuestions.Employee> employees = List.of(new AdvanceStreamsQuestions.Employee(1, "A", "Tech", 25.0),
                new AdvanceStreamsQuestions.Employee(2, "B", "Tech", 30.0),
                new AdvanceStreamsQuestions.Employee(3, "C", "HR", 20.0),
                new AdvanceStreamsQuestions.Employee(4, "D", "HR", 22.0));
        Map<String, List<String>> collect = employees.stream()
                .collect(Collectors.groupingBy(AdvanceStreamsQuestions.Employee::getDept,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                empList -> {
                                    Double avg = empList.stream().collect(Collectors.averagingDouble(e -> e.getSalary()));
                                    return empList.stream().filter(e -> e.getSalary() > avg).map(e -> e.getName()).toList();
                                }
                        )
                ));


        Map<String, List<AdvanceStreamsQuestions.Employee>> collect3 = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getName()
                ));

        Map<String, Map<Double, Long>> collect1 = employees.stream().collect(Collectors.groupingBy(AdvanceStreamsQuestions.Employee::getDept,

                Collectors.groupingBy(AdvanceStreamsQuestions.Employee::getSalary, Collectors.counting())));

        List<Integer> integerList = List.of(1, 2, 3, 2);
        Map<Integer, Boolean> collect2 = integerList.stream().collect(Collectors.groupingBy(e -> e, Collectors.collectingAndThen(
                Collectors.toList(),
                elist -> elist.size() > 1
        )));

    }
}
