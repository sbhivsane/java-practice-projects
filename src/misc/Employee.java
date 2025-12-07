package misc;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String dept;

    public Employee(int id,String name,int age,String dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dept = dept;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,age);
    }
}
