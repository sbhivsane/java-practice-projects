package streams.intermediate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MediumArticlePractice {


    static class Employee{
        private Integer id;
        private String name;
        private Integer age;
        private String gender;
        private String departName;
        private String city;
        private Date yearOfJoining;

        public Employee(Integer id, String name, Integer age, String gender, String departName, String city, Date yearOfJoining) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.departName = departName;
            this.city = city;
            this.yearOfJoining = yearOfJoining;
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Date getYearOfJoining() {
            return yearOfJoining;
        }

        public void setYearOfJoining(Date yearOfJoining) {
            this.yearOfJoining = yearOfJoining;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    ", departName='" + departName + '\'' +
                    ", city='" + city + '\'' +
                    ", yearOfJoining=" + yearOfJoining +
                    '}';
        }
    }

    static void main() {

        List<Employee> employees = List.of(
                new Employee(1, "sagar", 25, "male", "Engineering", "Pune", new Date(2025, 1, 1)),
                new Employee(1, "harshal", 35, "female", "HR", "amravati", new Date(2024, 1, 1)),
                new Employee(1, "rohan", 45, "male", "Finance", "Pune", new Date(2023, 1, 1)),
                new Employee(1, "datta", 25, "male", "Engineering", "Pune", new Date(2026, 1, 1)),
                new Employee(1, "jagruti", 35, "female", "HR", "nashik", new Date(2022, 1, 1)),
                new Employee(1, "indrjeet", 45, "male", "Finance", "kolhapur", new Date(2024, 1, 1))
                );

            // 1] group the employees by city

        Map<String, List<Employee>> employeeByCity = employees.stream().collect(Collectors.groupingBy(Employee::getCity));
       // study collector implementation;
        System.out.println(employeeByCity);
        // 2] group the employee by age
        Map<Integer, List<Employee>> employeeByAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(employeeByAge);
        // 3] find the count of male and female employee present in the organization
        Map<String, Long> genderCount = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        System.out.println(genderCount);
        //4] find the count of male and female present in each departmanet

        Map<String, Map<String, Long>> departMentByGenderCount = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.groupingBy(Employee::getGender, Collectors.counting())

        )); // here try seting 0 if not present;
        System.out.println(departMentByGenderCount);

        //5] print the name of distinct deptmnet present in the organization
        List<String> distictDeptOfOrg = employees.stream().map(Employee::getDepartName).distinct().toList();
        System.out.println(distictDeptOfOrg);

        //6] print employee details whose age is greater than 28
        List<Employee> employeeAgeGT28 = employees.stream().filter(e -> e.getAge() > 28).toList();

        //7] find maximum age/oldest of employees in organization
        Employee maxAgeEMployee = employees.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).orElse(null);
        System.out.println(maxAgeEMployee);
        //8] print average age of male and female employees in the organization
        Map<String, Double> averageAge = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingInt(Employee::getAge)
        ));

        //9] print average age of male and female employees in each department
        Map<String, Map<String, Double>> avgAgeByDepyByGender = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge)
                )

        ));
        System.out.println(avgAgeByDepyByGender);

        //10] print the number of employees in each department

        Map<String, Long> employeeCountByDepatment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName, Collectors.counting()));
        System.out.println(employeeCountByDepatment);


        System.out.println("******************************************************************************************");

        //11] find the longest serving employees in the organization
        Employee oldestEmployee = employees.stream().min((e1, e2) -> e1.getYearOfJoining().compareTo(e2.getYearOfJoining())).orElse(null);
        System.out.println(oldestEmployee);

        //12 find the longest servering employee in each department
        Map<String, Optional<Employee>> deptByLongestServeringEmpl = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.minBy((e1, e2) -> e1.getYearOfJoining().compareTo(e2.getYearOfJoining()))
        ));
        System.out.println(deptByLongestServeringEmpl);

        //13] find average age of gender in each department
        Map<String, Map<String, Double>> avgDeptGend = employees.stream().collect(
                Collectors.groupingBy(Employee::getDepartName,
                        Collectors.groupingBy(Employee::getGender,
                                Collectors.averagingInt(Employee::getAge)
                        )));

        System.out.println(avgDeptGend);

        //14] find the youngest female employee in the organization

        Employee youngestFemale = employees.stream().filter(e -> e.getGender().equals("female")).min((e1, e2) -> e1.getYearOfJoining().compareTo(e2.getYearOfJoining())).orElse(null);
        System.out.println(youngestFemale);

        //15] find the youngest employee in each department
        Map<String, Optional<Employee>> deptByYoungestEmployee = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.minBy((e1, e2) -> e1.getYearOfJoining().compareTo(e2.getYearOfJoining()))
        ));
        System.out.println(deptByYoungestEmployee);

        //16] find employee whose age is greater than 30 and less than 30
        // cross check questiom


        //17] find the department name which has the highest number of employees
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.counting()
        )).entrySet().stream().max((e1,e2)->e1.getValue().compareTo(e2.getValue())).ifPresent(e-> System.out.println(e.getKey()));


        //18] find if their any employee from HR departmemt

        boolean hrIsPresent = employees.stream().anyMatch(e -> e.getDepartName().equals("HR"));
        System.out.println(hrIsPresent);

        //19] find the department names that these employees work for where the number of employee in the department is over 3
        List<String> deptListEmpyGT3 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.counting()
        )).entrySet().stream().filter(e -> e.getValue() > 3).map(Map.Entry::getKey).toList();
        System.out.println(deptListEmpyGT3);

        //20] find all employees who lives in pune city sort them by their name and print the name of employee
        employees.stream().filter(e->e.getCity().equals("Pune")).sorted((e1,e2)->
                e1.getName().compareTo(e2.getName())
                ).forEach(e-> System.out.println(e.getName()));


        System.out.println("***************************************************************************************");

        //21] no of employees in the organization

        long noOfEmployee = employees.stream().count();
        System.out.println(noOfEmployee);
        
        //22] find employee count in every department
        Map<String, Long> deptCount = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.counting()
        ));
        
        System.out.println(deptCount);

        //23] find the department which has the highest number of employees
        // same as 17
        
        
        // 24] sorting a stream by age and name fields
        List<Employee> sortedEMployeeByAgeANdName = employees.stream().sorted((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            }
            return e1.getAge().compareTo(e2.getAge());
        }).toList();

        //25] print average age of the organization
        Double avgAge = employees.stream().collect(Collectors.averagingInt(Employee::getAge));
        System.out.println(avgAge);

        //26] print average age of each department
        Map<String, Double> avgAgeDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.averagingInt(Employee::getAge)

        ));
        System.out.println(avgAgeDept);

        //27] print average age by gender in each department
        Map<String, Map<String, Double>> avgAgeDeptByGender = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge)

                )));
        System.out.println(avgAgeDeptByGender);

        //28]to get list of employees from each department whose salry is greater than the average salary of their department
        Predicate<Employee> isGreaterThanAvgAge = (e)->{
            Double avgAge1 = employees.stream().filter(e1->e1.getDepartName().equals(e.getDepartName())).collect(Collectors.averagingInt(Employee::getAge));
            return e.getAge()>avgAge1;
        };
        Map<String, List<Employee>> avgAgeDeptGt = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.filtering(isGreaterThanAvgAge, Collectors.toList())
        ));
        System.out.println(avgAgeDeptGt);

        //29] find the highest age in the organization
        Optional<Employee> maxAgeEmploye = employees.stream().max((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        System.out.println(maxAgeEmploye);

        //30] find second highest age in the organization
        Optional<Employee> secondHighestAGe = employees.stream().sorted((e1, e2) -> e2.getAge().compareTo(e1.getAge())).skip(1).limit(1).findFirst();
        System.out.println(secondHighestAGe);

        System.out.println("*****************************************************************************************");

        //    31] Nth highest age
        int n =3;
        Optional<Employee> nthHighestAGe = employees.stream().sorted((e1, e2) -> e2.getAge().compareTo(e1.getAge())).skip(n-1).limit(1).findFirst();
        System.out.println(nthHighestAGe);

        //32] print the top 3 highest age  employees in the organization
        employees.stream().sorted((e1, e2) -> e2.getAge().compareTo(e1.getAge())).limit(3).forEach(e-> System.out.println(e));

        //33] print the top 2 highest age earned employee in each department
        //**

     //   34] find the highest age in the organization based on gender
        Map<String, Optional<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
        ));
        System.out.println(collect);

        //35 find lowest age in the organization
        Optional<Employee> lowestAgeEmployee = employees.stream().min((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        System.out.println(lowestAgeEmployee.get());

        //36] find lowest age in each department based on the gender
        Map<String, Map<String, Optional<Employee>>> lowestAgeBasedOnGender = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.groupingBy(Employee::getGender,
                        Collectors.minBy((e1, e2) -> e1.getAge().compareTo(e2.getAge())))
        ));
        System.out.println(lowestAgeBasedOnGender);

        //37 sort the employee age in the organization in asending order
        List<Employee> sortedAge = employees.stream().sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge())).toList();
        System.out.println(sortedAge);

        // 38] sort the employees salary in the organization in deseding order
        List<Employee> sortedAgeDesc = employees.stream().sorted((e1, e2) -> e2.getAge().compareTo(e1.getAge())).toList();
        System.out.println(sortedAgeDesc);

        //39] highest age based on department
        Map<String, Optional<Employee>> highestAge = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.maxBy((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
        ));
        System.out.println(highestAge);

        //40] lowest age based in each department
        Map<String, Optional<Employee>> lowestAge = employees.stream().collect(Collectors.groupingBy(Employee::getDepartName,
                Collectors.minBy((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
        ));
        System.out.println(lowestAge);

        //41] list of employee second highest age based on department
            //****
        //42] sort the employees age in each department in asecding order
        //43] sort the employees age in each department in desecding order
        //44] find list of employees whose age is less than 30 in department HR
        List<Employee> HRAgeLessThan30 = employees.stream().filter((e) -> e.getAge() < 30 && e.departName.equals("HR")).toList();
        //45] find the employees whose name start with J
        List<Employee> startWithJ = employees.stream().filter((e) -> e.getName().startsWith("J")).toList();
        System.out.println(startWithJ);



    }

    /*

    1] group the empoyess by city
2] group the employee by age
3] find the count of male and female employee present in the organization
4] find the count of male and female present in each departmanet
5] print the name of distinct deptmnet present in the organization
6] print employee details whose age is greater than 28
7] find maximum age/oldest of employees in organization
8] print average age of male and female employees in the organization
9] print average age of male and female employees in each department
10] print the number of employees in each department

11] find the longest serving employees in the organization
12 find the longest servering employee in each department
13] find average age of gender in each department
14] find the youngest female employee in the organization
15] find the youngest employee in each department
16] find employee whose age is greater than 30 and less than 30
17] find the department name which has the highest number of employees
18] find if their any employee from HR departmemt
19] find the department names that these employees work for where the number of employee in the department is over 3
20] find all employees who lives in blore city sort them by their name and print the name of employee

21] no of employees in the organization
22] find employee count in every department
23] find the department which has the highest number of employees
24] sorting a stream by age and name fields
25] print average and total salary of the organization
26] print average salary of each department
27] print average salary by gender in each department
28]to get list of employees from each department whose salry is greater than the average salary of their department
29] find the highest salary in the organization
30] find second highest salary in the organization


    31] Nth highest salary
32] print the top 3 highest salary earned employees in the organization
33] print the top 2 highest salary earned employee in each department
34] find the highest paid salary in the organization based on gender
35] find lowest paid salary in the organization
36] find lowest paid salary in each department based on the gender
37] sort the employee salary in the organization in asending order
38] sort the employees salary in the organization in deseding order
39] highest salary based on department
40] lowest paid based in each department
41] list of employee second highest record based on department
42] sort the employees salary in each department in asecding order
43] sort the employees salary in each department in desecding order
44] find list of employees whose age is less than 30 in department HR
45] find the employees whose name start with J











     */
}
