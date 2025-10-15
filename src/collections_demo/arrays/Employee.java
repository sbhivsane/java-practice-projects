package collections_demo.arrays;

public class Employee implements Comparable<Employee>{
    String name;
    int age;
    double salary;

    Employee(String n,int a, double sal){
        this.name = n;
        this.age =a;
        this.salary = sal;
    }


    @Override
    public String toString() {
        return "("+name+" , "+age+" , "+salary+")";
    }

    @Override
    public int compareTo(Employee o) {
        if(this.age==o.age)
            return (int) (this.salary-o.salary);
      return  this.age - o.age;
    }
}
